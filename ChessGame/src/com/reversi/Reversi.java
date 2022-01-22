package com.reversi;

import com.BoardGame;
import com.ChessBoard;
import com.ChessPieces;
import com.Players;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineForPlayer;
import com.rule.RuleEngineVO;

public class Reversi extends BoardGame {

	public static String ChessInd = "reversi";
	
	@Override
	protected ChessBoard prepareChessBoard() {
		return new ReversiBoard();
	}

	@Override
	protected ChessPieces prepareChessPieces(Players players) {
		// TODO Auto-generated method stub
		return new ReversiPieces(players);
	}

	@Override
	protected Players preparePlayers() {
		// TODO Auto-generated method stub
		return new ReversiPlayers(this);
	}

	/*
	@Override
	protected RuleEngine prepareRuleEngine(ChessBoard board) {
		// TODO Auto-generated method stub
		return new ReversiRuleEngine(new RuleEngineVO(board));
	}*/
	
	@Override
	protected RuleEngineForFramework prepareRuleEngineForFramework(
			ChessBoard chessBoard) {
		return new ReversiRuleEngineForFramework(new RuleEngineVO(chessBoard));
	}

	@Override
	protected RuleEngineForPlayer prepareRuleEngineForPlayer(
			ChessBoard chessBoard) {
		int w = chessBoard.getWidth();
		int h = chessBoard.getHeight();
		return new ReversiRuleEngineForPlayer(w,h);
	}


	@Override
	protected String getChessName() {
		// TODO Auto-generated method stub
		return ChessInd;
	}


}
