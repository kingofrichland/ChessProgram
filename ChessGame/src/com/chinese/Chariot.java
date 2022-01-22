package com.chinese;

import com.ChessPiece;

public class Chariot extends ChineseChessPiece {
	public Chariot(int racesId){
		super("Chariot", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("車");
		}else{
			setWord("車");
		}
		setType(ChineseChessPieces.CHARIOT);
	}
}
