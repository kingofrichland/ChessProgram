package com.animal;

import java.util.Vector;

import com.BoardGame;
import com.Player;
import com.Players;
import com.player.ExhaustiveSearchPlayer;
import com.player.HumanPlayer;
import com.player.RandomPlayer;

public class AnimalChessPlayers extends Players {

	public AnimalChessPlayers(BoardGame game) {
		super("Animal Chess Players", game);
	}

	@Override
	public Vector<Player> initialized() {
		Vector<Player> players = new Vector<Player>();
		//players.add(new ExhaustiveSearchPlayer("RED BOTTOM",getBoardGame(),AnimalChessPieces.RED,AnimalChessPieces.BOTTOM));
		//players.add(new RandomPlayer("BLACK TOP",getBoardGame(),AnimalChessPieces.BLACK,AnimalChessPieces.TOP));
		//players.add(new RandomPlayer("RED BOTTOM",getBoardGame(),AnimalChessPieces.RED,AnimalChessPieces.BOTTOM));
		//players.add(new ExhaustiveSearchPlayer("BLACK TOP",getBoardGame(),AnimalChessPieces.BLACK,AnimalChessPieces.TOP));
		players.add(new ExhaustiveSearchPlayer("RED BOTTOM",getBoardGame(),AnimalChessPieces.RED,AnimalChessPieces.BOTTOM));
		players.add(new ExhaustiveSearchPlayer("BLACK TOP",getBoardGame(),AnimalChessPieces.BLACK,AnimalChessPieces.TOP));
		return players;
	}

}
