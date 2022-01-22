package com.player;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import com.BoardGame;
import com.Move;
import com.chinese.ChineseChess;
import com.chinese.ChineseChessPieces;
import com.logger.HTMLFile;
import com.logger.HTMLUtil;

public class MonteCarloPlayer extends ComputerPlayer {

	private final int MAX_VALUE = Integer.MAX_VALUE;
	private final int MIN_VALUE = Integer.MIN_VALUE + 1;
	private final int SCALE = 50;

	private boolean bDebug = true;
	private boolean bTrace = false;
	private PrintWriter pw = null;
	private static int logIdx = 0;
	private HTMLFile html = null;
	private String errorlogcontent = "";

	private Random random = new Random();

	public MonteCarloPlayer(String name, BoardGame game, int races, int position) {
		super("Monte Carlo Player for "+game+"("+name+")", game, races, position);
	}

	@Override
	protected String calcMove(String strBoard) {
		if (bDebug){
			++logIdx;
			html = HTMLUtil.createHTMLFile("output/report_"+String.format("%04d", logIdx)+".html");
		}
		Scanner s = new Scanner(strBoard);
		String name = s.nextLine();//Name
		int w=s.nextInt();//W
		int h=s.nextInt();//H
		s.nextLine();
		char[][] cBoard = new char[w][h];
		for(int y=0; y<h; y++){
			String line = s.nextLine();
			for(int x=0; x<w; x++){
				cBoard[x][y] = line.charAt(x);
			}
		}
		s.close();
		
		int iScale = SCALE;
		Move bestMove = getBestMove(cBoard, w, h, iScale, "", 1);
		String res = bestMove.getFromX()+" "+bestMove.getFromY()+" "+bestMove.getToX()+" "+bestMove.getToY();
		if ("0 0 0 0".equals(res)) System.err.println(" No Valid Move. Please check the RuleEngineForPlayer.java");
		
		if (bDebug){
			System.out.print("bestMove = "+res);
			String bodycontent = "";
			int fmx = bestMove.getFromX();
			int fmy = bestMove.getFromY();
			int tox = bestMove.getToX();
			int toy = bestMove.getToY();
			char ch = cBoard[fmx-1][fmy-1];
			int value = bestMove.getValue();

			bodycontent += String.format("<br><h1>Before : <PRE>%s</PRE></h1>", strBoard);
			//bodycontent += String.format("<br><h1>After : <PRE>%s</PRE></h1>", engine.boardToString(cBoard, w, h));
			String directionsign = getDirectionSign(fmx,fmy,tox,toy);
			bodycontent += String.format("<br>%c; Best Move: (%d,%d) %s (%d,%d); Value: %d", ch, fmx, fmy, directionsign, tox, toy, value);

			bodycontent += HTMLUtil.table("tableid", bestMove.getTableContent());
			bodycontent += String.format("<br><h1>Error Log</h1><br>%s", errorlogcontent);
			bodycontent += HTMLUtil.treetablescript("tableid");

			String content = ""; 
			content += HTMLUtil.header("Player Decision Tree");
			content += HTMLUtil.body(HTMLUtil.div("main", bodycontent));
			html.write(HTMLUtil.html(content));
			html.close();

		}
		return res;
	}

	private Move getBestMove(char[][] cBoard, int w, int h, int iScale, String sParentId, int iParentId){
		
		return getBestMoveEat( cBoard, w, h, iScale, sParentId, iParentId);
	}

	private Move getBestMoveEat(char[][] cBoard, int w, int h, int iScale, String sParentId, int iParentId){
		int maxValue = MIN_VALUE;
		Vector<Move> moves = null;
		Move bestMove = null;
		
		
		// 1. Loop Valid Move
		// 2. For each move, try move to the end, to see if it is win
		// 3. Count all win and lose case, to make the move value
		// 4. return the most win move as best move
		
		// 1. Loop Valid Move
		moves = engine.getValidMoves(cBoard, w, h);
		
		// 2. For each move, try move to the end, to see if it is win
		// Try Different Move Loop Start Here
		String rowTableContent = "";
		int cnt = 0;
		String finalResult = "";
		for(Move m : moves){
			// 2.1. Initialized
			char eatenPiece = engine.getEatenPieceFromMove(cBoard, w, h, m);
			
			// 2.2. Try Move
			String orgBoardStr1 = engine.boardToString(cBoard, w, h);
			cBoard = engine.tryMove(cBoard, w, h, m);
			String orgBoardStr2 = engine.boardToString(cBoard, w, h);
			cBoard = engine.rotateBoard(cBoard, w, h);
			String orgBoardStr3 = engine.boardToString(cBoard, w, h);
			
			// 3. Count all win and lose case, to make the move value
			int winScore = 0;
			for(int s=0; s<iScale; s++) {
				String orgBoardStr4 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
				int score = -1 * isRandomPlayWin_Method1(array2DCopy(cBoard), w, h);
				winScore += score;
				//int score = -1 * isRandomPlayWin_Method2(array2DCopy(cBoard), w, h);
				//winScore += score;
			}
			int curValue = winScore;
			m.setValue(winScore);
			System.out.println(cnt+": "+m+" \t= "+winScore);
			finalResult += "\n" + cnt+": "+m+" \t= "+winScore;
			
			// 4. Untry move
			cBoard = engine.rotateBoard(cBoard, w, h);
			cBoard = engine.unTryMove(cBoard, w, h, m, eatenPiece);
			
			if (maxValue < curValue){
				maxValue = curValue;
				bestMove = m;
			}else if (maxValue==curValue){
				int ran = random.nextInt(10 ) + 1;
				if(ran>5) {
					bestMove = m;
				}
			}
			cnt++;
		}
		System.out.println(finalResult);
		
		bestMove = (bestMove==null)?new Move(0,0,0,0):bestMove; // No Valid Move Case
		bestMove.setTableContent(rowTableContent);
		return bestMove;
	}
	
