package com.animal;

import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

public class AnimalChessBoard extends ChessBoard {

	public AnimalChessBoard() {
		super("Animal Chess Board", 7, 9);
		// TODO Auto-generated constructor stub
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
			case AnimalChessPieces.ELEPHANT: 	ch = isTOP(p)?'e':'E'; break;
			case AnimalChessPieces.LION: 		ch = isTOP(p)?'l':'L'; break;
			case AnimalChessPieces.TIGER: 		ch = isTOP(p)?'t':'T'; break;
			case AnimalChessPieces.CHEEROR: 	ch = isTOP(p)?'h':'H'; break;
			case AnimalChessPieces.WOLF: 		ch = isTOP(p)?'w':'W'; break;
			case AnimalChessPieces.DOG: 		ch = isTOP(p)?'d':'D'; break;
			case AnimalChessPieces.CAT: 		ch = isTOP(p)?'c':'C'; break;
			case AnimalChessPieces.MOUSE: 		ch = isTOP(p)?'m':'M'; break;
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
		return p.getPosition()==AnimalChessPieces.TOP;
	}
	
	private char reverseLetter(char ch) {
		if (ch>='A'&&ch<='Z') return (char)(ch+32); 
		if (ch>='a'&&ch<='z') return (char)(ch-32);
		return ch;
	}

	@Override
	public boolean isTOP(int iPosition) {
		return iPosition==AnimalChessPieces.TOP;
	}

}
