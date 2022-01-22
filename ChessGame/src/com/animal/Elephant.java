package com.animal;

import com.ChessPiece;

public class Elephant extends AnimalChessPiece {
	public Elephant(int racesId){
		super("Elephant", racesId);
		setWord("象");
		setType(AnimalChessPieces.ELEPHANT);
	}
}
