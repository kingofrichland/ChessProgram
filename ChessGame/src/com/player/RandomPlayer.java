package com.player;

import java.util.Scanner;
import java.util.Vector;

import com.BoardGame;
import com.Move;

public class RandomPlayer extends ComputerPlayer {

	public RandomPlayer(String name, BoardGame game, int races, int position) {
		super("Random Player for "+game+"("+name+")", game, races, position);
		// TODO Auto-generated constructor stub
	}

	/*
	@Override
	protected Move calcMove(ChessBoard board){
		Vector<Move> moves = engine.getValidMoves(new RuleEngineVO(board, getRaces()));
		int sz = moves.size();
		int idx = (int)(Math.random() * sz);
		Move randomMove = moves.get(idx);
		return randomMove;
	}*/

	@Override
	protected String calcMove(String strBoard) {
		System.out.println(strBoard);
		Scanner s = new Scanner(strBoard);
		String name = s.nextLine();//Name
		int w=s.nextInt();//W
		int h=s.nextInt();//H
		s.nextLine();
		System.out.println("name="+name);
		System.out.println("w="+w);
		System.out.println("h="+h);
		char[][] cBoard = new char[w][h];
		for(int y=0; y<h; y++){
			String line = s.nextLine();
			for(int x=0; x<w; x++){
				cBoard[x][y] = line.charAt(x);
			}
		}
		s.close();
		
		//Vector<Move> moves = engine.getValidMoves(cBoard, w, h, getRaces());
		Vector<Move> moves = engine.getValidMoves(cBoard, w, h);
		int sz = moves.size();
		int idx = (int)(Math.random() * sz);
		Move randomMove = moves.get(idx);
		String res = randomMove.getFromX()+" "+randomMove.getFromY()+" "+randomMove.getToX()+" "+randomMove.getToY();
		
		//String res = "7 9 7 8";
		System.out.println("move => "+res);
		return res;
	}

}
