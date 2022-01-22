package com.animal;

import com.ChessPiece;

public class Lion extends AnimalChessPiece {

	public Lion(int racesId) {
		super("Lion", racesId);
		setWord("獅");
		setType(AnimalChessPieces.LION);
	}

}
