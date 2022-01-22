package com.chinese;

import com.ChessPiece;

public class King extends ChineseChessPiece {
	public King(int racesId){
		super("King", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("將");
		}else{
			setWord("帥");
		}
		setType(ChineseChessPieces.KING);
	}
}
