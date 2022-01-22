package com.player;

import com.BoardGame;
import com.ChessBoard;
import com.Move;
import com.Player;

public class HumanPlayer extends Player {

	public HumanPlayer(BoardGame game, int races, int position) {
		super("Human Player for "+game, game, races, position);
	}

	public HumanPlayer(String name, BoardGame game, int races, int position) {
		super(name, game, races, position);
	}
}
