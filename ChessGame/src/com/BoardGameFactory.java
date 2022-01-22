package com;

import com.animal.AnimalChess;
import com.animal.AnimalChessController;
import com.checker.Checker;
import com.checker.CheckerController;
import com.chinese.ChineseChess;
import com.chinese.ChineseChessController;
import com.reversi.Reversi;
import com.reversi.ReversiController;

public class BoardGameFactory {
	public BoardGameController createBoardGameController(String boardgame){
		BoardGameController res = null;
		if(ChineseChess.ChessInd.equalsIgnoreCase(boardgame)){
			res = new ChineseChessController(); // Just need to initialize, and it will work fine
		}else
		if(AnimalChess.ChessInd.equalsIgnoreCase(boardgame)){
			res = new AnimalChessController();
		}else
		if(Checker.ChessInd.equalsIgnoreCase(boardgame)){
			res = new CheckerController();
		}else
		if(Reversi.ChessInd.equalsIgnoreCase(boardgame)){
			res = new ReversiController();
		}else{
			throw new IllegalArgumentException("No such board game: "+boardgame);
		}
		return res;
	}

}
