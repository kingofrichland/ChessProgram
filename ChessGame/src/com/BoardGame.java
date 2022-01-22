package com;

import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineForPlayer;


abstract public class BoardGame {
	
	ChessBoard chessBoard = null;
	ChessPieces chessPieces = null;
	Players players = null;
	RuleEngineForFramework ruleEngineForFramework = null;
	RuleEngineForPlayer ruleEngineForPlayer = null;
	
	public void startGame(){
		
		/*
		 *  New Method
		 *  1. create chess board
		 *  2. rule(s) should be attach to each chess piece
		 *  3. create players
		 *  4. create chess pieces
		 *  5. add pieces to chess board (How to add depend on chess board)
		 *  6. add pieces to players (How to add depend on players)
		 * */
		
		// 1
		chessBoard = prepareChessBoard();
		System.out.println("Prepared board: "+chessBoard);

		// 2
		ruleEngineForFramework = prepareRuleEngineForFramework(chessBoard);
		System.out.println("Prepared rule engine for framework: "+ruleEngineForFramework);
		ruleEngineForPlayer = prepareRuleEngineForPlayer(chessBoard);
		System.out.println("Prepared rule engine for player: "+ruleEngineForPlayer);
		
		// 3
		players = preparePlayers();
		System.out.println("Prepared players: "+players);
		
		// 4
		chessPieces = prepareChessPieces(players);
		System.out.println("Prepared pieces: "+chessPieces);
		
		// 5
		chessBoard.setChessPieces(chessPieces);
		
		// 6
		//players.addPieces(chessPieces);
		
	}

	/*
	public ChessBoard getReverseChessBoard() {
		ChessBoard newChessBoard = new ChessBoard("");
		Vector<ChessPiece> pieces = chessBoard.getChessPieces().getPieceList();
		Vector<ChessPiece> newPieces = new Vector<ChessPiece>();
		for(ChessPiece p: pieces){
			ChessPiece newP = p.clone();
			newP.setX(chessBoard.getWidth()-p.getX());
			newP.setY(chessBoard.getHeight()-p.getY());
			newPieces.add(newP);
		}
		ChessPieces newChessPieces = prepareChessPieces();
		newChessPieces.setPieceList(newPieces);
		newChessBoard.setChessPieces(newChessPieces);
		return newChessBoard;
	}*/
	
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(ChessBoard chessBoard) {
		this.chessBoard = chessBoard;
	}

	public ChessPieces getChessPieces() {
		return chessPieces;
	}

	public void setChessPieces(ChessPieces chessPieces) {
		this.chessPieces = chessPieces;
	}

	public Players getPlayers() {
		return players;
	}

	public void setPlayers(Players players) {
		this.players = players;
	}

	/*
	public RuleEngine getRuleEngine() {
		return ruleEngine;
	}

	public void setRuleEngine(RuleEngine ruleEngine) {
		this.ruleEngine = ruleEngine;
	}*/
	
	public RuleEngineForFramework getRuleEngineForFramework() {
		return ruleEngineForFramework;
	}

	public void setRuleEngineForFramework(
			RuleEngineForFramework ruleEngineForFramework) {
		this.ruleEngineForFramework = ruleEngineForFramework;
	}

	public RuleEngineForPlayer getRuleEngineForPlayer() {
		return ruleEngineForPlayer;
	}

	public void setRuleEngineForPlayer(RuleEngineForPlayer ruleEngineForPlayer) {
		this.ruleEngineForPlayer = ruleEngineForPlayer;
	}
	
	public String toString(){
		return getChessName();
	}
	
	abstract protected String getChessName();
	abstract protected ChessBoard prepareChessBoard();
	abstract protected ChessPieces prepareChessPieces(Players players);
	abstract protected Players preparePlayers();
	abstract protected RuleEngineForFramework prepareRuleEngineForFramework(ChessBoard chessBoard);
	abstract protected RuleEngineForPlayer prepareRuleEngineForPlayer(ChessBoard chessBoard);
	
}
