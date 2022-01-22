package com.animal;

import java.util.Vector;

import com.Move;
import com.rule.RuleEngineForPlayer;

public class AnimalChessRuleEngineForPlayer extends RuleEngineForPlayer {

	/*
	 * Top left corner is 
	 * (1,1)
	 * 
	 * 
	 * 
	 * Right Down corner is 
	 * 				(7,9)
	 * */
	int[] dx = new int[]{1,0,-1,0};
	int[] dy = new int[]{0,1,0,-1};
	
	public AnimalChessRuleEngineForPlayer(int w, int h) {
		super(w,h);
	}

	private boolean isInsideRiver(int x, int y) {
		int river1x1 = 2;
		int river1y1 = 4;
		int river1x2 = 3;
		int river1y2 = 6;
		boolean in1 = ((x >= river1x1 && x <= river1x2) && (y >= river1y1 && y <= river1y2));
		
		int river2x1 = 5;
		int river2y1 = 4;
		int river2x2 = 6;
		int river2y2 = 6;
		boolean in2 = ((x >= river2x1 && x <= river2x2) && (y >= river2y1 && y <= river2y2));

		return (in1 || in2);
	}
	
	public boolean isWin(char[][] cBoard, int w, int h) {
		int enemyBaseX = 4;
		int enemyBaseY = 1;
		return this.hasSelfPiece(cBoard, enemyBaseX, enemyBaseY);
	}

	public int getWinValue() {
		// TODO Auto-generated method stub
		return 1000;
	}
	
	// Function for players

