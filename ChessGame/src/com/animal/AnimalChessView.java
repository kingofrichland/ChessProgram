package com.animal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;

import com.BoardGameModel;
import com.BoardGameView;
import com.ChessPiece;
import com.Move;

public class AnimalChessView extends BoardGameView {
	
	protected static final int 	FONT_SIZE = 36;
	
    //FLAG SETTING
    public static final int FLAG_SIZE 			= 60;
    public static final int FLAG_HALF_SIZE		= FLAG_SIZE/2;
    
    //BOARD SETTING
    public static final int LEFT_MARGIN = 5;	 //Left Margin of Main Panel, left spacing between the screen and the board left edge
    public static final int TOP_MARGIN = 5;	 //Top Margin of  Main Panel, top spacing between the screen and the board top edge
    public static final int LINE_INTERVAL = 70;	//Change this value will change the board size

	private static final long serialVersionUID = 1L;

	public AnimalChessView() {
		super();
        BufferedImage biBoard = loadImage("animal/Dou_shou_qi_board.png");
        this.setSize(biBoard.getWidth(), biBoard.getHeight()+50);
	}

	@Override
	public void drawBoard(Graphics g, BoardGameModel data) {
		Graphics2D g2 = (Graphics2D)g;
        BufferedImage biBoard = loadImage("animal/Dou_shou_qi_board.png");
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
		
		synchronized (pList) {
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

	/*
	 * Return true: if the chess piece is already in target XY
	 * Return false: if the chess piece still not in target XY
	 * */
	public boolean movePiece(Move move, BoardGameModel model){
		boolean bReachedTarget = false;
		int pieceId = move.getChessPiece().getId();
		ChessPiece p = model.getBoardGame().getChessBoard().getChessPieces().getPiece(pieceId);
		p.setFlow(true);
		
		int targetX = getGraphicalXByX(move.getToX());
		int targetY = getGraphicalYByY(move.getToY());
		int currentX = p.getFlowX();
		int currentY = p.getFlowY();
		currentX = (currentX==0)?getGraphicalXByX(p.getX()):p.getFlowX();
		currentY = (currentY==0)?getGraphicalYByY(p.getY()):p.getFlowY();
		int diffX = targetX - currentX;
		int diffY = targetY - currentY;
		int absX = Math.abs(diffX);
		int absY = Math.abs(diffY);
		currentX+=(diffX<0?-1:1)*Math.min(absX,10);
		currentY+=(diffY<0?-1:1)*Math.min(absY,10);
		p.setFlowX(currentX); 
		p.setFlowY(currentY); 
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
