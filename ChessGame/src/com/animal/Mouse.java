package com.animal;

import com.ChessPiece;

public class Mouse extends AnimalChessPiece {

	public Mouse(int racesId) {
		super("Mouse", racesId);
		setWord("鼠");
		setType(AnimalChessPieces.MOUSE);
	}

}
