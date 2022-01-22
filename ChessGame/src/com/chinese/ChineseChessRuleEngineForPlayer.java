package com.chinese;

import java.util.Vector;

import com.Move;
import com.rule.RuleEngineForPlayer;

public class ChineseChessRuleEngineForPlayer extends RuleEngineForPlayer {
	
	int[] dx_king = new int[]{1,0,-1,0}; 
	int[] dy_king = new int[]{0,1,0,-1}; 
	int[] dx_chariot = new int[]{1,0,-1,0}; 
	int[] dy_chariot = new int[]{0,1,0,-1};  
	int[] dx_horse = new int[]{
			 1, 2, 2, 1,
			-1,-2,-2,-1}; 
	int[] dy_horse = new int[]{
			-2,-1, 1, 2,
			 2, 1,-1,-2}; 
	int[] dx_cannon = new int[]{1,0,-1,0}; 
	int[] dy_cannon = new int[]{0,1,0,-1};
	int[] dx_servant = new int[]{ 1, 1,-1,-1}; 
	int[] dy_servant = new int[]{-1, 1, 1,-1}; 
	int[] dx_chancellor = new int[]{ 2, 2,-2,-2}; 
	int[] dy_chancellor = new int[]{-2, 2, 2,-2}; 
	int[] dx_pawn = new int[]{1,0,-1,0}; 
	int[] dy_pawn = new int[]{0,1,0,-1}; 
	
	int value_king = 50;
	int value_chariot = 20;
	int value_horse = 10;
	int value_cannon = 10;
	int value_servant = 2;
	int value_chancellor = 4;
	int value_pawn = 1;

	public ChineseChessRuleEngineForPlayer(int w, int h) {
		super(w,h);
	}
	

