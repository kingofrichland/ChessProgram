package com.chinese;

import com.ChessPiece;

public class Pawn extends ChineseChessPiece {
	public Pawn(int racesId){
		super("Pawn", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("卒");
		}else{
			setWord("兵");
		}
		setType(ChineseChessPieces.PAWN);
	}
}
