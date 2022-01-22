package com.reversi;

import java.util.Vector;

import com.BoardGame;
import com.Player;
import com.Players;
import com.animal.AnimalChessPieces;
import com.player.RandomPlayer;

public class ReversiPlayers extends Players {

	public ReversiPlayers(BoardGame game) {
		super("Reversi Chess Players", game);
	}

	@Override
	public Vector<Player> initialized() {
		Vector<Player> players = new Vector<Player>();
		players.add(new RandomPlayer("",getBoardGame(),ReversiPieces.BLACK,ReversiPieces.TOP));
		players.add(new RandomPlayer("",getBoardGame(),ReversiPieces.WHITE,ReversiPieces.BOTTOM));
		return players;
	}

}
