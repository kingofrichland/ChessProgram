package com.checker;

import java.util.Vector;

import com.BoardGame;
import com.Player;
import com.Players;
import com.player.HumanPlayer;
import com.player.RandomPlayer;

public class CheckerPlayers extends Players {

	public CheckerPlayers(BoardGame game) {
		super("Animal Chess Players", game);
	}

	@Override
	public Vector<Player> initialized() {
		Vector<Player> players = new Vector<Player>();
		players.add(new HumanPlayer(getBoardGame(),CheckerPieces.RED,CheckerPieces.TOP));
		players.add(new RandomPlayer("",getBoardGame(),CheckerPieces.BLACK,CheckerPieces.BOTTOM));
		return players;
	}

}
