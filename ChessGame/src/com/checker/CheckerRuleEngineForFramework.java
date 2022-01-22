package com.checker;

import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;
import com.rule.RuleEngine;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineVO;

public class CheckerRuleEngineForFramework extends RuleEngineForFramework {
	
	int[] dx = new int[]{-1, 1, 1,-1};
	int[] dy = new int[]{-1,-1, 1, 1};
	
	public CheckerRuleEngineForFramework(RuleEngineVO vo) {
		super(vo);
	}

	/*
	@Override
	public Vector<Move> getValidMoves(RuleEngineVO vo) {
		ChessBoard board = vo.getBoardObj();
		int iRaces = vo.getRaces();
		Vector<Move> vMove = new Vector<Move>();
		Vector<ChessPiece> pieces = board.getChessPieces().getPieceListByRaces(iRaces);
		for(ChessPiece p : pieces){
			for(int d=0; d<4; d++){
				// Check if exceed boundary
				int px = p.getX();
				int py = p.getY();
				int tox = px+dx[d];
				int toy = py+dy[d];
				boolean bIsInsideBoard = isInsideBoard(tox,toy);
				boolean bHasSelfPiece = hasSelfPiece(board, iRaces, tox, toy);
				
				if (bIsInsideBoard && !bHasSelfPiece){
					vMove.add(new Move(p,px,py,px+dx[d],py+dy[d]));
				}
			}
		}
		return vMove;
	}
	*/

	@Override
	public int getNextRaces(int iRaces) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected boolean hasSelfPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()==iRaces){
				return true;
		}
		return false;
	}

}
