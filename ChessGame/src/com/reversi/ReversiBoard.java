package com.reversi;

import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

public class ReversiBoard extends ChessBoard {

	public ReversiBoard() {
		super("Reversi Chess Board",8,8);
		// TODO Auto-generated constructor stub
	}
	
	public void takeMove(Move move) {
		// Get Moving Piece
		ChessPiece mPiece = move.getChessPiece();
		
		// Update New Piece
		getChessPieces().addPieceXY(mPiece, move.getToX(), move.getToY());
		
		// Update Related Eat Piece
		Vector<Integer> vList = move.getSuppInteger();
		for(Integer id : vList){
			getChessPieces().updatePieceRaces(id, mPiece.getRaces());
		}

	}

	@Override
	public String convertChessBoardToString(ChessBoard board2,
			boolean bToReverseBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTOP(int iPosition) {
		return iPosition==ReversiPieces.TOP;
	}


}
