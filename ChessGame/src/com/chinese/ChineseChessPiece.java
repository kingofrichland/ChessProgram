package com.chinese;

import java.util.List;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

public class ChineseChessPiece extends ChessPiece{
	
	
	public ChineseChessPiece(String name, int races){
		super( name, races);
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
