package com;

import java.util.Vector;

public class Move {

	ChessPiece chessPiece;
	int fromX = 0;
	int fromY = 0;
	int toX = 0;
	int toY = 0;
	int value = 0;
	Vector<Integer> vSuppInteger = null;
	String tableContent = "";
	
	/*
	 * This is used when those board game is purpose to move piece
	 * E.g. Chinese Chess / Animal Chess
	 * */
	public Move(ChessPiece chessPiece, int fromX, int fromY, int toX, int toY) {
		this.chessPiece = chessPiece;
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}
	
	/*
	 * This is used when those board game is purpose to place new piece, not move
	 * E.g. Reversi / 5 in a row
	 * */
    public Move(ChessPiece chessPiece, int toX, int toY) {
		this.chessPiece = chessPiece;
		this.toX = toX;
		this.toY = toY;
	}
    
    /*
     * This is used by player for no object consideration
     * */
	public Move(int fromX, int fromY, int toX, int toY) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}

	public int hashCode() {
        return fromX+fromY+toX+toY;
    }

    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Move))
            return false;

        Move mv = (Move) obj;
        return new EqualsBuilder().
            append(fromX, mv.fromX).
            append(fromY, mv.fromY).
            append(toX, mv.toX).
            append(toY, mv.toY).
            isEquals();
    }
    
    public String toString(){
    	return (chessPiece!=null?chessPiece.getWord():"")+" ("+fromX+", "+fromY+") -> ("+toX+", "+toY+")";
    }

	public ChessPiece getChessPiece() {
		return chessPiece;
	}

	public void setChessPiece(ChessPiece chessPiece) {
		this.chessPiece = chessPiece;
	}

	public int getFromX() {
		return fromX;
	}

	public void setFromX(int fromX) {
		this.fromX = fromX;
	}

	public int getFromY() {
		return fromY;
	}

	public void setFromY(int fromY) {
		this.fromY = fromY;
	}

	public int getToX() {
		return toX;
	}

	public void setToX(int toX) {
		this.toX = toX;
	}

	public int getToY() {
		return toY;
	}

	public void setToY(int toY) {
		this.toY = toY;
	}

	public Vector<Integer> getSuppInteger() {
		return vSuppInteger;
	}

	public void setSuppInteger(Vector<Integer> vSuppInteger) {
		this.vSuppInteger = vSuppInteger;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getTableContent() {
		return tableContent;
	}

	public void setTableContent(String tableContent) {
		this.tableContent = tableContent;
	}
	
	/*public void addTableContent(String tableContent) {
		this.tableContent += tableContent;
	}*/
	
}
