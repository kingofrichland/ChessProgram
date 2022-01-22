package com.chinese;

import java.util.Vector;

import com.BoardGame;
import com.Player;
import com.Players;
import com.player.ExhaustiveSearchPlayer;
import com.player.MonteCarloPlayer;
import com.player.RandomPlayer;

public class ChineseChessPlayers extends Players{
	public ChineseChessPlayers(BoardGame game){
		super("Chinese Chess Players", game);
	}

	@Override
	public Vector<Player> initialized() {
		Vector<Player> players = new Vector<Player>();
		players.add(new MonteCarloPlayer("RED BOTTOM",getBoardGame(),ChineseChessPieces.RED,ChineseChessPieces.BOTTOM));
		players.add(new ExhaustiveSearchPlayer("BLACK TOP",getBoardGame(),ChineseChessPieces.BLACK,ChineseChessPieces.TOP));
		return players;
	}
}
