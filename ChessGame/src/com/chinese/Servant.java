package com.chinese;

import com.ChessPiece;

public class Servant extends ChineseChessPiece {
	public Servant(int racesId){
		super("Servant", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("士");
		}else{
			setWord("仕");
		}
		setType(ChineseChessPieces.SERVANT);
	}
}
