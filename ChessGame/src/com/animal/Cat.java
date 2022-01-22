package com.animal;

public class Cat extends AnimalChessPiece {

	public Cat(int races) {
		super("Cat", races);
		setWord("貓");
		setType(AnimalChessPieces.CAT);
	}

	/*
	public boolean isValidMove(ChessBoard board, int tox, int toy){
		boolean bIsInsideBoard = isInsideBoard(tox,toy);
		boolean bHasSelfPiece = hasSelfPiece(board, getRaces(), tox, toy);
		boolean bIsInsideRiver = isInsideRiver(tox,toy);
		return bIsInsideBoard && !bHasSelfPiece && !bIsInsideRiver;
	}*/

}
