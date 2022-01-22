package com.chinese;

import com.BoardGame;
import com.ChessBoard;
import com.ChessPieces;
import com.Players;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineForPlayer;
import com.rule.RuleEngineVO;

public class ChineseChess extends BoardGame {

	public static String ChessInd = "chinese";
	
	protected ChessBoard prepareChessBoard() {
		return new ChineseChessBoard();
	}

	protected ChessPieces prepareChessPieces(Players players) {
		ChessPieces pieces = new ChineseChessPieces(players);
		return pieces;
	}

	protected Players preparePlayers() {
		return new ChineseChessPlayers(this);
	}

	/*
	protected RuleEngine prepareRuleEngine(ChessBoard board) {
		return new ChineseChessRuleEngine(new RuleEngineVO(board));
	}*/
	
	@Override
	protected RuleEngineForFramework prepareRuleEngineForFramework(
			ChessBoard chessBoard) {
		return new ChineseChessRuleEngineForFramework(new RuleEngineVO(chessBoard));
	}

	@Override
	protected RuleEngineForPlayer prepareRuleEngineForPlayer(
			ChessBoard chessBoard) {
		int w = chessBoard.getWidth();
		int h = chessBoard.getHeight();
		return new ChineseChessRuleEngineForPlayer(w,h);
	}


	@Override
	protected String getChessName() {
		// TODO Auto-generated method stub
		return ChessInd;
	}

}
