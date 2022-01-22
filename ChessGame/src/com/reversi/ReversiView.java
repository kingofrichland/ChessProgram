package com.reversi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import com.BoardGameModel;
import com.BoardGameView;
import com.ChessPiece;
import com.Move;

public class ReversiView extends BoardGameView {

	private static final long serialVersionUID = 1L;

	 //FLAG SETTING
    private final int FLAG_SIZE 			= 38;
    private final int FLAG_HALF_SIZE		= FLAG_SIZE/2;
    
    //BOARD SETTING
    public final int LEFT_MARGIN = 25 ;	 //Left Margin of Main Panel, left spacing between the screen and the board left edge
    public final int TOP_MARGIN = 25 ;	 //Top Margin of  Main Panel, top spacing between the screen and the board top edge
    private final int LINE_INTERVAL = 44;	//Change this value will change the board size
    
    BufferedImage biBoardObj = null;
	
	public ReversiView() {
		super();
        BufferedImage biBoard = getBoardImage();
        this.setSize(biBoard.getWidth(), biBoard.getHeight()+50);
	}
	
	private BufferedImage getBoardImage(){
		if(biBoardObj==null){
			biBoardObj = loadImage("reversi/reversi_board.jpg");
		}
		return biBoardObj;
	}

	@Override
	public void drawBoard(Graphics g, BoardGameModel data) {
		Graphics2D g2 = (Graphics2D)g;
        BufferedImage biBoard = getBoardImage();
        g2.drawImage(biBoard, 0, 0, null);
	}

	@Override
	public void drawPieces(Graphics g, BoardGameModel model) {
		if (model == null) return;
		
		Graphics2D g2 = (Graphics2D)g;
		
		intializeGraphicsSetting(g2);
		float BORDER_WIDTH = 0.2f;
        g2.setStroke(new BasicStroke(
        		BORDER_WIDTH,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND)); // Set the line width here

		
		// Read model and draw a pieces to reflect their position
		Vector<ChessPiece> pList = model.getBoardGame().getChessBoard().getChessPieces().getPieceList();
		for(ChessPiece p :  pList){
			int x = getGraphicalXByX(p.getX());
			int y = getGraphicalYByY(p.getY());
			int races = p.getRaces();
			if (p.isFlow()){ // Flipping Case: Draw Oval
				int z = p.getFlowZ(); // Flip from 0 -> 180, or 180 <- 0
				switch (races) {
				case ReversiPieces.BLACK: 
					drawCircle(g2, x, y, z, Color.BLACK, FLAG_SIZE);
					break;
				case ReversiPieces.WHITE: 
					drawCircle(g2, x, y, z, Color.WHITE, FLAG_SIZE);
					break;
				}
			}else{ // Normal Case: Draw Circle
				switch (races) {
				case ReversiPieces.BLACK: 
					drawCircle(g2, x, y, Color.BLACK, FLAG_SIZE);
					break;
				case ReversiPieces.WHITE: 
					drawCircle(g2, x, y, Color.WHITE, FLAG_SIZE);
					break;
				}
			}
		}
		
		// Read move and draw the new piece to the position
		Move move = model.getBoardGame().getPlayers().getCurrPlayer().getMove();
		if (move!=null){
			ChessPiece mp = move.getChessPiece();
			int x = getGraphicalXByX(mp.getX());
			int y = getGraphicalYByY(mp.getY());
			int races = mp.getRaces();
			switch (races) {
			case ReversiPieces.BLACK: 
				drawCircle(g2, x, y, Color.BLACK, FLAG_SIZE);
				break;
			case ReversiPieces.WHITE: 
				drawCircle(g2, x, y, Color.WHITE, FLAG_SIZE);
				break;
			}
		}

	}

	@Override
	public ChessPiece getPiece(BoardGameModel model, int iX, int iY) {
		if (model == null) return null;
		if (model.getBoardGame() == null) return null;
		if (model.getBoardGame().getChessBoard() == null) return null;
		if (model.getBoardGame().getChessBoard().getChessPieces() == null) return null;
		
		Vector<ChessPiece> pList = model.getBoardGame().getChessBoard().getChessPieces().getPieceList();
		for(ChessPiece p :  pList){
			int x = getGraphicalXByX(p.getX());
			int y = getGraphicalYByY(p.getY());
			double a = Math.abs(x-iX);
			double b = Math.abs(y-iY);
			if (a*a + b*b <= FLAG_HALF_SIZE*FLAG_HALF_SIZE) return p;
		}
		return null;
	}
	
	 protected void drawCircle(Graphics2D g2, int absoluteX, int absoluteY, Color cColor, int flagSize){
	    	
		 	int flagHalfSize = flagSize / 2;
		 
	        //Create flag object
	    	Ellipse2D.Float ellipseFlagCircle = new Ellipse2D.Float(
	                (float) absoluteX-(flagHalfSize),
	                (float) absoluteY-(flagHalfSize),
	                (float) flagSize,
	                (float) flagSize);//-(Con_ChessConstant.FLAG_HALF_SIZE)
	        
	        float[] dist = null;
	        Color[] colors = null;
	        if (cColor==Color.WHITE) {
	        	dist = new float[]{0.5f, 0.8f, 1.0f};
	        	colors = new Color[]{Color.WHITE, Color.GRAY, Color.DARK_GRAY};
	        }else{
	        	dist = new float[]{0.2f, 0.5f, 1.0f};
	        	colors = new Color[]{Color.LIGHT_GRAY, Color.BLACK, Color.BLACK};
	        }
	        RadialGradientPaint gpWhiteToFlagColor = new RadialGradientPaint(
	        		(float) (absoluteX),
	                (float) (absoluteY),
	                flagSize,
	                (float) (absoluteX+(flagSize)),
	                (float) (absoluteY-(flagSize)),
	                dist, 
	                colors,
	                CycleMethod.NO_CYCLE);
	        
	        
	        //Fill the circle
	        g2.setPaint(gpWhiteToFlagColor);
	        g2.fill(ellipseFlagCircle);
	        
	        //Draw the outline of the object
	        g2.setColor(cColor);
	        g2.draw(ellipseFlagCircle);
	    }   

