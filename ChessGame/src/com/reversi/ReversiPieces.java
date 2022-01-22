package com.reversi;

import java.awt.Color;
import java.util.Vector;

import com.ChessPiece;
import com.ChessPieces;
import com.Player;
import com.Players;
import com.Point;

public class ReversiPieces extends ChessPieces {

	private static final int PIECE = 0;
	
	public static final int WHITE = 100;
	public static final int BLACK = 200;

	public static final int TOP = 300;
	public static final int BOTTOM = 400;

	/*
	private static final int[] RACES = new int[]{
		WHITE,
		BLACK
	};

	private static final int[] POSITION = new int[]{
		TOP,
		BOTTOM
	};*/

	/*
	private static final int[] CHESS_PIECE_IDX = new int[]{
		PIECE,
		PIECE,
		PIECE,
		PIECE,
		PIECE,
		PIECE,
		PIECE,
		PIECE
	};

	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(1,8),
		new Point(2,7),
		new Point(3,6),
		new Point(4,5),
		new Point(5,4),
		new Point(6,3),
		new Point(7,2),
		new Point(8,1)
	};
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(1,1),
		new Point(2,2),
		new Point(3,3),
		new Point(4,4),
		new Point(5,5),
		new Point(6,6),
		new Point(7,7),
		new Point(8,8)
	};	
	*/
	
	/*
	private static final int[] CHESS_PIECE_IDX = new int[]{
		PIECE,
		PIECE,
		PIECE
	};
	
	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(5,3),
		new Point(4,5),
		new Point(5,4)
	};
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(4,3),
		new Point(4,4),
		new Point(5,5)
	};*/
	
	private static final int[] CHESS_PIECE_IDX = new int[]{
		PIECE,
		PIECE
	};
	
	private static final Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(4,5),
		new Point(5,4)
	};
	private static final Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(4,4),
		new Point(5,5)
	};	

	
	public ReversiPieces(Players player) {
		super("Reversi Pieces", player);
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
				case PIECE: p = new Piece(player.getRaces(),0,0); break;
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
			Point[] CHESS_PIECE_POS = (RACES[i] == WHITE) ? BOTTOM_CHESS_PIECE_POS : TOP_CHESS_PIECE_POS;
			for(int j=0; j<CHESS_PIECE_IDX.length; j++){
				ChessPiece p = null;
				switch(CHESS_PIECE_IDX[j]){
				case PIECE: p = new Piece(RACES[i],CHESS_PIECE_POS[j].getX(),CHESS_PIECE_POS[j].getY()); break;
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
		case WHITE: c = Color.WHITE; break;
		case BLACK: c = Color.BLACK; break;
		}
		return c;
	}
	

}
