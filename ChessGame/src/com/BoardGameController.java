package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import com.player.ComputerPlayer;
import com.rule.RuleEngineVO;

abstract public class BoardGameController {

	boolean bIsStarted = false;
	private BoardGameView boardGameView = null;
	private BoardGameModel boardGameModel = null;
	private boolean running = false;
	private State boardGameState = new StateNull();
	private static int speed = 50; //5000); // 1000 = 1sec; 1000/30 = 1/30sec
	
	
	public BoardGameController(BoardGameModel boardGameModel, BoardGameView boardGameView){
		this.boardGameView = boardGameView;
		this.boardGameModel = boardGameModel;
		boardGameView.addStartButtonListener(getBoardGameStartButtonListener());
		boardGameView.addOptionButtonListener(getBoardGameOptionButtonListener());
		boardGameView.addQuitButtonListener(getBoardGameQuitButtonListener());
		boardGameView.addMouseListener(getBoardGameMouseListener());
		boardGameView.addMouseMotionListener(getBoardGameMouseMotionListener());
		boardGameView.setVisible(true);
	}

	public void startGameLoop() {
		System.out.println("called startGameLoop()");
		if (!running){ // If not running, then start to run loop
			running = true;
			boardGameState = new StateStartGame();
			Thread th = new Thread(){
				public void run(){
					runGameLoop();
				}
			};
			th.start();
		}
		
	}
	
	public void stopGameLoop() {
		System.out.println("called stopGameLoop()");
		running = false;
	}
	
