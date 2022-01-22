package com.chinese;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;

import com.BoardGameController;
import com.ChessPiece;

public class ChineseChessController extends BoardGameController {
	
	
	ChessPiece flowChessPiece = null;
	
	public ChineseChessController() {
		super(new ChineseChessModel(), new ChineseChessView());
	}

	/*
	@Override
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
	}*/

	@Override
	public ActionListener getBoardGameOptionButtonListener() {
		return new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Option Clicked");
			}
		};
	}

	@Override
	public MouseListener getBoardGameMouseListener() {
		return new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed : ("+e.getX()+","+e.getY()+")");
				ChineseChessModelVO data = (ChineseChessModelVO)getBoardGameModel().getModelVO();
				data.setTargetXY(e.getX(), e.getY());
				getBoardGameModel().setModelVO(data);
				
				flowChessPiece = getBoardGameView().getPiece(getBoardGameModel(), e.getX(),e.getY());
				if (flowChessPiece!=null){
					System.out.println("Selected Piece : "+flowChessPiece.getClass().getName()+"; color:"+flowChessPiece.getRaces());
					flowChessPiece.setFlow(true);
					flowChessPiece.setFlowX(e.getX());
					flowChessPiece.setFlowY(e.getY());
					
				}

			}
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse Released : ("+e.getX()+","+e.getY()+")");
				if (flowChessPiece!=null){
					flowChessPiece.setFlow(false);
					
					flowChessPiece.setX(getBoardGameView().getXByGraphicalX(e.getX()));
					flowChessPiece.setY(getBoardGameView().getYByGraphicalY(e.getY()));
					
					flowChessPiece = null;
					
				}
			}
		}; 
	}

	@Override
	public MouseMotionListener getBoardGameMouseMotionListener() {
		// TODO Auto-generated method stub
		return new MouseMotionAdapter(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Dragged : ("+e.getX()+","+e.getY()+")");
				if (flowChessPiece!=null){
					flowChessPiece.setFlowX(e.getX());
					flowChessPiece.setFlowY(e.getY());
				}
			}
			
		};
	}


}
