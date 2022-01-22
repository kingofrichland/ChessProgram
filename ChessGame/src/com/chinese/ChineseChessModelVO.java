package com.chinese;

import com.ModelVO;

public class ChineseChessModelVO extends ModelVO {

	int x = 0;
	int y = 0;
	int tx = 0;
	int ty = 0;
	
	public ChineseChessModelVO() {
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setTargetXY(int x2, int y2) {
		tx = x2;
		ty = y2;
	}

	public int getTargetY() {
		return ty;
	}

	public int getTargetX() {
		return tx;
	}
}
