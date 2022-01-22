package com.checker;

import java.awt.Color;
import java.util.Vector;

import com.ChessPiece;
import com.ChessPieces;
import com.Player;
import com.Players;
import com.Point;
import com.animal.AnimalChessPieces;

public class CheckerPieces extends ChessPieces {

	private static final int PAWN = 0;
	private static final int KING = 1;
	
	public static final int RED = 100;
	public static final int BLACK = 200;

	public static final int TOP = 300;
	public static final int BOTTOM = 400;

	/*
	private static final int[] POSITION = new int[]{
		TOP,
		BOTTOM
	};
	private static final int[] RACES = new int[]{
		RED,
		BLACK
	};*/

	private static final int[] CHESS_PIECE_IDX = new int[]{
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN,
		PAWN
	};


	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(1,1),
		new Point(3,1),
		new Point(5,1),
		new Point(7,1),
		new Point(2,2),
		new Point(4,2),
		new Point(6,2),
		new Point(8,2),
		new Point(1,3),
		new Point(3,3),
		new Point(5,3),
		new Point(7,3)
	};
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(2,6),
		new Point(4,6),
		new Point(6,6),
		new Point(8,6),
		new Point(1,7),
		new Point(3,7),
		new Point(5,7),
		new Point(7,7),
		new Point(2,8),
		new Point(4,8),
		new Point(6,8),
		new Point(8,8)
	};	
	
	public CheckerPieces(Players player) {
		super("Checker Pieces", player);
	}
	
	public Vector<ChessPiece> initialized() {

		Vector<ChessPiece> pieces = new Vector<ChessPiece>();
		
		Vector<Player> playerList = players.getPLayerList();
		for(int i=0; i<playerList.size(); i++){
			Player player = playerList.get(i);
			Point[] CHESS_PIECE_POS = (player.getPosition() == TOP) ? TOP_CHESS_PIECE_POS : BOTTOM_CHESS_PIECE_POS;
			for(int j=0; j<CHESS_PIECE_IDX.length; j++){
				ChessPiece p = null;
				switch(CHESS_PIECE_IDX[j]){
				case PAWN: p = new Pawn(player.getRaces()); break;
				case KING: p = new King(player.getRaces()); break;
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

		/*
		for(int i=0; i<RACES.length; i++){
			Point[] CHESS_PIECE_POS = (RACES[i] == RED) ? BOTTOM_CHESS_PIECE_POS : TOP_CHESS_PIECE_POS;
			Color CHESS_PIECE_COLOR = (RACES[i] == RED) ? Color.RED : Color.BLACK;
			for(int j=0; j<CHESS_PIECE_IDX.length; j++){
				ChessPiece p = null;
				switch(CHESS_PIECE_IDX[j]){
				case PAWN: p = new Pawn(RACES[i]); break;
				case KING: p = new King(RACES[i]); break;
				}
				if(p!=null){
					p.setX(CHESS_PIECE_POS[j].getX());
					p.setY(CHESS_PIECE_POS[j].getY());
					p.setRaces(RACES[i]);
					pieces.add(p);
				}
			}
		}*/
		
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
