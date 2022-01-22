package com.checker;

import com.ChessBoard;

public class CheckerBoard extends ChessBoard {

	public CheckerBoard() {
		super("Checker Chess Board",8,8);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String convertChessBoardToString(ChessBoard board2,
			boolean bToReverseBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTOP(int iPosition) {
		return iPosition==CheckerPieces.TOP;
	}

}
