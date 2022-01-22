package com.rule;

import com.ChessBoard;
import com.Move;
import com.Point;
import com.Rect;

/*
 * RuleEngineVO used as a media to store and pass board / move information among Model View Controller Rule Engine etc.
 * 
 * */

public class RuleEngineVO {

	/*
	private int boardX1 = 1;
	private int boardY1 = 1;
	private int boardX2 = 7;
	private int boardY2 = 9;
	*/
	private Rect boardRect = null;
	private ChessBoard boardObj = null;
	private int races = 0;
	private Move move = null;
	//private char[][] arrBoard = null;
	private int boardWidth = 0;
	private int boardHeight = 0;
	private Point pt = null;
	
	public RuleEngineVO(ChessBoard board, Move move2) {
		this.setBoardObj(board);
		this.setMove(move2);
	}
	public RuleEngineVO(ChessBoard board) {
		this.setBoardObj(board);
	}
	public RuleEngineVO(ChessBoard board, int races2) {
		this.setBoardObj(board);
		this.setRaces(races2);
	}
	/*
	public RuleEngineVO(char[][] cBoard, int w, int h, int iRaces) {
		this.setArrBoard(cBoard);
		this.setBoardWidth(w);
		this.setBoardHeight(h);
		this.setRaces(iRaces);
	}
	public RuleEngineVO(char[][] cBoard, int w, int h) {
		this.setArrBoard(cBoard);
		this.setBoardWidth(w);
		this.setBoardHeight(h);
	}
	public RuleEngineVO(char[][] cBoard, int iRaces, Move move2) {
		this.setArrBoard(cBoard);
		this.setRaces(iRaces);
		this.setMove(move2);
	}
	public RuleEngineVO(char[][] cBoard, int w, int h, Move move2) {
		this.setArrBoard(cBoard);
		this.setBoardWidth(w);
		this.setBoardHeight(h);
		this.setMove(move2);
	}*/
	public Point getPt() {
		return pt;
	}
	public void setPt(Point pt) {
		this.pt = pt;
	}
	public Rect getBoardRect() {
		return boardRect;
	}
	public void setBoardRect(Rect boardRect) {
		this.boardRect = boardRect;
	}
	public ChessBoard getBoardObj() {
		return boardObj;
	}
	public void setBoardObj(ChessBoard boardObj) {
		this.boardObj = boardObj;
	}
	public int getRaces() {
		return races;
	}
	public void setRaces(int races) {
		this.races = races;
	}
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	/*
	public char[][] getArrBoard() {
		return arrBoard;
	}
	public void setArrBoard(char[][] arrBoard) {
		this.arrBoard = arrBoard;
	}*/
	public int getBoardWidth() {
		return boardWidth;
	}
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}
	public int getBoardHeight() {
		return boardHeight;
	}
	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}

}