	public Vector<Move> getValidMoves(char[][] cboard, int w, int h) {
		Vector<Move> vMove = new Vector<Move>();
		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				if (isBlank(cboard, x, y)) continue;
				if (!(hasSelfPiece(cboard, x, y))) continue;
				int[] dx = getDxArray(cboard[x-1][y-1]);
				int[] dy = getDyArray(cboard[x-1][y-1]);
				l2: for(int d=0; d<dx.length; d++){
					int px = x;
					int py = y;
					int tox = px;
					int toy = py;
					int dxyloop = getDxyLoopCount(cboard[x-1][y-1]);
					boolean bHasMiddleMan = false;
					l3: for(int lp=0; lp<dxyloop; lp++) {
						tox += dx[d];
						toy += dy[d];
						boolean bIsInsideBoard = isInsideBoard(tox,toy);
						if (!bIsInsideBoard) continue l2;
						boolean bHasSelfPiece = hasSelfPiece(cboard, tox, toy);
						boolean bIsInsideSelfSide = isInsideSelfSide(tox,toy);
						boolean bIsInsideKingSqr = isInsideKingSqr(tox,toy);
						boolean bHasEnemyPiece = hasEnemyPiece(cboard, tox, toy);
						
						switch(cboard[px-1][py-1]){
						case 'K': //ChineseChessPieces.KING
						case 'k': //ChineseChessPieces.KING
								if (bHasSelfPiece) continue l2;
								if (bIsInsideKingSqr){
									vMove.add(new Move(px,py,tox,toy));
								}
								break;	
						case 'T': //ChineseChessPieces.Chariot
						case 't': //ChineseChessPieces.Chariot
								if (bHasSelfPiece) continue l2;
								vMove.add(new Move(px,py,tox,toy));
								if (hasPiece(cboard, tox, toy)) break l3;
								break;
						case 'H': //ChineseChessPieces.Horse
						case 'h': //ChineseChessPieces.Horse
								if (bHasSelfPiece) continue l2;
								if(!hasHorseInterruptPiece(cboard, px, py, dx[d], dy[d])) {
									vMove.add(new Move(px,py,tox,toy));
								}
								break;
						case 'C': //ChineseChessPieces.Cannon
						case 'c': //ChineseChessPieces.Cannon
								if (bHasMiddleMan) {
									// Valid Eat
									if (bHasSelfPiece) {
										break l3;
									} else
									if (bHasEnemyPiece) {
										vMove.add(new Move(px,py,tox,toy));
										break l3;
									}
								} else {
									// Valid Move
									if ((hasPiece(cboard, tox, toy))) {
										bHasMiddleMan = true;
									} else {
										vMove.add(new Move(px,py,tox,toy));
									}
								}
								break;
						case 'S': //ChineseChessPieces.Servant
						case 's': //ChineseChessPieces.Servant
								if (bHasSelfPiece) continue l2;
								if (bIsInsideKingSqr){
									vMove.add(new Move(px,py,tox,toy));
								}
								break;
						case 'E': //ChineseChessPieces.Chancellor
						case 'e': //ChineseChessPieces.Chancellor
								if (bHasSelfPiece) continue l2;
								if (bIsInsideSelfSide && !hasChancellorInterruptPiece(cboard, px, py, dx[d], dy[d])) {
									vMove.add(new Move(px,py,tox,toy));
								}
								break;
						case 'P': //ChineseChessPieces.PAWN
						case 'p': //ChineseChessPieces.PAWN
								if (bHasSelfPiece) continue l2;
								if (bIsInsideSelfSide){
									if (tox==px && toy<py) {
										vMove.add(new Move(px,py,tox,toy));
									}
								}else {
									if (toy<=py) {
										vMove.add(new Move(px,py,tox,toy));
									}
								}
								break;	
						}
						
					}
				}			
			}
		}

		return vMove;
}

	private boolean hasChancellorInterruptPiece(char[][] cboard, int px, int py, int dx, int dy) {
		int interruptDx = dx / 2;
		int interruptDy = dy / 2;
		return hasPiece(cboard, px+interruptDx, py+interruptDy);
	}

	private boolean hasHorseInterruptPiece(char[][] cboard, int px, int py, int dx, int dy) {
		int interruptDx = dx / 2;
		int interruptDy = dy / 2;
		return hasPiece(cboard, px+interruptDx, py+interruptDy);
	}

	private boolean isInsideKingSqr(int x, int y) {
		int selfTopX = 4;
		int selfTopY = 8;
		int selfBottomX = 6;
		int selfBottomY = 10;
		return ((x >= selfTopX) && (x <= selfBottomX)) &&
			   ((y >= selfTopY) && (y <= selfBottomY));
	}


	private boolean isInsideSelfSide(int x, int y) {
		int selfTopY = 6;
		int selfBottomY = 10;
		return ((y >= selfTopY) && (y <= selfBottomY));
	}

	@Override
	public int getNextRaces(int iRaces) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRacesByPiece(char[][] cboard, int x, int y){
		int iRaces = 0;
		if (cboard[x-1][y-1]>='a' && cboard[x-1][y-1]<='z') iRaces = ChineseChessPieces.RED;
		if (cboard[x-1][y-1]>='A' && cboard[x-1][y-1]<='Z') iRaces = ChineseChessPieces.BLACK;
		return iRaces;
	}

	@Override
	public int evaluate(char[][] cBoard, int w, int h) {
		int selfValue = 0;
		int enemyValue = 0;
		selfPieceCalculation = "";
		enemyPieceCalculation = "";

		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				if (hasSelfPiece(cBoard, x, y)){
					int v1 = getPieceValue(cBoard[x-1][y-1]);
					selfValue += v1;
					selfPieceCalculation += " + " + v1;
				}else
				if (hasEnemyPiece(cBoard, x, y)){
					int v3 = getPieceValue(cBoard[x-1][y-1]);
					enemyValue += v3;				
					enemyPieceCalculation += " + " + v3;
				}
			}
		}
		selfPieceValue = ""+selfValue;
		enemyPieceValue = ""+enemyValue;
		
		int v5 = (selfValue - enemyValue);
		totalCalculation = String.format("(%s - %s)", selfPieceValue, enemyPieceValue);
		totalValue = ""+v5;
		
		return v5;
	}
	
	private int getPieceValue(char piece){
		int v = 0;
		switch(piece){
			case 'K': v = value_king; break;
			case 'k': v = value_king; break;
			case 'T': v = value_chariot; break;
			case 't': v = value_chariot; break;
			case 'H': v = value_horse; break;
			case 'h': v = value_horse; break;
			case 'C': v = value_cannon; break;
			case 'c': v = value_cannon; break;
			case 'S': v = value_servant; break;
			case 's': v = value_servant; break;
			case 'E': v = value_chancellor; break;
			case 'e': v = value_chancellor; break;
			case 'P': v = value_pawn; break;
			case 'p': v = value_pawn; break;
		}
		return v;
	}

	public boolean isWin(char[][] cBoard, int w, int h) {
		int enemyBaseX1 = 4;
		int enemyBaseX2 = 6;
		int enemyBaseY1 = 1;
		int enemyBaseY2 = 3;
		for(int y=enemyBaseY1; y<=enemyBaseY2; y++){
			for(int x=enemyBaseX1; x<=enemyBaseX2; x++){
				if (isBlank(cBoard, x, y)) continue;
				boolean bHasKing = cBoard[x-1][y-1]=='k';
				if (bHasKing) return false;
			}
		}
		return true;
	}
	
	private int[] getDxArray(char c) {
		int[] dx = new int[]{1,0,-1,0};
		switch(c){
		case 'K': 
		case 'k': dx = dx_king; break;
		case 'T': 
		case 't': dx = dx_chariot; break;
		case 'H': 
		case 'h': dx = dx_horse; break;
		case 'C': 
		case 'c': dx = dx_cannon; break;
		case 'S': 
		case 's': dx = dx_servant; break;
		case 'E': 
		case 'e': dx = dx_chancellor; break;
		case 'P': 
		case 'p': dx = dx_pawn; break;
		}
		return dx;
	}
	
	private int[] getDyArray(char c) {
		int[] dy = new int[]{0,1,0,-1};
		switch(c){
		case 'K': 
		case 'k': dy = dy_king; break;
		case 'T': 
		case 't': dy = dy_chariot; break;
		case 'H': 
		case 'h': dy = dy_horse; break;
		case 'C': 
		case 'c': dy = dy_cannon; break;
		case 'S': 
		case 's': dy = dy_servant; break;
		case 'E': 
		case 'e': dy = dy_chancellor; break;
		case 'P': 
		case 'p': dy = dy_pawn; break;
		}
		return dy;
	}
	
	private int getDxyLoopCount(char c) {
		int loop = 1;
		switch(c){
		case 'K': 
		case 'k': loop = 1; break;
		case 'T': 
		case 't': loop = 10; break;
		case 'H': 
		case 'h': loop = 1; break;
		case 'C': 
		case 'c': loop = 10; break;
		case 'S': 
		case 's': loop = 1; break;
		case 'E': 
		case 'e': loop = 1; break;
		case 'P': 
		case 'p': loop = 1; break;
		}
		return loop;
	}
}
