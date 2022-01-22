package com;

import java.util.List;

public abstract class ChessPiece {
	int id = 0; // A unique identifier inside ChessPieces Vector
	String name = "";
	int races = -1; // Determine which races this piece belongs to
	int x = 0;
	int y = 0;
	String word = "大";
	boolean flow = false;
	int flowX = 0;
	int flowY = 0;
	int flowZ = 0;
	int type = 0; // e.g. 0 - Elephant; 1 - Lion
	int position = 0; // e.g. TOP; BOTTOM
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public ChessPiece(String name, int races){
		this.name = name;
		this.races = races;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getFlowZ() {
		return flowZ;
	}

	public void setFlowZ(int flowZ) {
		this.flowZ = flowZ;
	}

	/*
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}*/
	public int getRaces() {
		return races;
	}
	public void setRaces(int races) {
		this.races = races;
	}
	public String toString(){
		return name;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}

	public boolean isFlow() {
		return flow;
	}

	public void setFlow(boolean flow) {
		this.flow = flow;
	}

	public int getFlowX() {
		//if (flowX==0) flowX = getGraphicalXByX(getX(), false);
		return flowX;
	}

	public void setFlowX(int flowX) {
		this.flowX = flowX;
	}

	public int getFlowY() {
		//if (flowY==0) flowY = getGraphicalYByY(getY(), false);
		return flowY;
	}

	public void setFlowY(int flowY) {
		this.flowY = flowY;
	}

	public boolean equals(ChessPiece p2){
		return getX() == p2.getX() && getY() == p2.getY();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	abstract public List<Move> getPossibleMoves(int fromX, int fromY);

	abstract public boolean isValidMove(ChessBoard cboard, int toX, int toY);

}
