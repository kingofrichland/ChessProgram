package com.player;

import com.BoardGame;
import com.ChessBoard;
import com.Move;
import com.Player;
import com.rule.RuleEngineForPlayer;

/*
 * Next steps:
 * Take away Races / Position from all Computer player, since they dont have to know races.
 * Always, they will be assigned Bottom to be self side, top to be the enemy side.
 * Also, as to simplifed, the calculation will be done after rotating the baord, so each function will be the same to treat self side on bottom, this should 
 * help to reduce many checking
 * */
abstract public class ComputerPlayer extends Player {
	
	RuleEngineForPlayer engine = null;
	boolean running = false;
	int count = 0;
	
	public ComputerPlayer(String name, BoardGame game, int races, int position) {
		super("Computer Player for "+game+"("+name+")", game, races, position);
		engine = game.getRuleEngineForPlayer();
		System.out.println("engine is "+engine);
	}
	
	public void calcAndSetMove(ChessBoard board){
		final ChessBoard board2 = board;

		if (!running){ // If not running, then start a thread to calculate a move
			running = true;
			Thread th = new Thread(){
				public void run(){
					System.out.println("Computer Player Call calMove()");
					String sChessBoard = "";
					System.out.println("Board Original: "+board2.convertChessBoardToString(board2,false));
					if (board2.isTOP(getPosition())){//TOP
						sChessBoard = board2.convertChessBoardToString(board2,true);
						System.out.println("Board (Top): "+sChessBoard);
					}else{//BOTTOM
						sChessBoard = board2.convertChessBoardToString(board2,false);
						System.out.println("Board (Bottom): "+sChessBoard);
					}
					long lStartTime = System.currentTimeMillis();
					String sMove = calcMove(sChessBoard);
					long lEndTime = System.currentTimeMillis();
					long lTimeUsedMillSec = lEndTime - lStartTime;
					long lTimeUsedSecond = lTimeUsedMillSec / 1000;
					System.out.println("Move Time Used: "+lTimeUsedMillSec+"ms (i.e. "+lTimeUsedSecond+"s)");
					Move move = null;
					System.out.println("Move Original: "+sMove);
					if (board2.isTOP(getPosition())){//TOP
						move = board2.convertStringToMove(board2,sMove,true);
						System.out.println("Move (Top): "+move.getFromX()+" "+move.getFromY()+" "+move.getToX()+" "+move.getToY());
					}else{//BOTTOM
						move = board2.convertStringToMove(board2,sMove,false);
						System.out.println("Move (Bottom): "+move.getFromX()+" "+move.getFromY()+" "+move.getToX()+" "+move.getToY());
					}
					
					String res = move.getFromX()+" "+move.getFromY()+" "+move.getToX()+" "+move.getToY();
					setMove(move);
					running = false;
				}
			};
			th.start();
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public char[][] array2DCopy (char[][] array2D) {
		int len = array2D.length;
		char [][] my2D = new char[len][];
		for(int i = 0; i < len; i++)
			my2D[i] = array2D[i].clone();
		return my2D;
	}

	//abstract protected Move calcMove(ChessBoard board);
	abstract protected String calcMove(String board);

}
