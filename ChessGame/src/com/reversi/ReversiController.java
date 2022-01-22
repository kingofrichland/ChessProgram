package com.reversi;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import com.BoardGameController;
import com.ChessPiece;
import com.Move;
import com.Player;

public class ReversiController extends BoardGameController {

	ChessPiece flowChessPiece = null;
	
	public ReversiController() {
		super(new ReversiModel(), new ReversiView());
	}

	@Override
	public MouseListener getBoardGameMouseListener() {
		return new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				int toX = getBoardGameView().getXByGraphicalX(e.getX());
				int toY = getBoardGameView().getYByGraphicalY(e.getY());
				System.out.printf("Mouse Pressed : (%2d,%2d) -> Reversi XY : (%2d,%2d)\n",e.getX(),e.getY(),toX,toY);
				Player player = getBoardGameModel().getBoardGame().getPlayers().getCurrPlayer();
				player.setMove(new Move(new Piece(player.getRaces(), toX, toY), toX, toY));

			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		}; 
	}
	
	/*
	public MouseMotionListener getBoardGameMouseMotionListener() {
		// TODO Auto-generated method stub
		return new MouseMotionAdapter(){

			@Override
			public void mouseDragged(MouseEvent e) {
				int toX = getBoardGameView().getX(e.getX());
				int toY = getBoardGameView().getY(e.getY());
				System.out.printf("Mouse Dragged : (%2d,%2d) -> Reversi XY : (%2d,%2d)\n",e.getX(),e.getY(),toX,toY);
			}
			
		};
	}
	*/


}
