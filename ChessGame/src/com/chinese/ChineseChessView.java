package com.chinese;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

import com.BoardGameModel;
import com.BoardGameView;
import com.ChessPiece;
import com.Move;

public class ChineseChessView extends BoardGameView { 

	private static final long serialVersionUID = 1L;
	
    //FLAG SETTING
    public static final int FLAG_SIZE 			= 48;
    public static final int FLAG_HALF_SIZE		= FLAG_SIZE/2;
    
    //BOARD SETTING
    public static final int LEFT_MARGIN = 0;	 //Left Margin of Main Panel, left spacing between the screen and the board left edge
    public static final int TOP_MARGIN = 0;	 //Top Margin of  Main Panel, top spacing between the screen and the board top edge
    public static final float LINE_WIDTH = 2.5f;
    public static final int MENU_WIDTH = 25;
    public static final int LINE_INTERVAL = 54;	//Change this value will change the board size
    public static final int LINE_INTERVAL_HALF = LINE_INTERVAL/2;
    public static final int NUM_OF_VLINE = 9;
    public static final int NUM_OF_HLINE = 10;
    public static final Color BOARD_LINE_COLOR = Color.BLACK;
    public static final Color BOARD_BG_COLOR = Color.LIGHT_GRAY;
    
    //Buffered Image Setting
    public static final int BI_LEFT_MARGIN = FLAG_HALF_SIZE; //Left Margin of Buffered Image, or the Spacing between board edge and the first left vertical line
    public static final int BI_TOP_MARGIN = FLAG_HALF_SIZE;	 //Top Margin of Buffered Image, or the Spacing between board edge and the first top horizontal line
    public static final int BI_WIDTH = (LINE_INTERVAL * (NUM_OF_VLINE - 1 + 1));	 //Width of Buffered Image
    public static final int BI_HEIGHT = (LINE_INTERVAL * (NUM_OF_HLINE - 1 + 1));	 //Height of Buffered Image
    private ChineseChessBoardImage chineseChessBoardImageObj = this.new ChineseChessBoardImage();

	public ChineseChessView() {
		super();
		BufferedImage biBoard = chineseChessBoardImageObj.getInstance();
        this.setSize(biBoard.getWidth(), biBoard.getHeight()+50);
	}

	@Override
	public void drawBoard(Graphics g, BoardGameModel model) {
		Graphics2D g2 = (Graphics2D)g;
		BufferedImage biBoard = chineseChessBoardImageObj.getInstance();
        g2.drawImage(biBoard, 0, 0, null);
	}

	@Override
	public void drawPieces(Graphics g, BoardGameModel model) {
		if (model == null) return;
		
		Graphics2D g2 = (Graphics2D)g;
		
		intializeGraphicsSetting(g2);
		
		// Draw a circle
		//ModelVO vo = model.getModelVO();
		//if (vo!=null && vo instanceof ChineseChessModelVO){
		//	ChineseChessModelVO ccvo = (ChineseChessModelVO)vo;
		//	drawCircle(g2, ccvo.getX(), ccvo.getY(), Color.RED);
		//}else{
		//	drawCircle(g2, 0, 0, Color.RED);
		//}
		
		// Read model and draw a pieces to reflect their position
		Vector<ChessPiece> pList = model.getBoardGame().getChessBoard().getChessPieces().getPieceList();
		ChessPiece flowPiece = null;
		for(ChessPiece p :  pList){
			if (p.isFlow()){
				flowPiece = p;
				continue;
			}
			int x = getGraphicalXByX(p.getX());
			int y = getGraphicalYByY(p.getY());
			Color c = model.getBoardGame().getChessBoard().getChessPieces().getColorByRaces(p.getRaces());
			String word = p.getWord();
			drawCircle(g2, x, y, c, FLAG_SIZE);
			drawWord(g2, word, x, y, c, FONT_SIZE);
		}
		
		// Draw Flow Piece at last
		if (flowPiece!=null){
			int x = flowPiece.getFlowX();
			int y = flowPiece.getFlowY();
			Color c = model.getBoardGame().getChessBoard().getChessPieces().getColorByRaces(flowPiece.getRaces());
			String word = flowPiece.getWord();
			drawCircle(g2, x, y, c, FLAG_SIZE);
			drawWord(g2, word, x, y, c, FONT_SIZE);
		}

		
	}