	private void runGameLoop(){
		System.out.println("runGameLoop start: running");
		while(running){
			System.out.println("runGameLoop running ("+boardGameModel.getBoardGame().getPlayers().getCurrPlayer().getName()+",\tiRaces="+boardGameModel.getBoardGame().getPlayers().getCurrPlayer().getRaces()+",\t"+boardGameState.getClass().getName()+")");

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
			boardGameState = boardGameState.executeAction(); // Update State
			boardGameModel.updateModel();
			boardGameView.drawModel(boardGameModel);
			
			try {
				Thread.sleep((int)speed); // 1000 = 1sec; 1000/30 = 1/30sec
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("runGameLoop end");
	}

	public BoardGameView getBoardGameView() {
		return boardGameView;
	}

	public BoardGameModel getBoardGameModel() {
		return boardGameModel;
	}
	
	/* State */
	abstract class State{
		abstract protected State executeAction();
	}
	/*
	* STATE			ACTION
	 * -------------	----------------------------------------
	 * NULL				Do Nothing
	 * END_GAME			Detach MouseListener, Show Game Over View, STATE = NULL
	 * CHECK_MOVE		Check if the move is valid, if not valid, STATE = INVALID_MOVE, else STATE = UPDATE_MOVE
	 * INVALID_MOVE		Show Invalid Move Text on screen, STATE = WAIT_MOVE
	 * UPDATE_MOVE		Update Chess Board with new move, show animation if any. Update current player to next player, STATE = PLAY_GAME
	 * PLAY_GAME		Check if the game is over, if game over, STATE = END_GAME, else STATE = WAIT_MOVE
	 * GET_NEXT_PLAYER	Find the next player to continue play the chess.
	 * WAIT_MOVE		Update timer if any. Check if player has made a move. If yes, STATE = CHECK_MOVE, else STATE = WAIT_MOVE
	 * START_GAME		Attach MouseListener
	 * */
	// NULL				Do Nothing
	class StateNull extends State{
		protected State executeAction(){
			return this;
		}
	}
	// END_GAME			Detach MouseListener, Show Game Over View, STATE = NULL
	class StateEndGame extends State{
		protected State executeAction(){
			boardGameView.sendMessage("End Game");
			boardGameView.removeMouseListener(getBoardGameMouseListener());
			boardGameView.removeMouseMotionListener(getBoardGameMouseMotionListener());
			stopGameLoop();
			return new StateNull();
		}
	}
	// CHECK_MOVE		Check if the move is valid, if not valid, STATE = INVALID_MOVE, else STATE = UPDATE_MOVE
	class StateCheckMove extends State{
		protected State executeAction(){
			ChessBoard board = boardGameModel.getBoardGame().getChessBoard();
			Move move = boardGameModel.getBoardGame().getPlayers().getCurrPlayer().getMove();
			boolean isValid = boardGameModel.getBoardGame().getRuleEngineForFramework().isValidMove(new RuleEngineVO(board, move));
			return (isValid ? new StateUpdateMove() : new StateInvalidMove());
		}
	}
	// INVALID_MOVE		Show Invalid Move Text on screen, STATE = WAIT_MOVE
	class StateInvalidMove extends State{
		protected State executeAction(){
			boardGameView.sendMessage("Invalid Move");
			return new StateWaitMove();
		}
	}
	// UPDATE_MOVE		Update Chess Board with new move, show animation if any. Update current player to next player, STATE = PLAY_GAME
	class StateUpdateMove extends State{
		protected State executeAction(){
			ChessBoard board = boardGameModel.getBoardGame().getChessBoard();
			Move move = boardGameModel.getBoardGame().getPlayers().getCurrPlayer().getMove();
			move = boardGameModel.getBoardGame().getRuleEngineForFramework().supplementMoveInfo(new RuleEngineVO(board, move));
			boolean isMoveFinished = boardGameView.movePiece(move, boardGameModel); // Do animation
			if (isMoveFinished){
				boardGameModel.getBoardGame().getChessBoard().takeMove(move);
				boardGameModel.getBoardGame().getPlayers().getCurrPlayer().setMove(null);
			}
			return (isMoveFinished ? new StatePlayGame() : new StateUpdateMove());
		}
	}
	
	// PLAY_GAME		Check if the game is over, if game over, STATE = END_GAME, else STATE = GET_NEXT_PLAYER
	class StatePlayGame extends State{
		protected State executeAction(){
			ChessBoard board = boardGameModel.getBoardGame().getChessBoard();
			boolean isGameOver = boardGameModel.getBoardGame().getRuleEngineForFramework().isGameOver(new RuleEngineVO(board));
			return (isGameOver ? new StateEndGame() : new StateGetNextPlayer());
		}
	}
	
	// GET_NEXT_PLAYER	
	class StateGetNextPlayer extends State{
		protected State executeAction(){
			boardGameModel.getBoardGame().getPlayers().turnToNextPlayer();
			boardGameModel.getBoardGame().getPlayers().getCurrPlayer().setMove(null);
			return new StateWaitMove();
		}
	}
	
	// WAIT_MOVE		Update timer if any. Check if player has made a move. If yes, STATE = CHECK_MOVE, else STATE = WAIT_MOVE
	class StateWaitMove extends State{
		protected State executeAction(){
			Player currPlayer = boardGameModel.getBoardGame().getPlayers().getCurrPlayer();
			Move move = currPlayer.getMove();
			boolean hasMoved = move!=null;
			
			if (!hasMoved && currPlayer instanceof ComputerPlayer){
				ComputerPlayer cplayer = (ComputerPlayer)currPlayer;
				ChessBoard board = boardGameModel.getBoardGame().getChessBoard();
				cplayer.calcAndSetMove(board);
			}
			return (hasMoved ? new StateCheckMove() : this);
		}
	}
	
	// START_GAME		Attach MouseListener
	class StateStartGame extends State{
		protected State executeAction(){
			//ChessBoard board = boardGameModel.getBoardGame().getChessBoard();
			//board.getChessPieces().initialized();
			boardGameView.addMouseListener(getBoardGameMouseListener());
			boardGameView.addMouseMotionListener(getBoardGameMouseMotionListener());
			return new StateWaitMove();
		}
	}

	public ActionListener getBoardGameQuitButtonListener() {
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Quit Clicked");
				stopGameLoop();
				//System.exit(0);
			}
		};
	}
	
	public ActionListener getBoardGameStartButtonListener() {
		
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Start Clicked");
				if(!bIsStarted){
					bIsStarted = true;
					System.out.println("Start Game");
					getBoardGameModel().getBoardGame().startGame();
					//getBoardGameView(); // Update the view to reflect the chess board 
					startGameLoop();
				}else{
					System.out.println("Game already started, do nothing.");
				}
			}
		};
	}

	public ActionListener getBoardGameOptionButtonListener() {
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Animal : Option Button");
				if (running)
					stopGameLoop();
				else
					startGameLoop();
			}
		};
	}
	
	public MouseMotionListener getBoardGameMouseMotionListener() {
		// TODO Auto-generated method stub
		return new MouseMotionAdapter(){};
	}
	
	abstract public MouseListener getBoardGameMouseListener();
	
}
