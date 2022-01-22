package com.rule;
import java.util.Arrays;
import java.util.Vector;

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
abstract public class RuleEngineForPlayer extends RuleEngine {

	protected int boardX1 = 1;
	protected int boardY1 = 1;
	protected int boardX2 = 7;
	protected int boardY2 = 9;
	
	protected String selfPieceCalculation = "";
	protected String selfPositionCalculation = "";
	protected String selfPieceValue = "";
	protected String selfPositionValue = "";
	
	protected String enemyPieceCalculation = "";
	protected String enemyPositionCalculation = "";
	protected String enemyPieceValue = "";
	protected String enemyPositionValue = "";

	protected String totalCalculation = "";
	protected String totalValue = "";
	
	public RuleEngineForPlayer(int w, int h) {
		boardX2 = w;
		boardY2 = h;
	}
	

	public boolean isBlank(char[][] cboard, int x, int y) {
		return (cboard[x-1][y-1]=='.');
	}
	
	public boolean isInsideBoard(int x, int y) {
		return ((x >= boardX1 && x <= boardX2) && (y >= boardY1 && y <= boardY2));
	}
	
	public void printBoard(char[][] cBoard, int w, int h){
		System.out.println(boardToString(cBoard, w, h));
	}
	
	public String boardToString(char[][] cBoard, int w, int h){
		String str = "";
		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				str += cBoard[x-1][y-1];
			}
			str += "\n";
		}
		return str;
	}

	
	public char[][] rotateBoard(char[][] board2d, int w, int h){
		char[][] rBoard2d = new char[w][h];
		for(int y=0; y<h; y++)
			for(int x=0; x<w; x++)
				rBoard2d[x][y] = reverseLetter(board2d[w-x-1][h-y-1]);
		return rBoard2d;
	}
	
	public Move rotateMove(Move move, int w, int h) {
		int fmX = move.getFromX();
		int fmY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		fmX = w - fmX + 1;
		fmY = h - fmY + 1;
		toX = w - toX + 1;
		toY = h - toY + 1;
		Move newMove = new Move(fmX,fmY,toX,toY);
		newMove.setValue(move.getValue());
		return newMove;
	}
	
	private char reverseLetter(char ch) {
		if (ch>='A'&&ch<='Z') return (char)(ch+32); 
		if (ch>='a'&&ch<='z') return (char)(ch-32);
		return ch;
	}
	
	// No Races Set
	public boolean hasEnemyPiece(char[][] cboard, int x, int y) {
		char ch = cboard[x-1][y-1];
		return (ch>='a'&&ch<='z');
	}

	public boolean hasSelfPiece(char[][] cboard, int x, int y) {
		char ch = cboard[x-1][y-1];
		return (ch>='A'&&ch<='Z');
	}
	
	public boolean hasPiece(char[][] cboard, int x, int y) {
		return (cboard[x-1][y-1]!='.');
	}

	public Move supplementMoveInfo(char[][] cBoard, Move move){
		return move;
	}

	// With Races Set
	public boolean hasEnemyPiece(char[][] cboard, int x, int y, int iRaces) {
		return (getRacesByPiece(cboard,x,y)==getNextRaces(iRaces));
	}

	public boolean hasSelfPiece(char[][] cboard, int x, int y, int iRaces) {
		return (getRacesByPiece(cboard,x,y)==iRaces);
	}
	
	public Move supplementMoveInfo(char[][] cBoard, int iRaces, Move move){
		return move;
	}

	public char[][] tryMove(char[][] cBoard, int w, int h, Move move) {
		
		int fmX = move.getFromX();
		int fmY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		
		// Update Moving Piece
		cBoard[toX-1][toY-1] = cBoard[fmX-1][fmY-1];
		cBoard[fmX-1][fmY-1] = '.';
		
		return cBoard;
	}

	public char[][] unTryMove(char[][] cBoard, int w, int h, Move move, char eatenPiece) {
		
		int fmX = move.getFromX();
		int fmY = move.getFromY();
		int toX = move.getToX();
		int toY = move.getToY();
		
		// Update Moving Piece
		cBoard[fmX-1][fmY-1] = cBoard[toX-1][toY-1];
		cBoard[toX-1][toY-1] = eatenPiece;

		return cBoard;
	}
	
	public char getEatenPieceFromMove(char[][] cBoard, int w, int h, Move move) {
		int toX = move.getToX();
		int toY = move.getToY();
		return cBoard[toX-1][toY-1];
	}
	
	// This method will help to break the connection of the caller, so that the cBoard change will not affect the original caller
	public char[][] cloneBoard(char[][] cBoard, int w, int h) {
		char[][] newBoard = new char[w][h];
		for(int x=0; x<w; x++)
			for(int y=0; y<h; y++)
				newBoard[x][y] = cBoard[x][y];
		return newBoard;
	}
	
	// No Races Set, assume top is enemy, bottom is self
	abstract public Vector<Move> getValidMoves(char[][] cBoard, int w, int h) ;
	
	abstract public int evaluate(char[][] cBoard, int w, int h);
	
	public Vector<Move> getValidEats(char[][] cBoard, int w, int h) {
		Vector<Move> vMoves = getValidMoves( cBoard, w, h);
		Vector<Move> vEats = new Vector<Move>();
		if(vMoves==null) return vEats;
		for(Move m: vMoves){
			if (hasEnemyPiece( cBoard, m.getToX(), m.getToY())) {
				vEats.add(m);
			}
		}
		return vEats;
	}

	public Vector<Move> getValidWinMoves(char[][] cBoard, int w, int h) {
		return null;
	}
	
	// With Races Set. Depreicated. PLease use getValidMoves without race
	public Vector<Move> getValidMoves(char[][] cBoard, int w, int h, int iRaces) {
		return null;
	}

	public int evaluate(char[][] cBoard, int w, int h, int iRaces){
		return 0;
	}

	abstract public int getNextRaces(int iRaces);
	
	abstract public int getRacesByPiece(char[][] cboard, int x, int y);

	public String selfPieceCalculationToString(char[][] cBoard, int w, int h) {
		return selfPieceCalculation;
	}

	public String selfPieceValueToString(char[][] cBoard, int w, int h) {
		return selfPieceValue;
	}


	public String enemyPieceCalculationToString(char[][] cBoard, int w, int h) {
		return enemyPieceCalculation;
	}


	public String enemyPieceValueToString(char[][] cBoard, int w, int h) {
		return enemyPieceValue;
	}


	public String selfPositionCalculationToString(char[][] cBoard, int w, int h) {
		return selfPositionCalculation;
	}


	public String selfPositionValueToString(char[][] cBoard, int w, int h) {
		return selfPositionValue;
	}


	public String enemyPositionCalculationToString(char[][] cBoard, int w, int h) {
		return enemyPositionCalculation;
	}


	public String enemyPositionValueToString(char[][] cBoard, int w, int h) {
		return enemyPositionValue;
	}


	public String totalCalculationToString(char[][] cBoard, int w, int h) {
		return totalCalculation;
	}


	public String totalValueToString(char[][] cBoard, int w, int h) {
		return totalValue;
	}


	public boolean isWin(char[][] cBoard, int w, int h) {
		// TODO Auto-generated method stub
		return false;
	}


	public int getWinValue() {
		// TODO Auto-generated method stub
		return 1000;
	}


	

}