	 protected void drawCircle(Graphics2D g2, int absoluteX, int absoluteY, int absoluteZ, Color cColor, int flagSize){
	    	
		 	int flagHalfSize = flagSize / 2;
		 
	        //Create flag object
	    	//Float(float x, float y, float w, float h)
		 	//Calc some 3D, so to know from top-view, how much width should be if Z is an angel from 0-180
		 	boolean bHasFlipOver = absoluteZ > 90;
		 	float angle = (bHasFlipOver) ? 180 - absoluteZ : absoluteZ;
		 	float halfSizeIn3D = (flagHalfSize * (90-angle)) / 90;
		 	float sizeIn3D = halfSizeIn3D * 2;
		 	if(sizeIn3D>flagSize){
		 		System.out.printf("Z:%2d; flagSize:%2d; flagHalfSize:%2d; angle:%.1f; halfSizeIn3D:%.1f; sizeIn3D:%.1f; \n",absoluteZ,flagSize,flagHalfSize,angle,halfSizeIn3D,sizeIn3D);
		 	}
		 	
	    	Ellipse2D.Float ellipseFlagCircle = new Ellipse2D.Float(
	                (float) absoluteX-(halfSizeIn3D),
	                (float) absoluteY-(flagHalfSize),
	                (float) sizeIn3D,
	                (float) flagSize);
	        
	        boolean bDrawWhite = false;
	        boolean bDrawBlack = false;
	        if (cColor==Color.WHITE) {
	        	if(!bHasFlipOver){
	        		bDrawWhite = true;
	        	}else{
	        		bDrawBlack = true;
	        	}
	        }else{
	        	if(!bHasFlipOver){
	        		bDrawBlack = true;
	        	}else{
	        		bDrawWhite = true;
	        	}
	        }
	        float[] dist = null;
	        Color[] colors = null;
	        if (bDrawWhite){
	        	dist = new float[]{0.5f, 0.8f, 1.0f};
	        	colors = new Color[]{Color.WHITE, Color.GRAY, Color.DARK_GRAY};
	        }
	        if (bDrawBlack){
	        	dist = new float[]{0.2f, 0.5f, 1.0f};
	        	colors = new Color[]{Color.LIGHT_GRAY, Color.BLACK, Color.BLACK};
	        }
	        
	        RadialGradientPaint gpWhiteToFlagColor = new RadialGradientPaint(
	        		(float) (absoluteX),
	                (float) (absoluteY),
	                flagSize,
	                (float) (absoluteX+(flagSize)),
	                (float) (absoluteY-(flagSize)),
	                dist, 
	                colors,
	                CycleMethod.NO_CYCLE);
	        
	        //Fill the circle
	        g2.setPaint(gpWhiteToFlagColor);
	        g2.fill(ellipseFlagCircle);
	        
	        //Draw the outline of the object
	        g2.setColor(cColor);
	        g2.draw(ellipseFlagCircle);
	    }  

	/*
	 * Return true: if the chess piece is already in target XY
	 * Return false: if the chess piece still not in target XY
	 * */
	public boolean movePiece(Move move, BoardGameModel model){
		boolean bReachedTarget = false;
		
		// Update Related Eat Piece
		boolean bAllFlipFinish = true;
		int targetZ = 180;
		Vector<Integer> vList = move.getSuppInteger();
		boolean bToBreak = false;
		for(Integer id : vList){
			ChessPiece p = model.getBoardGame().getChessBoard().getChessPieces().getPiece(id);
			p.setFlow(true);
			int currentZ = p.getFlowZ();
			bToBreak = (currentZ==0);
			System.out.printf("Reversi XYZ : (%2d,%2d,%2d) \n",move.getToX(),move.getToY(),currentZ);
			currentZ=Math.min(currentZ+30,180);
			p.setFlowZ(currentZ); 
			if (currentZ<targetZ){
				bAllFlipFinish = false;
			}
			if (bToBreak) break;
		}
		if (bAllFlipFinish){
			bReachedTarget = true;
			for(Integer id : vList){
				ChessPiece p = model.getBoardGame().getChessBoard().getChessPieces().getPiece(id);
				p.setFlowZ(0); 
				p.setFlow(false);
			}
		}
		
		return bReachedTarget;
		
	}
	
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getGraphicalXByX(int x){
            return (LEFT_MARGIN)+((x-1)*LINE_INTERVAL)+LINE_INTERVAL/2;
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getGraphicalYByY(int y){
            return (TOP_MARGIN)+((y-1)*LINE_INTERVAL)+LINE_INTERVAL/2;
    }
        
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getXByGraphicalX(int iAbsoluteX){
        short result = 0;
        result = (short)(Math.ceil((float)(iAbsoluteX-(LEFT_MARGIN))/(LINE_INTERVAL)));
        return result;
    }
    
    //Convert absolute X-axis, Y-axis values to X,Y
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getYByGraphicalY(int iAbsoluteY){
        short result = 0;
        result = (short)(Math.ceil((float)(iAbsoluteY-(TOP_MARGIN))/(LINE_INTERVAL)));
        return result;
    }

}
