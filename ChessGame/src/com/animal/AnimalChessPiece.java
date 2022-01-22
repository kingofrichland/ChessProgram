package com.animal;

import java.util.List;
import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

public class AnimalChessPiece extends ChessPiece {

	public AnimalChessPiece(String name, int races) {
		super( name, races);
	}
	
	public List<Move> getPossibleMoves(int fromX, int fromY){
		List<Move> vList = new Vector<Move>();
		int[] dx = new int[]{1,0,-1,0};
		int[] dy = new int[]{0,1,0,-1};
		for(int d=0; d<dx.length; d++){
			int px = fromX;
			int py = fromY;
			int tox = px+dx[d];
			int toy = py+dy[d];
			vList.add(new Move(this, px, py, tox, toy));
		}
		return vList;
	}

	@Override
	public boolean isValidMove(ChessBoard cboard, int toX, int toY) {
		// TODO Auto-generated method stub
		return false;
	}

}
