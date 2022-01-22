package com.animal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import com.BoardGameController;
import com.ChessPiece;
import com.Move;

public class AnimalChessController extends BoardGameController {

	ChessPiece flowChessPiece = null;
	
	public AnimalChessController() {
		super(new AnimalChessModel(), new AnimalChessView());
	}

	@Override
	public MouseListener getBoardGameMouseListener() {
		return new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse Pressed : ("+e.getX()+","+e.getY()+")");
				AnimalChessModelVO data = (AnimalChessModelVO)getBoardGameModel().getModelVO();
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
					//flowChessPiece.setFlow(false);
					int fromX = flowChessPiece.getX();
					int fromY = flowChessPiece.getY();
					int toX = getBoardGameView().getXByGraphicalX(e.getX());
					int toY = getBoardGameView().getYByGraphicalY(e.getY());
					getBoardGameModel().getBoardGame().getPlayers().getCurrPlayer().setMove(new Move(flowChessPiece, fromX,fromY,toX,toY));
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
