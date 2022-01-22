package com.checker;

import java.util.Vector;

import com.Move;
import com.rule.RuleEngineForPlayer;

public class CheckerRuleEngineForPlayer extends RuleEngineForPlayer {
	
	int[] dx = new int[]{-1, 1, 1,-1};
	int[] dy = new int[]{-1,-1, 1, 1};
	
	public CheckerRuleEngineForPlayer(int w, int h) {
		super(w,h);
	}

	@Override
	public int evaluate(char[][] cBoard, int w, int h, int iRaces) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector<Move> getValidMoves(char[][] cboard, int w, int h, int iRaces) {
		Vector<Move> vMove = new Vector<Move>();
		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				if (isBlank(cboard, x, y)) continue;
				if (!(hasSelfPiece(cboard, x, y, iRaces))) continue;
				l2: for(int d=0; d<dx.length; d++){
					int px = x;
					int py = y;
					int tox = px+dx[d];
					int toy = py+dy[d];
					boolean bIsInsideBoard = isInsideBoard(tox,toy);
					boolean bHasSelfPiece = hasSelfPiece(cboard, iRaces, tox, toy);
					
					if (bIsInsideBoard && !bHasSelfPiece){
						vMove.add(new Move(px,py,px+dx[d],py+dy[d]));
					}
				}
			}
		}
		return vMove;
	}

	@Override
	public int getNextRaces(int iRaces) {
		// TODO Auto-generated method stub
		return 0;
	}


	/*
	protected boolean hasSelfPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()==iRaces){
				return true;
		}
		return false;
	}*/
	
	@Override
	public int getRacesByPiece(char[][] cboard, int x, int y){
		int iRaces = 0;
		if (cboard[x-1][y-1]>='a' && cboard[x-1][y-1]<='z') iRaces = CheckerPieces.RED;
		if (cboard[x-1][y-1]>='A' && cboard[x-1][y-1]<='Z') iRaces = CheckerPieces.BLACK;
		return iRaces;
	}

	@Override
	public Vector<Move> getValidMoves(char[][] cBoard, int w, int h) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int evaluate(char[][] cBoard, int w, int h) {
		// TODO Auto-generated method stub
		return 0;
	}


}
