package com.checker;

import com.BoardGame;
import com.ChessBoard;
import com.ChessPieces;
import com.Players;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineForPlayer;
import com.rule.RuleEngineVO;

public class Checker extends BoardGame {

	public static String ChessInd = "checker";
	
	@Override
	protected ChessBoard prepareChessBoard() {
		return new CheckerBoard();
	}

	@Override
	protected ChessPieces prepareChessPieces(Players players) {
		// TODO Auto-generated method stub
		return new CheckerPieces(players);
	}

	@Override
	protected Players preparePlayers() {
		// TODO Auto-generated method stub
		return new CheckerPlayers(this);
	}

	/*
	@Override
	protected RuleEngine prepareRuleEngine(ChessBoard board) {
		// TODO Auto-generated method stub
		return new CheckerRuleEngine(new RuleEngineVO(board));
	}*/
	
	@Override
	protected RuleEngineForFramework prepareRuleEngineForFramework(
			ChessBoard chessBoard) {
		return new CheckerRuleEngineForFramework(new RuleEngineVO(chessBoard));
	}

	@Override
	protected RuleEngineForPlayer prepareRuleEngineForPlayer(
			ChessBoard chessBoard) {
		int w = chessBoard.getWidth();
		int h = chessBoard.getHeight();
		return new CheckerRuleEngineForPlayer(w,h);
	}


	@Override
	protected String getChessName() {
		// TODO Auto-generated method stub
		return ChessInd;
	}


}
