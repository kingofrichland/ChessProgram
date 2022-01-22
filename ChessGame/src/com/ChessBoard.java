package com;

import java.util.Scanner;


abstract public class ChessBoard {
	String name = "";
	ChessPieces chessPieces = null;
	int width = 0;
	int height = 0;
	public ChessPieces getChessPieces() {
		return chessPieces;
	}
	public void setChessPieces(ChessPieces chessPieces) {
		this.chessPieces = chessPieces;
	}
	public ChessBoard(String name, int width, int height){
		this.name = name;
		this.width = width;
		this.height = height;
	}
	public String toString(){
		return name;
	}
	
	public void takeMove(Move move) {
		// Check Existing Piece
		ChessPiece oPiece = chessPieces.getPieceByXY(move.getToX(), move.getToY());
		// Get Moving Piece
		ChessPiece mPiece = move.getChessPiece();
		
		// Update Moving Piece
		chessPieces.updatePieceXY(mPiece.getId(), move.getToX(), move.getToY());

		// Support eat move, so will delete the existing piece
		if (oPiece!=null){
			chessPieces.deletePiece(oPiece.getId()); // Eat take place
		}
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Move convertStringToMove(ChessBoard board2, String sMove, boolean bToReverseMove) {
		Scanner s = new Scanner(sMove);
		int fmX = s.nextInt();
		int fmY = s.nextInt();
		int toX = s.nextInt();
		int toY = s.nextInt();
		s.close();
		if (bToReverseMove) {
			int w = board2.getWidth();
			int h = board2.getHeight();
			fmX = w - fmX + 1;
			fmY = h - fmY + 1;
			toX = w - toX + 1;
			toY = h - toY + 1;
		}
		ChessPiece p = chessPieces.getPieceByXY(fmX,fmY);
		return new Move(p,fmX,fmY,toX,toY);
	}
	
	abstract public String convertChessBoardToString(ChessBoard board2, boolean bToReverseBoard);
	abstract public boolean isTOP(int iPosition);
}
