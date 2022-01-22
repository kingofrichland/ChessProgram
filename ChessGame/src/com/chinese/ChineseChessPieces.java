package com.chinese;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import com.ChessPiece;
import com.ChessPieces;
import com.Player;
import com.Players;
import com.Point;

public class ChineseChessPieces extends ChessPieces {
	public static final int KING = 0;
	public static final int CHARIOT = 1;
	public static final int HORSE = 2;
	public static final int CANNON = 3;
	public static final int SERVANT = 4;
	public static final int CHANCELLOR = 5;
	public static final int PAWN = 6;

	// Races
	public static final int RED = 100;
	public static final int BLACK = 200;
	
	public static final int TOP = 300;
	public static final int BOTTOM = 400;
	
	private static final String[] CHESS_INITIAL_BOARD = new String[]{
			"...k.se..",
			".........",
			"tchse..ch",
			"p.p.pC...",
			".C...t...",
			"......p.P",
			"P.P.P...T",
			"..H......",
			".....K...",
			"T.ES.SE.H"
		};
	
	private static int[] TOP_CHESS_PIECE_IDX = new int[]{
			KING,
			CHARIOT,
			CHARIOT,
			HORSE,
			HORSE,
			CANNON,
			CANNON,
			SERVANT,
			SERVANT,
			CHANCELLOR,
			CHANCELLOR,
			PAWN,
			PAWN,
			PAWN,
			PAWN,
			PAWN
		};
	
	private static int[] BOTTOM_CHESS_PIECE_IDX = new int[]{
			KING,
			CHARIOT,
			CHARIOT,
			HORSE,
			HORSE,
			CANNON,
			CANNON,
			SERVANT,
			SERVANT,
			CHANCELLOR,
			CHANCELLOR,
			PAWN,
			PAWN,
			PAWN,
			PAWN,
			PAWN
		};
	
	private static Point[] TOP_CHESS_PIECE_POS = new Point[]{
		new Point(5,1),
		new Point(1,1),
		new Point(9,1),
		new Point(2,1),
		new Point(8,1),
		new Point(2,3),
		new Point(8,3),
		new Point(4,1),
		new Point(6,1),
		new Point(3,1),
		new Point(7,1),
		new Point(1,4),
		new Point(3,4),
		new Point(5,4),
		new Point(7,4),
		new Point(9,4)
	};
	private static Point[] BOTTOM_CHESS_PIECE_POS = new Point[]{
		new Point(5,10),
		new Point(1,10),
		new Point(9,10),
		new Point(2,10),
		new Point(8,10),
		new Point(2,8),
		new Point(8,8),
		new Point(4,10),
		new Point(6,10),
		new Point(3,10),
		new Point(7,10),
		new Point(1,7),
		new Point(3,7),
		new Point(5,7),
		new Point(7,7),
		new Point(9,7)
	};	
	
	public ChineseChessPieces(Players player){
		super("Chinese Chess Pieces", player);
	}

	public Vector<ChessPiece> initialized() {
		
		System.out.println("ChineseChessPieces.java - initialized");

		prepare_BOTTOM_CHESS_PIECE();
		
		Vector<ChessPiece> pieces = new Vector<ChessPiece>();
		Vector<Player> playerList = players.getPLayerList();
		for(int i=0; i<playerList.size(); i++){
			Player player = playerList.get(i);
			int[] CHESS_PIECE_IDX = (player.getPosition() == TOP) ? TOP_CHESS_PIECE_IDX : BOTTOM_CHESS_PIECE_IDX;
			Point[] CHESS_PIECE_POS = (player.getPosition() == TOP) ? TOP_CHESS_PIECE_POS : BOTTOM_CHESS_PIECE_POS;
			for(int j=0; j<CHESS_PIECE_IDX.length; j++){
				ChessPiece p = null;
				switch(CHESS_PIECE_IDX[j]){
				case KING: p = new King(player.getRaces()); break;
				case CHARIOT: p = new Chariot(player.getRaces()); break;
				case HORSE: p = new Horse(player.getRaces()); break;
				case CANNON: p = new Cannon(player.getRaces()); break;
				case SERVANT: p = new Servant(player.getRaces()); break;
				case CHANCELLOR: p = new Chancellor(player.getRaces()); break;
				case PAWN: p = new Pawn(player.getRaces()); break;
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
	
	private void prepare_BOTTOM_CHESS_PIECE() {
		ArrayList<Integer> arr_TOP_CHESS_PIECE_IDX = new ArrayList<Integer>();
		ArrayList<Point> arr_TOP_CHESS_PIECE_POS = new ArrayList<Point>();
		ArrayList<Integer> arr_BOTTOM_CHESS_PIECE_IDX = new ArrayList<Integer>();
		ArrayList<Point> arr_BOTTOM_CHESS_PIECE_POS = new ArrayList<Point>();
		for(int y=0; y<CHESS_INITIAL_BOARD.length; y++) {
			String line = CHESS_INITIAL_BOARD[y];
			for(int x=0; x<line.length(); x++) {
				char piece = CHESS_INITIAL_BOARD[y].charAt(x);
				switch(getRacesByPiece(piece)) {
				case TOP:
					arr_TOP_CHESS_PIECE_IDX.add(getPieceIndex(piece));
					arr_TOP_CHESS_PIECE_POS.add(new Point(x+1,y+1));
					break;
				case BOTTOM:
					arr_BOTTOM_CHESS_PIECE_IDX.add(getPieceIndex(piece));
					arr_BOTTOM_CHESS_PIECE_POS.add(new Point(x+1,y+1));
					break;
				}
			}
		}
		TOP_CHESS_PIECE_IDX = toIntArray(arr_TOP_CHESS_PIECE_IDX);
		TOP_CHESS_PIECE_POS = toPointArray(arr_TOP_CHESS_PIECE_POS);
		BOTTOM_CHESS_PIECE_IDX = toIntArray(arr_BOTTOM_CHESS_PIECE_IDX);
		BOTTOM_CHESS_PIECE_POS = toPointArray(arr_BOTTOM_CHESS_PIECE_POS);
	}
	
	private Point[] toPointArray(ArrayList<Point> pointArray) {
		return pointArray.toArray(new Point[pointArray.size()]);
	}

	private int[] toIntArray(ArrayList<Integer> integerArray) {
		int[] intarr = new int[integerArray.size()];
		for(int i=0; i<integerArray.size(); i++)
			intarr[i] = integerArray.get(i);
		return intarr;
	}
	
	private int getPieceIndex(char piece) {
		int r = 0;
		switch(piece){
		case 'K': 
		case 'k': r = KING; break;
		case 'T': 
		case 't': r = CHARIOT; break;
		case 'H': 
		case 'h': r = HORSE; break;
		case 'C': 
		case 'c': r = CANNON; break;
		case 'S': 
		case 's': r = SERVANT; break;
		case 'E': 
		case 'e': r = CHANCELLOR; break;
		case 'P': 
		case 'p': r = PAWN; break;
		}
		return r;
	}
	
	public int getRacesByPiece(char piece){
		int iRaces = 0;
		if (piece>='a' && piece<='z') iRaces = ChineseChessPieces.TOP;
		if (piece>='A' && piece<='Z') iRaces = ChineseChessPieces.BOTTOM;
		return iRaces;
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