	// Return random play result, true when win; false when lose
	/*
	 * Method1: Win Count: After n move, the game end, mark win if the game end with win
	 * Since it is so stupid that even the king being capture in next move, still dont know to save the king first, so amend the logic.
	 * 
	 * Method2: Return +(1000-NumOfTurns) for win, and -(1000-NumOfTurns) for Lose. Return 0 if draw
	 * Purpose: Avoid i need 900 moves to win, but 10 moves to lose, still thinking i am win
	 * */
	private int isRandomPlayWin_Method1(char[][] cBoard, int w, int h){
		int side = 0;
		String orgBoardStr5 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		while (side<1000) { // if side = 1000, means draw
			Vector<Move> moves = engine.getValidMoves(cBoard, w, h);
			int moveSize = moves.size();
			int selMove = (int) (random.nextInt(moveSize));
			Move m = moves.get(selMove);
			cBoard = engine.tryMove(cBoard, w, h, m);
			String orgBoardStr6 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
			if (engine.isWin(cBoard, w, h)) break;
			cBoard = engine.rotateBoard(cBoard, w, h);
			side ++;
		}
		String orgBoardStr7 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		cBoard = engine.rotateBoard(cBoard, w, h);
		String orgBoardStr8 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		boolean bIsWin = side % 2 == 0;
		int res = 0;
		if (side==100) { // draw
			res = 0;
		}else if (bIsWin) { // win
			res = 1;
		}else { // lose
			res = -1;
		}
		return res;
	}
	
	private int isRandomPlayWin_Method2(char[][] cBoard, int w, int h){
		int side = 0;
		String orgBoardStr5 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		while (side<1000) { // if side = 1000, means draw
			Vector<Move> moves = engine.getValidMoves(cBoard, w, h);
			int moveSize = moves.size();
			int selMove = (int) (random.nextInt(moveSize));
			Move m = moves.get(selMove);
			cBoard = engine.tryMove(cBoard, w, h, m);
			String orgBoardStr6 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
			if (engine.isWin(cBoard, w, h)) break;
			cBoard = engine.rotateBoard(cBoard, w, h);
			side ++;
		}
		String orgBoardStr7 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		cBoard = engine.rotateBoard(cBoard, w, h);
		String orgBoardStr8 = engine.boardToString(cBoard, w, h); // The board here is not idential, has been changed by someone !
		boolean bIsWin = side % 2 == 0;
		int res = 0;
		if (side==100) { // draw
			res = 0;
		}else if (bIsWin) { // win
			res = (1000-side) * +1;
		}else { // lose
			res = (1000-side) * -1;
		}
		return res;
	}

	
	private String writeMoveLogStr(String sId, String sParentId, char[][] cBoard, int w, int h, Move move, int iDepth, String sRemarks) {
		//String sId = (id==0)?null:id+"";
		//String sParentId = (parentid==0)?null:parentid+"";
		
		// Move Line
		String strBoard = "";
		String strMove = "";
		String position = (iDepth)%2==0?"BOTTOM":"TOP";
		if ("TOP".equals(position)){
			char[][] rBoard = engine.rotateBoard(cBoard, w, h);
			Move rMove = engine.rotateMove(move, w, h);
			int fmx = rMove.getFromX();
			int fmy = rMove.getFromY();
			int tox = rMove.getToX();
			int toy = rMove.getToY();
			char ch = rBoard[fmx-1][fmy-1];
			int value = rMove.getValue();
			String directionsign = getDirectionSign(fmx,fmy,tox,toy);
			strMove = String.format("%s;%c; T-Move: (%d,%d) %s (%d,%d); Value: %d", sId, ch, fmx, fmy, directionsign, tox, toy, value);
		}else{
			int fmx = move.getFromX();
			int fmy = move.getFromY();
			int tox = move.getToX();
			int toy = move.getToY();
			char ch = cBoard[fmx-1][fmy-1];
			int value = move.getValue();
			String directionsign = getDirectionSign(fmx,fmy,tox,toy);
			strMove = String.format("%s;%c; B-Move: (%d,%d) %s (%d,%d); Value: %d", sId, ch, fmx, fmy, directionsign, tox, toy, value);
		}

		String rowmove = strMove;
		rowmove += sRemarks;
		String rowtd = HTMLUtil.td(5, rowmove);
		String rowtr = HTMLUtil.tr_tt(sId, sParentId, rowtd);
		
		return rowtr;
	}

	private String getDirectionSign(int fmx, int fmy, int tox, int toy) {
		String sign = "";
		if (fmx==tox){  // Up or Down
			sign = fmy > toy ? "^" : "V"; 
		}else{			// Left or Right
			sign = fmx > tox ? "<-" : "->";
		}
		return sign;
	}

	public static void main(String[] s) {
		ChineseChess boardGame = new ChineseChess();
		MonteCarloPlayer player = new MonteCarloPlayer("RED BOTTOM",boardGame,ChineseChessPieces.RED,ChineseChessPieces.BOTTOM);
		String strBoard = "" +
				"ChineseChess\r\n" + 
				"9\r\n" + 
				"10\r\n" + 
				"...k.se..\r\n" + 
				".........\r\n" + 
				"tchse..ch\r\n" + 
				"p.p.pC...\r\n" + 
				".C...t...\r\n" + 
				"......p.P\r\n" + 
				"P.P.P...T\r\n" + 
				"..H......\r\n" + 
				".....K...\r\n" + 
				"T.ES.SE.H";
		
		player.calcMove(strBoard);
		
	}
}