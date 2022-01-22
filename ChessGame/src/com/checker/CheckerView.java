package com.checker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.BoardGameModel;
import com.BoardGameView;
import com.ChessPiece;
import com.Move;

public class CheckerView extends BoardGameView {

	private static final long serialVersionUID = 1L;
	
    //FLAG SETTING
    public static final int FLAG_SIZE 			= 64;
    public static final int FLAG_HALF_SIZE		= FLAG_SIZE/2;
    
    //BOARD SETTING
    public static final int LEFT_MARGIN = 2;	 //Left Margin of Main Panel, left spacing between the screen and the board left edge
    public static final int TOP_MARGIN = 2;	 //Top Margin of  Main Panel, top spacing between the screen and the board top edge
    public static final float LINE_WIDTH = 2.5f;
    public static final int MENU_WIDTH = 25;
    public static final int LINE_INTERVAL = 75;	//Change this value will change the board size
    public static final int LINE_INTERVAL_HALF = LINE_INTERVAL/2;
    public static final int NUM_OF_VLINE = 9;
    public static final int NUM_OF_HLINE = 9;
    public static final Color BOARD_LINE_COLOR = Color.BLACK;
    public static final Color BOARD_BG_COLOR = Color.LIGHT_GRAY;

    BufferedImage biBoardObj = null;

	public CheckerView() {
		super();
        BufferedImage biBoard = getBoardImage();
        this.setSize(biBoard.getWidth(), biBoard.getHeight()+50);
	}

	private BufferedImage getBoardImage(){
		if(biBoardObj==null){
			biBoardObj = loadImage("checker/checker_board.png");
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
			drawCircle(g2, x, y, c, FLAG_SIZE-10);
			//drawWord(g2, word, x, y, c, CheckerConst.FONT_SIZE);
		}
		// Draw Flow Piece at last
		if (flowPiece!=null){
			int x = flowPiece.getFlowX();
			int y = flowPiece.getFlowY();
			Color c = model.getBoardGame().getChessBoard().getChessPieces().getColorByRaces(flowPiece.getRaces());
			String word = flowPiece.getWord();
			drawCircle(g2, x, y, c, FLAG_SIZE);
			drawCircle(g2, x, y, c, FLAG_SIZE-10);
			//drawWord(g2, word, x, y, c, CheckerConst.FONT_SIZE);
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

	/*
	 * Return true: if the chess piece is already in target XY
	 * Return false: if the chess piece still not in target XY
	 * */
	public boolean movePiece(Move move, BoardGameModel model){
		boolean bReachedTarget = false;
		int pieceId = move.getChessPiece().getId();
		ChessPiece p = model.getBoardGame().getChessBoard().getChessPieces().getPiece(pieceId);
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
