package com.chinese;

import com.ChessPiece;

public class Horse extends ChineseChessPiece {
	public Horse(int racesId){
		super("Horse", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("馬");
		}else{
			setWord("傌");
		}
		setType(ChineseChessPieces.HORSE);
	}
}