	public Vector<Move> getValidMoves(char[][] cboard, int w, int h) {
		Vector<Move> vMove = new Vector<Move>();
		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				if (isBlank(cboard, x, y)) continue;
				if (!(hasSelfPiece(cboard, x, y))) continue;
				l2: for(int d=0; d<dx.length; d++){
					int px = x;
					int py = y;
					int tox = px+dx[d];
					int toy = py+dy[d];
					boolean bIsInsideBoard = isInsideBoard(tox,toy);
					if (!bIsInsideBoard) continue l2;
					boolean bHasSelfPiece = hasSelfPiece(cboard, tox, toy);
					if (bHasSelfPiece) continue l2;
					boolean bIsInsideSelfHome = isInsideSelfBase(tox,toy);
					if (bIsInsideSelfHome) continue l2;
					boolean bIsInsideRiver = isInsideRiver(tox,toy);
					boolean bHasEnemyPiece = hasEnemyPiece(cboard, tox, toy);
					
					switch(cboard[px-1][py-1]){
					case 'E': //AnimalChessPieces.ELEPHANT: 
					case 'e': //AnimalChessPieces.ELEPHANT: 
							if (!bIsInsideRiver){
								if (!bHasEnemyPiece){
									vMove.add(new Move(px,py,tox,toy));
								}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
									vMove.add(new Move(px,py,tox,toy));
								}
							}
							break;
					case 'L': //AnimalChessPieces.LION: 
					case 'l': //AnimalChessPieces.LION: 
							if (!bIsInsideRiver){
								if (!bHasEnemyPiece){
									vMove.add(new Move(px,py,tox,toy));
								}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
									vMove.add(new Move(px,py,tox,toy));
								}
							}else{//jump river
								do{
									tox = tox+dx[d];
									toy = toy+dy[d];
									if (hasSelfPiece(cboard, tox, toy)) continue l2;
								} while(isInsideRiver(tox,toy));
								bIsInsideBoard = isInsideBoard(tox,toy);
								bHasSelfPiece = hasSelfPiece(cboard, tox, toy);
								bHasEnemyPiece = hasEnemyPiece(cboard, tox, toy);
								if (bIsInsideBoard && !bHasSelfPiece){
									if (!bHasEnemyPiece){
										vMove.add(new Move(px,py,tox,toy));
									}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1])){
										vMove.add(new Move(px,py,tox,toy));
									}
								}
							}
							break;
					case 'T'://AnimalChessPieces.TIGER: 
					case 't'://AnimalChessPieces.TIGER: 
							if (!bIsInsideRiver){
								if (!bHasEnemyPiece){
									vMove.add(new Move(px,py,tox,toy));
								}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
									vMove.add(new Move(px,py,tox,toy));
								}
							}else{//jump river
								do{
									tox = tox+dx[d];
									toy = toy+dy[d];
									if (hasSelfPiece(cboard, tox, toy)) continue l2;
								} while(isInsideRiver(tox,toy));
								bIsInsideBoard = isInsideBoard(tox,toy);
								bHasSelfPiece = hasSelfPiece(cboard, tox, toy);
								bHasEnemyPiece = hasEnemyPiece(cboard, tox, toy);
								if (bIsInsideBoard && !bHasSelfPiece){
									if (!bHasEnemyPiece){
										vMove.add(new Move(px,py,tox,toy));
									}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1])){
										vMove.add(new Move(px,py,tox,toy));
									}
								}
							}
						break;
					case 'H'://AnimalChessPieces.CHEEROR: 
					case 'h'://AnimalChessPieces.CHEEROR: 
						if (!bIsInsideRiver){
							if (!bHasEnemyPiece){
								vMove.add(new Move(px,py,tox,toy));
							}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
								vMove.add(new Move(px,py,tox,toy));
							}
						}
						break;
					case 'W'://AnimalChessPieces.WOLF: 
					case 'w'://AnimalChessPieces.WOLF: 
						if (!bIsInsideRiver){
							if (!bHasEnemyPiece){
								vMove.add(new Move(px,py,tox,toy));
							}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
								vMove.add(new Move(px,py,tox,toy));
							}
						}
						break;
					case 'D'://AnimalChessPieces.DOG: 
					case 'd'://AnimalChessPieces.DOG: 
						if (!bIsInsideRiver){
							if (!bHasEnemyPiece){
								vMove.add(new Move(px,py,tox,toy));
							}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
								vMove.add(new Move(px,py,tox,toy));
							}
						}
						break;
					case 'C'://AnimalChessPieces.CAT: 
					case 'c'://AnimalChessPieces.CAT: 
						if (!bIsInsideRiver){
							if (!bHasEnemyPiece){
								vMove.add(new Move(px,py,tox,toy));
							}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
								vMove.add(new Move(px,py,tox,toy));
							}
						}
						break;
					case 'M'://AnimalChessPieces.MOUSE: 
					case 'm'://AnimalChessPieces.MOUSE: 
						if (!bHasEnemyPiece){
							vMove.add(new Move(px,py,tox,toy));
						}else if (canEat(cboard[px-1][py-1],cboard[tox-1][toy-1]) || canEatByPosition(tox,toy)){
							vMove.add(new Move(px,py,tox,toy));
						}
						break;
					}
				}			
			}
		}

		return vMove;
	}

	public Vector<Move> getValidWinMoves(char[][] cBoard, int w, int h) {
		int enemyBaseX = 4;
		int enemyBaseY = 1;
		Vector<Move> vMoves = getValidMoves( cBoard, w, h);
		Vector<Move> vWinMoves = new Vector<Move>();
		for(Move m: vMoves){
			if (m.getToX()==enemyBaseX && m.getToY()==enemyBaseY) {
				vWinMoves.add(m);
			}
		}
		return vWinMoves;
	}

	private boolean canEat(char self, char enemy) {
		boolean bCanEat = false;
		self = (self+"").toUpperCase().charAt(0);
		enemy = (enemy+"").toUpperCase().charAt(0);
		if (self=='E' && enemy=='M'){
			bCanEat = false;
		}else
		if (self=='M' && enemy=='E'){
			bCanEat = true;
		}else{
			int iSelfValue = getPieceValue(self);
			int iEnemyValue = getPieceValue(enemy);
			bCanEat = (iSelfValue >= iEnemyValue);
		}
		return bCanEat;
	}
	
	private boolean canEatByPosition(int tox, int toy) {
		int[] selfBaseX = new int[]{3,4,5};
		int[] selfBaseY = new int[]{9,8,9};
		boolean bCanEat = false;
		for(int i=0;i<3;i++){
			bCanEat |= (selfBaseX[i]==tox && selfBaseY[i]==toy);
		}
		return bCanEat;
	}

	
	private int getPieceValue(char piece){
		int value = 0;
		switch(piece){
			case 'E': //AnimalChessPieces.ELEPHANT: 
			case 'e': //AnimalChessPieces.ELEPHANT: 
				value = 8;
				break;
			case 'L': //AnimalChessPieces.LION: 
			case 'l': //AnimalChessPieces.LION: 
				value = 7;
				break;
			case 'T'://AnimalChessPieces.TIGER: 
			case 't'://AnimalChessPieces.TIGER: 
				value = 6;
				break;
			case 'H'://AnimalChessPieces.CHEEROR: 
			case 'h'://AnimalChessPieces.CHEEROR: 
				value = 5;
				break;
			case 'W'://AnimalChessPieces.WOLF: 
			case 'w'://AnimalChessPieces.WOLF: 
				value = 4;
				break;
			case 'D'://AnimalChessPieces.DOG: 
			case 'd'://AnimalChessPieces.DOG: 
				value = 3;
				break;
			case 'C'://AnimalChessPieces.CAT: 
			case 'c'://AnimalChessPieces.CAT: 
				value = 2;
				break;
			case 'M'://AnimalChessPieces.MOUSE: 
			case 'm'://AnimalChessPieces.MOUSE: 
				value = 1;
				break;
		}
		return value;
	}
	
	@Override
	public int evaluate(char[][] cBoard, int w, int h) {
		int selfValue = 0;
		int enemyValue = 0;
		int selfPosValue = 0;
		int enemyPosValue = 0;
		selfPieceCalculation = "";
		selfPositionCalculation = "";
		enemyPieceCalculation = "";
		enemyPositionCalculation = "";

		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				if (hasSelfPiece(cBoard, x, y)){
					int v1 = getPieceValue(cBoard[x-1][y-1]);
					int v2 = getSelfPositionValue(x, y);
					selfValue += v1;
					selfPosValue += v2;
					selfPieceCalculation += " + " + v1;
					selfPositionCalculation += " + " + v2;
				}else
				if (hasEnemyPiece(cBoard, x, y)){
					int v3 = getPieceValue(cBoard[x-1][y-1]);
					int v4 = getEnemyPositionValue(x, y);
					enemyValue += v3;
					enemyPosValue += v4;					
					enemyPieceCalculation += " + " + v3;
					enemyPositionCalculation += " + " + v4;
				}
			}
		}
		selfPieceValue = ""+selfValue;
		selfPositionValue = ""+selfPosValue;
		enemyPieceValue = ""+enemyValue;
		enemyPositionValue = ""+enemyPosValue;
		
		int v5 = (selfValue - enemyValue) * 10 + (selfPosValue - enemyPosValue);
		totalCalculation = String.format("(%s - %s) * 10 + (%s - %s)", selfPieceValue, enemyPieceValue, selfPositionValue, enemyPositionValue);
		totalValue = ""+v5;
		
		return v5;
	}
	
	private int getSelfPositionValue(int x, int y) {
		int enemyBaseX = 4;
		int enemyBaseY = 1;
		if (x==enemyBaseX && y==enemyBaseY) return 1000;
		if (x==enemyBaseX && y==enemyBaseY+1) return 900;
		if (x==enemyBaseX && y==enemyBaseY-1) return 900;
		if (x==enemyBaseX+1 && y==enemyBaseY) return 900;
		if (x==enemyBaseX-1 && y==enemyBaseY) return 900;
		int wh = 13;
		int dist = Math.abs(x-enemyBaseX) + Math.abs(y-enemyBaseY);
		int posValue = wh - dist;
		return posValue;
	}
	
	private int getEnemyPositionValue(int x, int y) {
		int enemyBaseX = 4;
		int enemyBaseY = 9;
		if (x==enemyBaseX && y==enemyBaseY) return 1000;
		if (x==enemyBaseX && y==enemyBaseY+1) return 900;
		if (x==enemyBaseX && y==enemyBaseY-1) return 900;
		if (x==enemyBaseX+1 && y==enemyBaseY) return 900;
		if (x==enemyBaseX-1 && y==enemyBaseY) return 900;
		int wh = 13;
		int dist = Math.abs(x-enemyBaseX) + Math.abs(y-enemyBaseY);
		int posValue = wh - dist;
		return posValue;
	}
	
	private boolean isInsideSelfBase(int x, int y) {
		int selfBaseX = 4;
		int selfBaseY = 9;
		return ((x == selfBaseX) && (y == selfBaseY));
	}

	
	@Override
	public Move supplementMoveInfo(char[][] cBoard, Move move) {
		Vector<Integer> vList = null;
		if (cBoard!=null){
			vList = getEatPieceList(cBoard, move);
		}
		move.setSuppInteger(vList);
		return move;
	}
	
	private Vector<Integer> getEatPieceList(char[][] cBoard, Move move) {
		Vector<Integer> vResult = null;
		// Check Existing Piece
		char oPiece = cBoard[move.getToX()-1][move.getToY()-1];
		if (oPiece!='.'){
			vResult = new Vector<Integer>();
			vResult.add((int)oPiece);
		}
		return vResult;
	}

	@Override
	public int getNextRaces(int iRaces) {
		int iNextRaces = 0;
		if (iRaces==AnimalChessPieces.RED) iNextRaces = AnimalChessPieces.BLACK;
		if (iRaces==AnimalChessPieces.BLACK) iNextRaces = AnimalChessPieces.RED;
		return iNextRaces;
	}
	
	@Override
	public int getRacesByPiece(char[][] cboard, int x, int y){
		int iRaces = 0;
		if (cboard[x-1][y-1]>='a' && cboard[x-1][y-1]<='z') iRaces = AnimalChessPieces.RED;
		if (cboard[x-1][y-1]>='A' && cboard[x-1][y-1]<='Z') iRaces = AnimalChessPieces.BLACK;
		return iRaces;
	}


	
	public static void main(String[] s){
	}
	
	private static int testGetSelfPositionValue(int x, int y) {
		int enemyBaseX = 4;
		int enemyBaseY = 1;
		if (x==enemyBaseX && y==enemyBaseY) return 1000;
		int wh = 13;
		int dist = Math.abs(x-enemyBaseX) + Math.abs(y-enemyBaseY);
		int posValue = wh - dist;
		return posValue;
	}


}
