package com.animal;

import java.util.List;
import java.util.Vector;

import com.ChessBoard;
import com.ChessPiece;
import com.Move;
import com.rule.RuleEngineForFramework;
import com.rule.RuleEngineVO;

public class AnimalChessRuleEngineForFramework extends RuleEngineForFramework {

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
	
	public AnimalChessRuleEngineForFramework(RuleEngineVO vo) {
		super(vo);
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
	
	public boolean isGameOver(RuleEngineVO vo) {
		ChessBoard board = vo.getBoardObj();
		int topTargetBaseX = 4;
		int topTargetBaseY = 9;
		ChessPiece topP = board.getChessPieces().getPieceByXY(topTargetBaseX, topTargetBaseY);
		if (topP!=null && board.isTOP(topP.getPosition())){
				return true;
		}
		int bottomTargetBaseX = 4;
		int bottomTargetBaseY = 1;
		ChessPiece bottomP = board.getChessPieces().getPieceByXY(bottomTargetBaseX, bottomTargetBaseY);
		if (bottomP!=null && !board.isTOP(bottomP.getPosition())){ 
				return true;
		}
		return false;
	}
	
	// Function for players
	
	/*
	 * Lion: Lion can jump over river, both vertical and horizontal
	 * Tiger: Tiger can jump over river, both vertical and horizontal
	 * Mouse: Only Mouse can go into river
	 * No Races required, since I always be the capital letter side
	 * */
	public Vector<Move> getValidMoves(RuleEngineVO vo) {
		int iRaces = vo.getRaces();
		ChessBoard cboard = vo.getBoardObj();
		Vector<Move> vValidMove = new Vector<Move>();
		
		Vector<ChessPiece> vList = cboard.getChessPieces().getPieceList();
		for(ChessPiece p: vList) {
			// To be implemented below
			List<Move> vMove = p.getPossibleMoves(p.getX(), p.getY());
			for(Move m: vMove) {
				if (p.isValidMove(cboard, m.getToX(), m.getToY())) {
					vValidMove.add(m);
				}
			}
		}
		
		return vValidMove;
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

	private boolean hasEnemyPiece(char[][] cboard, int x, int y, int iRaces) {
		return (getRacesByPiece(cboard,x,y)==getNextRaces(iRaces));
	}

	private boolean isBlank(char[][] cboard, int x, int y) {
		return (cboard[x-1][y-1]=='.');
	}

	private boolean hasSelfPiece(char[][] cboard, int x, int y, int iRaces) {
		return (getRacesByPiece(cboard,x,y)==iRaces);
	}
	
	private int getRacesByPiece(char[][] cboard, int x, int y){
		int iRaces = 0;
		if (cboard[x-1][y-1]>='a' && cboard[x-1][y-1]<='z') iRaces = AnimalChessPieces.RED;
		if (cboard[x-1][y-1]>='A' && cboard[x-1][y-1]<='Z') iRaces = AnimalChessPieces.BLACK;
		return iRaces;
	}

	/*
	public void printBoard(char[][] cBoard, int w, int h){
		String str = "";
		for(int y=1; y<=h; y++){
			for(int x=1; x<=w; x++){
				str += cBoard[x-1][y-1];
			}
			str += "\n";
		}
		System.out.println(str);
	}*/

	
	@Override
	public Move supplementMoveInfo(RuleEngineVO vo) {
		ChessBoard boardObj = vo.getBoardObj();
		int iRaces = vo.getRaces();
		Move move = vo.getMove();
		Vector<Integer> vList = null;
		if (boardObj!=null){
			vList = getEatPieceList(boardObj, iRaces, move);
		}
		move.setSuppInteger(vList);
		return move;
	}
	
	private Vector<Integer> getEatPieceList(ChessBoard boardObj, int iRaces, Move move) {
		Vector<Integer> vResult = new Vector<Integer>();
		// Check Existing Piece
		//char oPiece = cBoard[move.getToX()-1][move.getToY()-1];
		ChessPiece oPiece = boardObj.getChessPieces().getPieceByXY(move.getToX(),move.getToY());
		if(oPiece!=null){
			vResult.add(oPiece.getId());
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

}
