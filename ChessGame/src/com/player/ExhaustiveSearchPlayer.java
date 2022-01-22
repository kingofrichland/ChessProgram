package com.player;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

import com.BoardGame;
import com.Move;
import com.logger.HTMLFile;
import com.logger.HTMLUtil;

public class ExhaustiveSearchPlayer extends ComputerPlayer {

	private final int MAX_VALUE = Integer.MAX_VALUE;
	private final int MIN_VALUE = Integer.MIN_VALUE + 1;
	private final int DEPTH = 2;
	private final int EAT_DEPTH = 2;

	private boolean bDebug = true;
	private boolean bTrace = false;
	private PrintWriter pw = null;
	private static int logIdx = 0;
	private HTMLFile html = null;
	private String errorlogcontent = "";

	private Random random = new Random();

	public ExhaustiveSearchPlayer(String name, BoardGame game, int races, int position) {
		super("Exhaustive Search Player for "+game+"("+name+")", game, races, position);
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
		
		int iDepth = DEPTH;
		Move bestMove = getBestMove(cBoard, w, h, iDepth, "", 1);
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

	private Move getBestMove(char[][] cBoard, int w, int h, int iDepth, String sParentId, int iParentId){
		return getBestMoveEat( cBoard, w, h, iDepth, sParentId, iParentId, false);
	}
	
	private Move getBestEat(char[][] cBoard, int w, int h, int iDepth, String sParentId, int iParentId){
		return getBestMoveEat( cBoard, w, h, iDepth, sParentId, iParentId, true);
	}

	private Move getBestMoveEat(char[][] cBoard, int w, int h, int iDepth, String sParentId, int iParentId, boolean bIsEatMode){
		int maxValue = MIN_VALUE;
		Vector<Move> moves = null;
		Move bestMove = null;
		boolean bIsWin = engine.isWin(cBoard, w, h);
		
		if (iDepth>0 && !bIsWin){ // To save some calculation power, by calc only when iDepth>0
			cBoard = engine.cloneBoard(cBoard, w, h);
			if (bIsEatMode){
				moves = engine.getValidEats(cBoard, w, h);
				//Vector<Move> vValidWinMoves = engine.getValidWinMoves(cBoard, w, h);
				//if (vValidWinMoves!=null && vValidWinMoves.size()>0)
				//	moves.addAll(vValidWinMoves);
			}else{
				moves = engine.getValidMoves(cBoard, w, h);
			}
		}
		
		// When depth=0, return either evaluation result, or besteat result
		if (iDepth<=0 || bIsWin){
			int curValue = 0;
			Move leafNodeMove = null;
			String tableContent = "";
			if (bIsWin) {
				leafNodeMove = new Move(0,0,0,0);
				curValue = engine.getWinValue() * (iDepth + 1); // For iDepth=0 case, it will be 1xWinValue; For iDepth=1 case, it will be 2xWinValue
				tableContent = writeEvaluateLogStr(sParentId, cBoard, w, h, leafNodeMove, iDepth);
			}else
			if (bIsEatMode){
				leafNodeMove = new Move(0,0,0,0);
				curValue = engine.evaluate(cBoard, w, h);
				tableContent = writeEvaluateLogStr(sParentId, cBoard, w, h, leafNodeMove, iDepth);
			}else{ // If leafnode of bestmove, call besteat
				leafNodeMove = getBestEat(cBoard, w, h, EAT_DEPTH, sParentId, iParentId);
				curValue = leafNodeMove.getValue();
				tableContent = leafNodeMove.getTableContent();
			}
			leafNodeMove.setValue(curValue);
			leafNodeMove.setTableContent(tableContent);
			return leafNodeMove;
		}
		
		// Try Different Move Loop Start Here
		int cnt = 1;
		String rowTableContent = "";
		for(Move m : moves){
			// 1. Initialized
			//m = engine.supplementMoveInfo(cBoard, m);
			char eatenPiece = engine.getEatenPieceFromMove(cBoard, w, h, m);
			String sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
			String boardInHTML = "";
			String orgBoardStr = engine.boardToString(cBoard, w, h);
			if (bTrace) boardInHTML += HTMLUtil.td("<br>Original:<br><pre>" + orgBoardStr + "</pre>");
			
			// 2. Try Move
			cBoard = engine.tryMove(cBoard, w, h, m);
			if (bTrace) boardInHTML += HTMLUtil.td("<br>After TryMove:<br><pre>" + engine.boardToString(cBoard, w, h) + "</pre>");

			int curValue = 0;
			// 3. Check If Win Already
			if (engine.isWin(cBoard, w, h)){
				curValue += engine.getWinValue() * (iDepth + 1); // For iDepth=0 case, it will be 1xWinValue; For iDepth=1 case, it will be 2xWinValue
			}
			
			// 4. Go Deeper, Get Value
			cBoard = engine.rotateBoard(cBoard, w, h);
			if (bTrace) boardInHTML += HTMLUtil.td("<br>After Rotate (One):<br><pre>" + engine.boardToString(cBoard, w, h) + "</pre>");
			
			Move childBestMove = null;
			if (bIsEatMode){
				childBestMove = getBestEat(cBoard, w, h, iDepth-1, sId, cnt);
			}else{
				childBestMove = getBestMove(cBoard, w, h, iDepth-1, sId, cnt);
			}
			
			curValue += -1 * childBestMove.getValue();
			String childTableContent = childBestMove.getTableContent();
			if (bTrace) boardInHTML += HTMLUtil.td("<br>After getBestMove:<br><pre>" + engine.boardToString(cBoard, w, h) + "</pre>");
			
			m.setValue(curValue);
			cBoard = engine.rotateBoard(cBoard, w, h);
			if (bTrace) boardInHTML += HTMLUtil.td("<br>After Rotate (Two):<br><pre>" + engine.boardToString(cBoard, w, h) + "</pre>");
			if (maxValue < curValue){
				maxValue = curValue;
				bestMove = m;
			}else if (!bIsEatMode && iDepth==DEPTH && maxValue==curValue){
				int ran = random.nextInt(10 ) + 1;
				if(ran>5) {
					bestMove = m;
				}
			}

			
			// 4. UnTry Move
			cBoard = engine.unTryMove(cBoard, w, h, m, eatenPiece);
			String unTryBoardStr = engine.boardToString(cBoard, w, h);
			if (bTrace) boardInHTML += HTMLUtil.td("<br>After UnTryMove:<br><pre>" + unTryBoardStr + "</pre>");

			// 5. Write Log
			String sRemark = "";
			if (!"".equals(boardInHTML)){
				String sTR = HTMLUtil.tr(boardInHTML);
				String sTABLE = HTMLUtil.table(sTR);
				sRemark = sTABLE;
			}
			//writeMoveLog(sId, sParentId, cBoard, w, h, m, iDepth, sRemark);
			String selfTableContent = "";
			if (bIsEatMode){
				selfTableContent = writeEatLogStr(sId, sParentId, cBoard, w, h, m, iDepth, sRemark);
			}else{
				selfTableContent = writeMoveLogStr(sId, sParentId, cBoard, w, h, m, iDepth, sRemark);
			}

			rowTableContent += selfTableContent + childTableContent;
			if (!orgBoardStr.equals(unTryBoardStr)){
				errorlogcontent += String.format("<br>Error Id: %s", sId);
			}
			
			cnt++;
		}
		bestMove = (bestMove==null)?new Move(0,0,0,0):bestMove; // No Valid Move Case
		bestMove.setTableContent(rowTableContent);
		return bestMove;
	}
	

	
	private String writeMoveLogStr(String sId, String sParentId, char[][] cBoard, int w, int h, Move move, int iDepth, String sRemarks) {
		//String sId = (id==0)?null:id+"";
		//String sParentId = (parentid==0)?null:parentid+"";
		
		// Move Line
		String strBoard = "";
		String strMove = "";
		String position = (DEPTH+iDepth)%2==0?"BOTTOM":"TOP";
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
	
	
	private String writeEatLogStr(String sId, String sParentId, char[][] cBoard, int w, int h, Move move, int iDepth, String sRemarks) {
		//String sId = (id==0)?null:id+"";
		//String sParentId = (parentid==0)?null:parentid+"";
		
		// Move Line
		String strMove = "";
		String position = ((EAT_DEPTH-iDepth)+DEPTH)%2==0?"BOTTOM":"TOP";
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
			strMove = String.format("%s;%c; T-EatMove: (%d,%d) %s (%d,%d); Value: %d", sId, ch, fmx, fmy, directionsign, tox, toy, value);
		}else{
			int fmx = move.getFromX();
			int fmy = move.getFromY();
			int tox = move.getToX();
			int toy = move.getToY();
			char ch = cBoard[fmx-1][fmy-1];
			int value = move.getValue();
			String directionsign = getDirectionSign(fmx,fmy,tox,toy);
			strMove = String.format("%s;%c; B-EatMove: (%d,%d) %s (%d,%d); Value: %d", sId, ch, fmx, fmy, directionsign, tox, toy, value);
		}

		String rowmove = strMove;
		//rowmove += String.format("<br><h1>%s</h1><h1><PRE>%s</PRE></h1>", position, strBoard);
		rowmove += sRemarks;
		String rowtd = HTMLUtil.td(5, rowmove);
		String rowtr = HTMLUtil.tr_tt(sId, sParentId, rowtd);
		
		return rowtr;
	}
	
	
	
	private String writeEvaluateLogStr(String sParentId, char[][] cBoard, int w, int h, Move move, int iDepth) {
		/*
		 * 	<tr data-tt-id="111" data-tt-parent-id="11">
		<th>BOARD</th>
		<th colspan=4>
		<h1><PRE>
l.....t
.d...c.
m.hw..e
.......
.......
.......
.E.WH.M
.C...D.
T.....L
		</PRE></h1>
		</th>
	</tr>
	<tr data-tt-id="112" data-tt-parent-id="11">
		<th>SEQ</th>
		<th>FIELD</th>
		<th>CALCULATION</th>
		<th>VALUE</th>
		<th>REMARK</th>
	</tr>
	<tr data-tt-id="113" data-tt-parent-id="11">
		<td>1.</td>
		<td>SELF PIECE VALUE</td>
		<td>8 + 6 + 5 + 2</td>
		<td>21</td>
		<td>-</td>
	</tr>
	<tr data-tt-id="114" data-tt-parent-id="11">
		<td>2.</td>
		<td>ENEMY PIECE VALUE</td>
		<td>6 + 5 + 2</td>
		<td>18</td>
		<td>-</td>
	</tr>
	<tr data-tt-id="115" data-tt-parent-id="11">
		<td>3.</td>
		<td>SELF POSITION VALUE</td>
		<td>8 + 7 + 6 + 5 + 2</td>
		<td>16</td>
		<td>-</td>
	</tr>
	<tr data-tt-id="116" data-tt-parent-id="11">
		<td>4.</td>
		<td>ENEMY POSITION VALUE</td>
		<td>13 + 7 + 6 + 5 + 2</td>
		<td>9</td>
		<td>-</td>
	</tr>
	<tr data-tt-id="117" data-tt-parent-id="11">
		<td>5.</td>
		<td>TOTAL</td>
		<td>(21 - 12) * 10 - ( 4 - 3)</td>
		<td>6</td>
		<td>-</td>
	</tr>

		 * */
		
		// Initialize
		int cnt = 1;
		String sId = "";
		//String sParentId = "";
		String rowth = "";
		String rowtr = "";
		String tempevaluatecontent = "";

		//parentid = id;
		tempevaluatecontent = "";
		//sParentId = parentid+"";

		// Board Line
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("BOARD");
		String strBoard = "";
		String position = ((EAT_DEPTH-iDepth)+DEPTH)%2==0?"BOTTOM":"TOP";
		if ("TOP".equals(position)){
			char[][] rBoard = engine.rotateBoard(cBoard, w, h);
			strBoard = engine.boardToString(rBoard, w, h);
		}else{
			strBoard = engine.boardToString(cBoard, w, h);
		}
		
		rowth += HTMLUtil.th(4, String.format("<h1>%s;%s</h1><h1><PRE>%s</PRE></h1>", sId, position, strBoard));

		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;

		// Header Line
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("SEQ");
		rowth += HTMLUtil.th("FIELD");
		rowth += HTMLUtil.th("CALCULATION");
		rowth += HTMLUtil.th("VALUE");
		rowth += HTMLUtil.th("REMARK");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;
		
		// Row 1 : SELF PIECE VALUE
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("1.");
		rowth += HTMLUtil.th("SELF PIECE VALUE");
		rowth += HTMLUtil.th(engine.selfPieceCalculationToString(cBoard, w, h));
		rowth += HTMLUtil.th(engine.selfPieceValueToString(cBoard, w, h));
		rowth += HTMLUtil.th("-");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;

		// Row 2 : ENEMY PIECE VALUE
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("2.");
		rowth += HTMLUtil.th("ENEMY PIECE VALUE");
		rowth += HTMLUtil.th(engine.enemyPieceCalculationToString(cBoard, w, h));
		rowth += HTMLUtil.th(engine.enemyPieceValueToString(cBoard, w, h));
		rowth += HTMLUtil.th("-");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;

		// Row 3 : SELF POSITION VALUE
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("3.");
		rowth += HTMLUtil.th("SELF POSITION VALUE");
		rowth += HTMLUtil.th(engine.selfPositionCalculationToString(cBoard, w, h));
		rowth += HTMLUtil.th(engine.selfPositionValueToString(cBoard, w, h));
		rowth += HTMLUtil.th("-");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;

		// Row 4 : ENEMY POSITION VALUE
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("4.");
		rowth += HTMLUtil.th("ENEMY POSITION VALUE");
		rowth += HTMLUtil.th(engine.enemyPositionCalculationToString(cBoard, w, h));
		rowth += HTMLUtil.th(engine.enemyPositionValueToString(cBoard, w, h));
		rowth += HTMLUtil.th("-");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;

		// Row 5 : TOTAL
		//id = parentid*10 + cnt;
		//sId = id+"";
		sId = "".equals(sParentId) ? "" + cnt : sParentId + "_" + cnt;
		rowth = "";
		rowth += HTMLUtil.th("5.");
		rowth += HTMLUtil.th("TOTAL");
		rowth += HTMLUtil.th(engine.totalCalculationToString(cBoard, w, h));
		rowth += HTMLUtil.th(engine.totalValueToString(cBoard, w, h));
		rowth += HTMLUtil.th("-");
		rowtr = HTMLUtil.tr_tt(sId, sParentId, rowth);
		tempevaluatecontent += rowtr;
		cnt++;
		
		return tempevaluatecontent;
		
	}
	


}