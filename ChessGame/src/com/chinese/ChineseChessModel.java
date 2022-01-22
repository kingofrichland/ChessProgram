package com.chinese;

import com.BoardGameModel;

public class ChineseChessModel extends BoardGameModel {

	//ChineseChessModelVO data = null;
	//ChineseChess cc = null;
	
	public ChineseChessModel() {
		super(new ChineseChess(), new ChineseChessModelVO());
	}

	@Override
	public void updateModel() {
		System.out.println("ChineseChessModel : updateModel");
		
		//getBoardGame().getChessBoard().getChessPieces().getPieceList();
		//getBoardGame().getChessBoard().getChessPieces().initialized();
		
		/* *
		 * 
		 * New Logic
		 * 
		 * WHILE ( NOT END OF GAME )
		 *   GET NEXT PLAYER
		 *   WAIT THE PLAYER MAKE THE MOVE
		 *   RECEIVE MOVE INFORMATION
		 *   UPDATE BOARD ACCORDINGLY
		 * END WHILE
		 *
		 * 
		 * For implementation, we will not use while. Instead, we will use STATE to do the logic:
		 * 
		 * IF END OF GAME THEN
		 *    	STATE = END GAME
		 * ELSE
		 *    	STATE = PLAY GAME
		 * END IF
		 * IF PLAYER MAKE THE MOVE THEN
		 * 		STATE = CHECK MOVE
		 * ELSE
		 * 		STATE = WAIT MOVE
		 * END IF
		 * 
		 * ** Note, we dont have to think the action, we only need to due with state!! 
		 *    After determine the state, the related action can be executed, defined as follows:
		 *    
		 * STATE			ACTION
		 * -------------	----------------------------------------
		 * END_GAME			Detach MouseListener, Show Game Over View
		 * CHECK_MOVE		Check if the move is valid, if not valid, STATE = INVALID_MOVE, else STATE = UPDATE_MOVE
		 * INVALID_MOVE		Show Invalid Move Text on screen, STATE = WAIT_MOVE
		 * UPDATE_MOVE		Update Chess Board with new move, show animation if any. Update current player to next player, STATE = PLAY_GAME
		 * PLAY_GAME		Check if the game is over, if game over, STATE = END_GAME, else STATE = WAIT_MOVE
		 * WAIT_MOVE		Update timer if any. Check if player has made a move. If yes, STATE = CHECK_MOVE, else STATE = WAIT_MOVE
		 * START_GAME		Attach MouseListener, 
		 * 
		 * */
		
		//if (state == stateWaitMove) {
			// Update timer if any
			// Check if player has made a move. If yes, STATE = CHECK_MOVE, else STATE = WAIT_MOVE
			//return;
		//}
		
		
		
		/*
		 * Old Logic
		 * */
		/*
		ChineseChessModelVO data = (ChineseChessModelVO)getModelVO();
		int x = data.getX();
		int y = data.getY();
		int tx = data.getTargetX();
		int ty = data.getTargetY();
		
		if (tx!=x){
			int dirX = tx - x;
			x = x + (dirX / 10);
		}
		if (ty!=y){
			int dirY = ty - y;
			y = y + (dirY / 10);
		}
		data.setX(x);
		data.setY(y);
		setModelVO(data);
		*/
	}
	
}
