package com.reversi;

import java.util.List;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

public class Piece extends ChessPiece {
	
	public Piece(int races, int x, int y) {
		super("Piece", races);
		setX(x);
		setX(y);
	}

	@Override
	public List<Move> getPossibleMoves(int fromX, int fromY) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidMove(ChessBoard cboard, int toX, int toY) {
		// TODO Auto-generated method stub
		return false;
	}

}
