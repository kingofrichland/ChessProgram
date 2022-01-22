package com;

import java.util.Vector;

abstract public class Player {
	String name = "";
	BoardGame game = null;
	Vector<ChessPiece> pieceList = null;
	Move move = null;
	int races = 0;
	int position = 0;
	
	public Player(String name, BoardGame game, int races, int position){
		this.name = name;
		this.game = game;
		this.races = races;
		this.position = position;
	}
	public String toString(){
		return name;
	}
	public void addPieceList(Vector<ChessPiece> pieceList) {
		this.pieceList = pieceList;
	}
	public Vector<ChessPiece> getPieceList() {
		return pieceList;
	}
	public void setPieceList(Vector<ChessPiece> pieceList) {
		this.pieceList = pieceList;
	}
	public void setMove(Move move) {
		this.move = move;
	}
	public Move getMove() {
		return move;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRaces() {
		return races;
	}
	public void setRaces(int races) {
		this.races = races;
	}
	public BoardGame getGame() {
		return game;
	}
	public void setGame(BoardGame game) {
		this.game = game;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
