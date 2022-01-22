package com.chinese;

import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;
import com.animal.AnimalChessPieces;
import com.rule.RuleEngine;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineVO;

public class ChineseChessRuleEngineForFramework extends RuleEngineForFramework {

	int[] dx = new int[] { 1, 0, -1, 0 };
	int[] dy = new int[] { 0, 1, 0, -1 };

	public ChineseChessRuleEngineForFramework(RuleEngineVO vo) {
		super(vo);
	}

	@Override
	public boolean isGameOver(RuleEngineVO vo) {
		ChessBoard board = vo.getBoardObj();
		int enemyBaseX1 = 4;
		int enemyBaseX2 = 6;
		int enemyBaseY1 = 1;
		int enemyBaseY2 = 3;
		boolean bHasEnemyKing = false;
		board.getChessPieces().getPieceList().stream().map(p->{
			System.out.println(p);
			return p;
		}
		).collect(Collectors.toList());
		l1 : for(int y=enemyBaseY1; y<=enemyBaseY2; y++){
			for(int x=enemyBaseX1; x<=enemyBaseX2; x++){
				if (isBlank(board, x, y)) continue;
				ChessPiece p = board.getChessPieces().getPieceByXY(x, y);
				if (p instanceof King) {
					bHasEnemyKing = true;
					break l1;
				}
			}
		}
		int selfBaseX1 = 4;
		int selfBaseX2 = 6;
		int selfBaseY1 = 8;
		int selfBaseY2 = 10;
		boolean bHasSelfKing = false;
		l2 : for(int y=selfBaseY1; y<=selfBaseY2; y++){
			for(int x=selfBaseX1; x<=selfBaseX2; x++){
				if (isBlank(board, x, y)) continue;
				ChessPiece p = board.getChessPieces().getPieceByXY(x, y);
				if (p instanceof King) {
					bHasSelfKing = true;
					break l2;
				}
			}
		}
		return (!bHasEnemyKing || !bHasSelfKing);
	}
		
	private boolean isBlank(ChessBoard board, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x, y);
		return p==null;
	}

	
	
	
	@Override
	public Vector<Move> getValidMoves(RuleEngineVO vo) {
		ChessBoard board = vo.getBoardObj();
		int w = vo.getBoardWidth();
		int h = vo.getBoardHeight();
		char[][] cboard = new char[w][h];
		int iRaces = vo.getRaces();
		Vector<Move> vMove = new Vector<Move>();

		for (int y = 1; y <= h; y++) {
			for (int x = 1; x <= w; x++) {
				if (isBlank(board, x, y))
					continue;
				if (!(hasSelfPiece(board, x, y, iRaces)))
					continue;
				l2: for (int d = 0; d < dx.length; d++) {
					int px = x;
					int py = y;
					int tox = px + dx[d];
					int toy = py + dy[d];
					boolean bIsInsideBoard = isInsideBoard(tox, toy);
					if (!bIsInsideBoard)
						continue l2;
					boolean bHasSelfPiece = hasSelfPiece(board, tox, toy, iRaces);
					if (bHasSelfPiece)
						continue l2;
					boolean bHasEnemyPiece = hasEnemyPiece(board, tox, toy, iRaces);

					switch (cboard[px - 1][py - 1]) {
					case 'P': // ChineseChessPieces.PAWN:
					case 'p': // ChineseChessPieces.PAWN:
						if (!bHasEnemyPiece) {
							vMove.add(new Move(px, py, tox, toy));
						}
						break;
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

	protected boolean hasSelfPiece(ChessBoard board, int x, int y, int iRaces) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x, y);
		if (p != null && p.getRaces() == iRaces) {
			return true;
		}
		return false;
	}
	
	protected boolean hasEnemyPiece(ChessBoard board, int x, int y, int iRaces) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x, y);
		if (p != null && p.getRaces() == getNextRaces(iRaces)) {
			return true;
		}
		return false;
	}

	//private boolean hasEnemyPiece(char[][] cboard, int x, int y, int iRaces) {
	//	return (getRacesByPiece(cboard, x, y) == getNextRaces(iRaces));
	//}

	//private boolean isBlank(char[][] cboard, int x, int y) {
	//	return (cboard[x - 1][y - 1] == '.');
	//}

	//private boolean hasSelfPiece(char[][] cboard, int x, int y, int iRaces) {
	//	return (getRacesByPiece(cboard, x, y) == iRaces);
	//}

	/*private int getRacesByPiece(char[][] cboard, int x, int y) {
		int iRaces = 0;
		if (cboard[x - 1][y - 1] >= 'a' && cboard[x - 1][y - 1] <= 'z')
			iRaces = AnimalChessPieces.RED;
		if (cboard[x - 1][y - 1] >= 'A' && cboard[x - 1][y - 1] <= 'Z')
			iRaces = AnimalChessPieces.BLACK;
		return iRaces;
	}*/
	
	@Override
	public Move supplementMoveInfo(RuleEngineVO vo) {
		ChessBoard boardObj = vo.getBoardObj();
		int iRaces = vo.getRaces();
		Move move = vo.getMove();
		Vector<Integer> vList = null;
		if (boardObj!=null){
			vList = getEatPieceList(boardObj, iRaces, move);
		}
		move.setSuppInteger(vList);
		return move;
	}
	
	private Vector<Integer> getEatPieceList(ChessBoard boardObj, int iRaces, Move move) {
		Vector<Integer> vResult = new Vector<Integer>();
		// Check Existing Piece
		//char oPiece = cBoard[move.getToX()-1][move.getToY()-1];
		ChessPiece oPiece = boardObj.getChessPieces().getPieceByXY(move.getToX(),move.getToY());
		if(oPiece!=null){
			vResult.add(oPiece.getId());
		}
		return vResult;
	}

}
