package com.reversi;

import java.util.Vector;

import com.Move;
import com.rule.RuleEngineForPlayer;

public class ReversiRuleEngineForPlayer extends RuleEngineForPlayer {
	
	int[] dx = new int[]{ 0, 1, 1, 1, 0,-1,-1,-1};
	int[] dy = new int[]{-1,-1, 0, 1, 1, 1, 0,-1};
	
	public ReversiRuleEngineForPlayer(int w, int h) {
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
		
		l1: for(int px=1; px<=8; px++){
			l2: for(int py=1; py<=8; py++){
				if (!isBlank(cboard, px, py)) continue;
				l3: for(int d=0; d<8; d++){
					int tox = px;
					int toy = py;
					int cnt = 0;
					do {
						tox += dx[d];
						toy += dy[d];
						cnt++;//count if 2 self pieces has at least 1 enemy piece
					} while (isInsideBoard(tox, toy) && hasEnemyPiece(cboard, iRaces, tox, toy));
					if (isInsideBoard(tox, toy) && hasSelfPiece(cboard, iRaces, tox, toy) && cnt>=2){
						vMove.add(new Move(new Piece(iRaces,px,py), px,py));
						//System.out.printf("isBlank: (%d,%d) \t= %s\n", px,py,(isBlank(board, px, py)?"T":"F"));
						//System.out.printf("isInsideBoard: (%d,%d) \t= %s\n", tox,toy,(isInsideBoard(tox, toy)?"T":"F"));
						//System.out.printf("hasEnemyPiece: (%d,%d) \t= %s\n", tox,toy,(hasEnemyPiece(board, iRaces, tox, toy)?"T":"F"));
						//System.out.printf("hasSelfPiece: (%d,%d) \t= %s\n", tox,toy,(hasSelfPiece(board, iRaces, tox, toy)?"T":"F"));
						System.out.printf("Add Valid Move for %d: (%d,%d)\n", iRaces,px,py);
						break l3;
					}
				}
			}
		}
		return vMove;
	}

	/*
	public Vector<Integer> getEatPieceList(ChessBoard board, int iRaces, Move move) {
		Vector<Integer> vResult = new Vector<Integer>();
		int px = move.getToX();
		int py = move.getToY();

		for(int d=0; d<8; d++){
			Vector<Integer> vTmp = new Vector<Integer>();
			int tox = px;
			int toy = py;
			while (true) {
				tox += dx[d];
				toy += dy[d];
				if (isInsideBoard(tox, toy) && hasEnemyPiece(board, iRaces, tox, toy)){
					ChessPiece p2 = board.getChessPieces().getPieceByXY(tox,toy);
					vTmp.add(p2.getId());
				}else{
					break;
				}
			} 
			if (isInsideBoard(tox, toy) && hasSelfPiece(board, iRaces, tox, toy)){
				vResult.addAll(vTmp);
			}
		}
		return vResult;
	}
		
	public Move supplementMoveInfo(ChessBoard board, Move move){
		ChessPiece p = move.getChessPiece();
		Vector<Integer> vList = getEatPieceList(board, p.getRaces(), move);
		move.setSuppInteger(vList);
		return move;
	}*/

	@Override
	public int getNextRaces(int iRaces) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	private boolean hasSelfPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()==iRaces){
				return true;
		}
		return false;
	}

	private boolean isBlank(ChessBoard board, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		return p==null;
	}

	private boolean hasEnemyPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()!=iRaces){
				return true;
		}
		return false;
	}*/
	
	@Override
	public int getRacesByPiece(char[][] cboard, int x, int y){
		int iRaces = 0;
		if (cboard[x-1][y-1]>='a' && cboard[x-1][y-1]<='z') iRaces = ReversiPieces.WHITE;
		if (cboard[x-1][y-1]>='A' && cboard[x-1][y-1]<='Z') iRaces = ReversiPieces.BLACK;
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
