package com.rule;
import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;

/*
 * RuleEngine
 * RuleEngine should divided into 2 parts.
 * Part1, is a set of function that is rich of information, and is easily be used by BoardGameFramework
 * Part2, is a set of function that is not so rich of info, but uses integer, array, char etc to speed up the calculation process, 
 * and is used by players to calc their best move.
 * Some of the functions may be duplicate in 2 parts. However, as their purpose are different, they are target to be exist together
 * Both set of functions are stateless, which means caller should pass all the required information to the functions, and those functions 
 * will base on those parameter to return corresponding value. They will not "remember" board state 
 * */
abstract public class RuleEngineForFramework extends RuleEngine{

	protected int boardX1 = 0;
	protected int boardY1 = 0;
	protected int boardX2 = 0;
	protected int boardY2 = 0;
	
	/*
	
	public RuleEngine(ChessBoard board) {
		boardX2 = board.getWidth();
		boardY2 = board.getHeight();
	}

	abstract public Vector<Move> getValidMoves(ChessBoard board, int iRaces);	

	abstract public Vector<Move> getValidMoves(char[][] cBoard, int w, int h, int iRaces) ;
	
	abstract public int getNextRaces(int iRaces);

	public void printBoard(char[][] cBoard, int w, int h){
		
	}

	public Move supplementMoveInfo(char[][] cBoard, int iRaces, Move move){
		return move;
	}
	
	public boolean isValidMove(ChessBoard board, Move move) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isGameOver(ChessBoard board) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Move supplementMoveInfo(ChessBoard board, Move move){
		return move;
	}
	
	protected boolean hasSelfPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()==iRaces){
				return true;
		}
		return false;
	}
	
	protected boolean isInsideBoard(int x, int y) {
		return ((x >= boardX1 && x <= boardX2) && (y >= boardY1 && y <= boardY2));
	}

	protected boolean isBlank(ChessBoard board, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		return p==null;
	}

	protected boolean hasEnemyPiece(ChessBoard board, int iRaces, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()!=iRaces){
				return true;
		}
		return false;
	}*/

	public RuleEngineForFramework(RuleEngineVO vo) {
		ChessBoard board = vo.getBoardObj();
		boardX2 = board.getWidth();
		boardY2 = board.getHeight();
	}

	public void printBoard(RuleEngineVO vo){
		
	}

	public Move supplementMoveInfo(RuleEngineVO vo){
		Move move = vo.getMove();
		return move;
	}
	
	public boolean isValidMove(RuleEngineVO vo) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isGameOver(RuleEngineVO vo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean isInsideBoard(int x, int y) {
		return ((x >= boardX1 && x <= boardX2) && (y >= boardY1 && y <= boardY2));
	}

	/*
	protected boolean isBlank(ChessBoard board, int x, int y) {
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		return p==null;
	}*/

	protected boolean hasSelfPiece(RuleEngineVO vo){
		ChessBoard board = vo.getBoardObj();
		int iRaces = vo.getRaces();
		int x = vo.getPt().getX();
		int y = vo.getPt().getY();
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()==iRaces){
				return true;
		}
		return false;
	}

	protected boolean hasEnemyPiece(RuleEngineVO vo){
		ChessBoard board = vo.getBoardObj();
		int iRaces = vo.getRaces();
		int x = vo.getPt().getX();
		int y = vo.getPt().getY();
		ChessPiece p = board.getChessPieces().getPieceByXY(x,y);
		if (p!=null && p.getRaces()!=iRaces){
				return true;
		}
		return false;
	}

	
	public Vector<Move> getValidMoves(RuleEngineVO vo){
		System.err.println("getValidMoves not implemented");
		return null;
	}	
	
	
	abstract public int getNextRaces(int iRaces);

}
