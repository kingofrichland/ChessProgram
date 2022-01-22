package com.chinese;

import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.ChessPieces;

public class ChineseChessBoard extends ChessBoard {
	
	public ChineseChessBoard(){
		super("Chinese Chess Board",9,10);
	}
	
	public void addPieces(ChessPieces chessPieces){
		super.setChessPieces(chessPieces);
		
		// 1. Get Races List
		// 2. For each races, get piece List, determine the position set
		// 3. For each piece, map the location to the position set
		// 4. set the position
		
		/*
		int[] racesList = chessPieces.getRacesList();
		for(int r : racesList){
			Vector<ChessPiece> plist = chessPieces.getPieceList(r);
			Point[] pos = (r == ChineseChessPieces.RED) ? BOTTOM_CHESS_PIECE_POS : TOP_CHESS_PIECE_POS;
			for(ChessPiece p : plist){
				
				switch(){
				case KING: p = new King(RACES[i]); break;
				case CHARIOT: p = new Chariot(RACES[i]); break;
				case HORSE: p = new Horse(RACES[i]); break;
				case CANNON: p = new Cannon(RACES[i]); break;
				case SERVANT: p = new Servant(RACES[i]); break;
				case CHANCELLOR: p = new Chancellor(RACES[i]); break;
				case PAWN: p = new Pawn(RACES[i]); break;
				}
				if(p!=null){
					//p.setX(CHESS_PIECE_POS[j].getX());
					//p.setY(CHESS_PIECE_POS[j].getY());
					pieces.add(p);
				}
				
			}
		}
		
		
		
		Vector<ChessPiece> vChessPieceList = chessPieces.getPieceList();
		
		if (vChessPieceList!=null){
			for(ChessPiece p : vChessPieceList){
				int races = p.getRaces();
				Point[] CHESS_PIECE_POS = null;
				CHESS_PIECE_POS = (races == ChineseChessPieces.RED) ? BOTTOM_CHESS_PIECE_POS : TOP_CHESS_PIECE_POS;
				
			}
		}
		
		int[] racesList = chessPieces.getRacesList();
		
		// Set Position For Different Races
		for(int races : racesList){
			Point[] CHESS_PIECE_POS = null;
			switch(races){
			case ChineseChessPieces.RED: 
				CHESS_PIECE_POS = BOTTOM_CHESS_PIECE_POS;
				break;
			case ChineseChessPieces.BLACK: 
				CHESS_PIECE_POS = TOP_CHESS_PIECE_POS;
				break;
			}
			
			for(Point p : CHESS_PIECE_POS){
				ChessPiece p = null;
				switch(CHESS_PIECE_IDX[j]){
				case KING: p = new King(RACES[i]); break;
				case CHARIOT: p = new Chariot(RACES[i]); break;
				case HORSE: p = new Horse(RACES[i]); break;
				case CANNON: p = new Cannon(RACES[i]); break;
				case SERVANT: p = new Servant(RACES[i]); break;
				case CHANCELLOR: p = new Chancellor(RACES[i]); break;
				case PAWN: p = new Pawn(RACES[i]); break;
				}
				if(p!=null){
					//p.setX(CHESS_PIECE_POS[j].getX());
					//p.setY(CHESS_PIECE_POS[j].getY());
					pieces.add(p);
				}
			}			
		}
		*/
		/*
		if (chessPieces!=null){
			Vector<ChessPiece> vPieceList = chessPieces.getPieces();
			for(int i=0; vPieceList!=null && i < vPieceList.size(); i++){
				ChessPiece cp = vPieceList.get(i);
			}
		}*/
	}

	@Override
	public String convertChessBoardToString(ChessBoard board, boolean bToRotateBoard) {
		String name = board.getName();
		int w = board.getWidth();
		int h = board.getHeight();
		String str = "";
		str += String.format("%s\n", name);
		str += String.format("%d %d\n", w, h);
		Vector<ChessPiece> vPieceList = board.getChessPieces().getPieceList();
		char[][] board2d = new char[w][h];
		for(int y=0; y<h; y++)
			for(int x=0; x<w; x++)
				board2d[x][y] = '.';
		for(ChessPiece p : vPieceList){
			int x = p.getX();
			int y = p.getY();
			char ch = '.';
			switch(p.getType()){
			case ChineseChessPieces.KING: 		ch = isTOP(p)?'k':'K'; break;
			case ChineseChessPieces.CHARIOT: 	ch = isTOP(p)?'t':'T'; break;
			case ChineseChessPieces.HORSE: 		ch = isTOP(p)?'h':'H'; break;
			case ChineseChessPieces.CANNON: 	ch = isTOP(p)?'c':'C'; break;
			case ChineseChessPieces.SERVANT: 	ch = isTOP(p)?'s':'S'; break;
			case ChineseChessPieces.CHANCELLOR: ch = isTOP(p)?'e':'E'; break;
			case ChineseChessPieces.PAWN: 		ch = isTOP(p)?'p':'P'; break;
			}
			board2d[x-1][y-1] = ch;
		}
		
		if(bToRotateBoard){
			char[][] rBoard2d = new char[w][h];
			for(int y=0; y<h; y++)
				for(int x=0; x<w; x++)
					//rBoard2d[x][y] = board2d[w-x-1][h-y-1];
					rBoard2d[x][y] = reverseLetter(board2d[w-x-1][h-y-1]);
			for(int y=0; y<h; y++){
				for(int x=0; x<w; x++)
					str += rBoard2d[x][y];
				str += "\n";
			}
		}else {
			for(int y=0; y<h; y++){
				for(int x=0; x<w; x++)
					str += board2d[x][y];
				str += "\n";
			}
		}
		return str;
	}
	
	private boolean isTOP(ChessPiece p){
		return p.getPosition()==ChineseChessPieces.TOP;
	}
	
	private char reverseLetter(char ch) {
		if (ch>='A'&&ch<='Z') return (char)(ch+32); 
		if (ch>='a'&&ch<='z') return (char)(ch-32);
		return ch;
	}

	@Override
	public boolean isTOP(int iPosition) {
		return iPosition==ChineseChessPieces.TOP;
	}
	
}