	@Override
	public ChessPiece getPiece(BoardGameModel model, int iX, int iY) {
		if (model == null) return null;
		if (model.getBoardGame() == null) return null;
		if (model.getBoardGame().getChessBoard() == null) return null;
		if (model.getBoardGame().getChessBoard().getChessPieces() == null) return null;
		
    	int FLAG_SIZE = 48;
    	int FLAG_HALF_SIZE = FLAG_SIZE / 2;
		
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
	
    private class ChineseChessBoardImage{

    	BufferedImage singleImage = null;
    	
		public BufferedImage getInstance() {
			if (singleImage == null){
				singleImage = getBufferedImage();
	        }else{
	            //Use Old Image
	            //I.E. biBoard
	        }//end if : Check if Flag Image Created	
			return singleImage;
		}

    	private BufferedImage getBufferedImage() {
    		
        	BufferedImage biBoard = null;
        	Graphics2D gbiBoard = null;
            int x1=0,y1=0,w1=0,h1=0;
            int x2=0,y2=0,w2=0,h2=0;
            Rectangle2D.Float rectWallpaper;
            Rectangle2D.Float rectOutline;
            GradientPaint gWhiteToSilver;
			
            //Create New Image
            biBoard = new BufferedImage(
                    BI_WIDTH,
                    BI_HEIGHT,
                    BufferedImage.TYPE_INT_ARGB);
            gbiBoard = biBoard.createGraphics();
            
            intializeGraphicsSetting(gbiBoard);
            
            //Draw wallpaper/background of the board
            x2 = 0;
            y2 = 0;
            w2 = biBoard.getWidth();
            h2 = biBoard.getHeight();
            rectWallpaper = new Rectangle2D.Float( x2, y2, w2 , h2 );
            gWhiteToSilver = new GradientPaint(
                    (float)x2,			(float)y2,			Color.WHITE,
                    (float)x2+w2/4,		(float)y2+h2/4,		BOARD_BG_COLOR,
                    true);
            gbiBoard.setPaint(gWhiteToSilver);
            gbiBoard.fill(rectWallpaper);
            
            //Draw outline of the board
            x1 = getGraphicalXByX(1,true)-5;			  //Outline x
            y1 = getGraphicalYByY(1,true)-5;			  //Outline y
            w1 = BI_WIDTH - LINE_INTERVAL + 10;//Outline width
            h1 = BI_HEIGHT - LINE_INTERVAL + 10;//Outline height
            rectOutline = new Rectangle2D.Float((float)x1,(float)y1,(float)w1,(float)h1);
            gbiBoard.setColor(BOARD_LINE_COLOR);
            gbiBoard.draw(rectOutline);
            
            //Draw a series of horizontal lines on screen
            for (int h = 1; h <= NUM_OF_HLINE; h++)
                gbiBoard.drawLine(
                        getGraphicalXByX(1,true),
                        getGraphicalYByY(h,true),
                        getGraphicalXByX(NUM_OF_VLINE,true),
                        getGraphicalYByY(h,true));
            
            //Draw a series of top-vertical lines on screen (above the river)
            for (int v = 0; v <= NUM_OF_VLINE; v++)
                gbiBoard.drawLine(
                        getGraphicalXByX(v,true),
                        getGraphicalYByY(1,true),
                        getGraphicalXByX(v,true),
                        getGraphicalYByY((int)(NUM_OF_HLINE/2),true));
            
            //Draw a series of bottom-vertical lines on screen (below the river)
            for (int v = 0; v <= NUM_OF_VLINE; v++)
                gbiBoard.drawLine(
                        getGraphicalXByX(v,true),
                        getGraphicalYByY((int)(NUM_OF_HLINE/2)+1,true),
                        getGraphicalXByX(v,true),
                        getGraphicalYByY(NUM_OF_HLINE,true));
            
            //Draw River Word
            int iRiverWordY = (int)getGraphicalYByY(NUM_OF_HLINE/2,true)+LINE_INTERVAL_HALF;
            int iRiverWordX = (int)getGraphicalXByX(2,true);
            drawWord(gbiBoard, "楚", iRiverWordX, iRiverWordY, BOARD_LINE_COLOR, FONT_SIZE);
            iRiverWordX = (int)getGraphicalXByX(3,true);
            drawWord(gbiBoard, "河", iRiverWordX, iRiverWordY, BOARD_LINE_COLOR, FONT_SIZE);
            iRiverWordX = (int)getGraphicalXByX(7,true);
            drawWord(gbiBoard, "漢", iRiverWordX, iRiverWordY, BOARD_LINE_COLOR, FONT_SIZE);
            iRiverWordX = (int)getGraphicalXByX(8,true);
            drawWord(gbiBoard, "界", iRiverWordX, iRiverWordY, BOARD_LINE_COLOR, FONT_SIZE);
            
            //Draw in on Top
            gbiBoard.drawLine(
                    getGraphicalXByX(4,true),
                    getGraphicalYByY(1,true),
                    getGraphicalXByX(6,true),
                    getGraphicalYByY(3,true));
            gbiBoard.drawLine(
                    getGraphicalXByX(6,true),
                    getGraphicalYByY(1,true),
                    getGraphicalXByX(4,true),
                    getGraphicalYByY(3,true));
            
            //Draw in on Bottom
            gbiBoard.drawLine(
                    getGraphicalXByX(4,true),
                    getGraphicalYByY(8,true),
                    getGraphicalXByX(6,true),
                    getGraphicalYByY(10,true));
            gbiBoard.drawLine(
                    getGraphicalXByX(6,true),
                    getGraphicalYByY(8,true),
                    getGraphicalXByX(4,true),
                    getGraphicalYByY(10,true));
		
			return biBoard;
		}
	    
    }
    
    public boolean movePiece(Move move, BoardGameModel model){
		boolean bReachedTarget = false;
		ChessPiece p = move.getChessPiece();
		int pieceId = p.getId();
		System.out.printf("Piece (%d,%s)(%d,%d)\t",p.getId(), p.getWord(), p.getX(), p.getY());
		p.setFlow(true);
		
		int targetX = getGraphicalXByX(move.getToX());
		int targetY = getGraphicalYByY(move.getToY());
		int currentX = p.getFlowX();
		int currentY = p.getFlowY();
		currentX = (currentX==0)?getGraphicalXByX(p.getX()):p.getFlowX();
		currentY = (currentY==0)?getGraphicalYByY(p.getY()):p.getFlowY();
		System.out.printf("Before (%d,%d)\t",currentX,currentY);
		int diffX = targetX - currentX;
		int diffY = targetY - currentY;
		int absX = Math.abs(diffX);
		int absY = Math.abs(diffY);
		currentX+=(diffX<0?-1:1)*Math.min(absX,10);
		currentY+=(diffY<0?-1:1)*Math.min(absY,10);
		p.setFlowX(currentX); 
		p.setFlowY(currentY); 
		System.out.printf("Target (%d,%d)\t",targetX,targetY);
		System.out.printf("diff (%d,%d)\t",diffX,diffY);
		System.out.printf("abs (%d,%d)\t",absX,absY);
		System.out.printf("After (%d,%d)\n",currentX,currentY);
		if (absX==0 && absY==0){
			bReachedTarget = true;
			p.setFlow(false);
		}
		model.getBoardGame().getChessBoard().getChessPieces().setPiece(pieceId, p);
		return bReachedTarget;
	}
    
    //Convert X,Y to Buffered Image absolute X-axis, Y-axis values
    public int getGraphicalXByX(int x){
        return getGraphicalXByX(x, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static int getGraphicalXByX(int x, boolean bIsChessBoardOnly){
        if (bIsChessBoardOnly)
            return (BI_LEFT_MARGIN)+((x-1)*LINE_INTERVAL);
        else
            return (LEFT_MARGIN + BI_LEFT_MARGIN)+((x-1)*LINE_INTERVAL);
    }
    
    //Convert Screen absolute X-axis, Y-axis to X,Y values
    public int getGraphicalYByY(int y){
        return getGraphicalYByY(y, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public static int getGraphicalYByY(int y, boolean bIsChessBoardOnly){
        if (bIsChessBoardOnly)
            return (BI_TOP_MARGIN)+((y-1)*LINE_INTERVAL);
        else
            return (TOP_MARGIN + BI_TOP_MARGIN)+((y-1)*LINE_INTERVAL);
    }

        
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getXByGraphicalX(int iAbsoluteX){
        return getXByGraphicalX(iAbsoluteX, false);
    }
    
    //Convert X,Y to absolute X-axis, Y-axis values
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public short getXByGraphicalX(int iAbsoluteX, boolean bIsChessBoardOnly){
        short result = 0;
        if (bIsChessBoardOnly)
            result = (short)(Math.round((float)(iAbsoluteX-(BI_LEFT_MARGIN))/(LINE_INTERVAL))+1);
        else
            result = (short)(Math.round((float)(iAbsoluteX-(LEFT_MARGIN+BI_LEFT_MARGIN))/(LINE_INTERVAL))+1);
        return result;
    }

    //Convert absolute X-axis, Y-axis values to X,Y
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public int getYByGraphicalY(int iAbsoluteY){
        return getYByGraphicalY(iAbsoluteY, false);
    }
    
    //Convert absolute X-axis, Y-axis values to X,Y
    //bIsChessBoardOnly: True for calculate point start from ChessBoard (0,0), False for screen(0,0)
    public short getYByGraphicalY(int iAbsoluteY, boolean bIsChessBoardOnly){
        short result = 0;
        if (bIsChessBoardOnly)
            result = (short)(Math.round((float)(iAbsoluteY-(BI_TOP_MARGIN))/(LINE_INTERVAL))+1);
        else
            result = (short)(Math.round((float)(iAbsoluteY-(TOP_MARGIN+BI_TOP_MARGIN))/(LINE_INTERVAL))+1);
        return result;
    }



    
}
