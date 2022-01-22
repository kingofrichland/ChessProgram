package com;

public class BoardGameCafe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Welcome to BoardGameCafe!");
		String boardgame = "chinese";
		if (args.length>0) {
			System.out.println("First Param: "+args[0]);
			boardgame = args[0];
		}
		BoardGameFactory factory = new BoardGameFactory(); 
		factory.createBoardGameController(boardgame);
		
	}
	

}
