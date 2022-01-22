package com;
import java.util.Vector;

import com.player.HumanPlayer;
import com.player.RandomPlayer;

abstract public class Players {
	String name = "";
	BoardGame game = null;
	Vector<Player> players = new Vector<Player>();
	ChessPieces chessPieces = null;
	int currPlayerIdx = 0;
	int numOfPlayers = 0;
	public Players(String name, BoardGame game){
		this.name = name;
		this.game = game;
		this.players = initialized();
		if (this.players==null) throw new IllegalStateException("ChessPlayers: "+name+" cannot be initialized");
		currPlayerIdx = 0;
		numOfPlayers = players.size();
		
	}
	
	public Vector<Player> getPLayerList(){
		return players;
	}
	
	public void addPieces(ChessPieces chessPieces) {
		this.chessPieces = chessPieces;
		if(chessPieces!=null){
			if(players!=null){
				for(Player p : players){
					p.addPieceList(chessPieces.getPieceListByPosition(p.getPosition()));
				}
			}
		}
		
	}

	public String toString(){
		String res = name;
		for(Player p: players){
			res += "\n Preparing "+p;
		}
		return res;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BoardGame getBoardGame() {
		return game;
	}
	public void setBoardGame(BoardGame game) {
		this.game = game;
	}

	public Player getCurrPlayer() {
		return players.get(currPlayerIdx);
	}

	public void turnToNextPlayer() {
		currPlayerIdx = (currPlayerIdx + 1) % numOfPlayers;
	}
	
	abstract public Vector<Player> initialized();


}