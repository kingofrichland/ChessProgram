package com.animal;

import java.awt.Color;
import java.util.List;
import java.util.Vector;

import com.ChessPiece;
import com.ChessPieces;
import com.Move;
import com.Player;
import com.Players;
import com.Point;

public class AnimalChessPieces extends ChessPieces {

	public static final int ELEPHANT = 0;
	public static final int LION = 1;
	public static final int TIGER = 2;
	public static final int CHEEROR = 3;
	public static final int WOLF = 4;
	public static final int DOG = 5;
	public static final int CAT = 6;
	public static final int MOUSE = 7;
	
	public static final int RED = 100;
	public static final int BLACK = 200;

	public static final int TOP = 300;
	public static final int BOTTOM = 400;
	
	
	private static final int[] TOP_CHESS_PIECE_TYPE = new int[]{
		ELEPHANT,
		LION,
		TIGER,
		CHEEROR,
		WOLF,
		DOG,
		CAT,
		MOUSE
	};
	
	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(7,3),
		new Point(1,1),
		new Point(7,1),
		new Point(3,3),
		new Point(5,3),
		new Point(2,2),
		new Point(6,2),
		new Point(1,3)
	};
	
	private static final int[] BOTTOM_CHESS_PIECE_TYPE = new int[]{
		ELEPHANT,
		LION,
		TIGER,
		CHEEROR,
		WOLF,
		DOG,
		CAT,
		MOUSE
	};
	
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(1,7),
		new Point(7,9),
		new Point(1,9),
		new Point(5,7),
		new Point(3,7),
		new Point(6,8),
		new Point(2,8),
		new Point(7,7)
	};	
	
/*
 * Problem Board:
 * Animal Chess Board
7 9
.......
..L....
....c..
d..w...
......t
.......
...e.l.
.m.....
..T....

1;L; B-Move: (3,2) -> (4,2); Value: -222
1_16;w; T-Move: (4,4) -> (4,3); Value: 172
1_16_1;L; B-EatMove: (4,2) -> (4,3); Value: -172
 * */
	
	/*
	private static final int[] TOP_CHESS_PIECE_TYPE = new int[]{
		MOUSE,
		LION,
		ELEPHANT,
		TIGER,
		WOLF,
		DOG,
		CAT
	};
	
	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(2,8),
		new Point(6,7),
		new Point(4,7),
		new Point(7,5),
		new Point(4,4),
		new Point(1,4),
		new Point(5,3)
	};
	
	private static final int[] BOTTOM_CHESS_PIECE_TYPE = new int[]{
		LION,
		TIGER
	};
	
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(3,2),
		new Point(3,9)
	};	*/
	

	public AnimalChessPieces(Players p) {
		super("Animal Chess Pieces", p);
	}
	
	public Vector<ChessPiece> initialized() {

		Vector<ChessPiece> pieces = new Vector<ChessPiece>();
		Vector<Player> playerList = players.getPLayerList();
		for(int i=0; i<playerList.size(); i++){
			Player player = playerList.get(i);
			Point[] CHESS_PIECE_POS = (player.getPosition() == TOP) ? TOP_CHESS_PIECE_POS : BOTTOM_CHESS_PIECE_POS;
			int[] CHESS_PIECE_TYPE = (player.getPosition() == TOP) ? TOP_CHESS_PIECE_TYPE : BOTTOM_CHESS_PIECE_TYPE ;
			for(int j=0; j<CHESS_PIECE_TYPE.length; j++){
				ChessPiece p = null;
				switch(CHESS_PIECE_TYPE[j]){
				case ELEPHANT: p = new Elephant(player.getRaces()); break;
				case LION: p = new Lion(player.getRaces()); break;
				case TIGER: p = new Tiger(player.getRaces()); break;
				case CHEEROR: p = new Cheeror(player.getRaces()); break;
				case WOLF: p = new Wolf(player.getRaces()); break;
				case DOG: p = new Dog(player.getRaces()); break;
				case CAT: p = new Cat(player.getRaces()); break;
				case MOUSE: p = new Mouse(player.getRaces()); break;
				}
				if(p!=null){
					p.setX(CHESS_PIECE_POS[j].getX());
					p.setY(CHESS_PIECE_POS[j].getY());
					p.setRaces(player.getRaces());
					p.setPosition(player.getPosition());
					pieces.add(p);
				}
			}
		}
		
		
		return pieces;
	}
	
	public Color getColorByRaces(int iRaces) {
		Color c = Color.GRAY;
		switch(iRaces){
		case RED: c = Color.RED; break;
		case BLACK: c = Color.BLACK; break;
		}
		return c;
	}



	

}
