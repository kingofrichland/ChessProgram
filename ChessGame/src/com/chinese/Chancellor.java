package com.chinese;

import com.ChessPiece;

public class Chancellor extends ChineseChessPiece {
	public Chancellor(int racesId){
		super("Chancellor", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("象");
		}else{
			setWord("相");
		}
		setType(ChineseChessPieces.CHANCELLOR);
	}
}
