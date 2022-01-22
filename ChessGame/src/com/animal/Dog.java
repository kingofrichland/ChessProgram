package com.animal;

import com.ChessPiece;

public class Dog extends AnimalChessPiece {

	public Dog(int racesId) {
		super("Dog", racesId);
		setWord("狗");
		setType(AnimalChessPieces.DOG);
	}

}
