package com.animal;

import com.BoardGame;
import com.ChessBoard;
import com.ChessPieces;
import com.Players;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineForPlayer;
import com.rule.RuleEngineVO;

public class AnimalChess extends BoardGame {

	public static String ChessInd = "animal";
	
	@Override
	protected ChessBoard prepareChessBoard() {
		return new AnimalChessBoard();
	}

	@Override
	protected ChessPieces prepareChessPieces(Players players) {
		// TODO Auto-generated method stub
		return new AnimalChessPieces(players);
	}

	@Override
	protected Players preparePlayers() {
		// TODO Auto-generated method stub
		return new AnimalChessPlayers(this);
	}

	/*
	@Override
	protected RuleEngine prepareRuleEngine(ChessBoard board) {
		// TODO Auto-generated method stub
		return new AnimalChessRuleEngine(new RuleEngineVO(board));
	}*/

	@Override
	protected RuleEngineForFramework prepareRuleEngineForFramework(
			ChessBoard chessBoard) {
		return new AnimalChessRuleEngineForFramework(new RuleEngineVO(chessBoard));
	}

	@Override
	protected RuleEngineForPlayer prepareRuleEngineForPlayer(
			ChessBoard chessBoard) {
		int w = chessBoard.getWidth();
		int h = chessBoard.getHeight();
		return new AnimalChessRuleEngineForPlayer(w,h);
	}

	
	@Override
	protected String getChessName() {
		// TODO Auto-generated method stub
		return ChessInd;
	}



}
