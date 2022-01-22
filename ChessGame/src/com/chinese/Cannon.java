package com.chinese;

import com.ChessPiece;

public class Cannon extends ChineseChessPiece{
	public Cannon(int racesId){
		super("Cannon", racesId);
		if (racesId==ChineseChessPieces.BLACK){
			setWord("砲");
		}else{
			setWord("炮");
		}
		setType(ChineseChessPieces.CANNON);
	}
}
