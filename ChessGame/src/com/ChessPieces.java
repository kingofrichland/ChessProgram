package com;
import java.awt.Color;
import java.util.Arrays;
import java.util.Vector;

abstract public class ChessPieces {
	String name = "";
	Vector<ChessPiece> pieces = new Vector<ChessPiece>();
	public Players players = null;
	
	public ChessPieces(String name, Players players){
		this.name = name;
		this.players = players;
		this.pieces = fillPieceId(initialized());
		if (this.pieces==null) throw new IllegalStateException("ChessPieces: "+name+" cannot be initialized");
	}
	public String toString(){
		String res = name;
		for(ChessPiece p: pieces){
			res += "\n Preparing "+p;
		}
		return res;
	}
	public Vector<ChessPiece> getPieceList() {
		return pieces;
	}
	public void setPieceList(Vector<ChessPiece> pieces) {
		this.pieces = fillPieceId(pieces);
	}
	
	private Vector<ChessPiece> fillPieceId(Vector<ChessPiece> pieces) {
		Vector<ChessPiece> res = new Vector<ChessPiece>();
		for(int i=0; i<pieces.size(); i++){
			ChessPiece p = pieces.get(i);
			p.setId(i);
			res.add(p);
		}
		return res;
	}
	/*
	public void resetAvailableRaces() {
		Arrays.fill(racesUsed, false);
	}
	*/
	/*
	public int getAvailableRaces() {
		int res = -1;
		for(int i=0; racesList!=null && i<racesList.length; i++){
			if(racesUsed[i]) continue;
			res = racesList[i];
			break;
		}
		return res;
	}
	*/

	/*
	 * Get a list of piece with specified iRaces, if iRaces is -1, then return all
	 * */
	public Vector<ChessPiece> getPieceListByRaces(int iRaces) {
		Vector<ChessPiece> res = new Vector<ChessPiece>();
		for(ChessPiece p : pieces){
			if (p.getRaces() == iRaces){
				res.add(p);
			}
		}
		return res;
	}
	
	public Vector<ChessPiece> getPieceListByPosition(int iPosition) {
		Vector<ChessPiece> res = new Vector<ChessPiece>();
		for(ChessPiece p : pieces){
			if (p.getPosition() == iPosition){
				res.add(p);
			}
		}
		return res;
	}
	
	/*public boolean markRacesUsed(int iRaces) {
		boolean bOK = false;
		for(int i=0; racesList!=null && i<racesList.length; i++){
			if(racesUsed[i]) continue;
			if (racesList[i] == iRaces){
				racesUsed[i]=true;
				bOK = true;
			}
			break;
		}
		return bOK;
	}*/
	
	/*
	public int[] getRacesList() {
		return racesList;
	}
	public void setRacesList(int[] racesList) {
		this.racesList = racesList;
	}*/
	
	public void updatePieceXY(int id, int toX, int toY) {
		ChessPiece p2 = getPiece(id);
		p2.setX(toX);
		p2.setY(toY);
	}
	
	public void updatePieceRaces(int id, int races) {
		ChessPiece p2 = getPiece(id);
		p2.setRaces(races);
	}
	
	public void addPieceXY(ChessPiece p, int toX, int toY) {
		p.setX(toX);
		p.setY(toY);
		pieces.add(p);
		this.pieces = fillPieceId(pieces); // reload piece id after remove id
	}
	
	public ChessPiece getPieceByXY(int x, int y){
		for(ChessPiece p : pieces){
			if (p.getX() == x && p.getY() == y){
				return p;
			}
		}
		return null;
	}
	
	public ChessPiece getPiece(int id) {
		return pieces.get(id);
	}
	
	public void setPiece(int id, ChessPiece p) {
		//pieces.set(id, p);
		ChessPiece p2 = getPiece(id);
		p2.setName(p.getName());
		p2.setId(p.getId());
		p2.setX(p.getX());
		p2.setY(p.getY());
		p2.setFlowX(p.getFlowX());
		p2.setFlowY(p.getFlowY());
		p2.setFlow(p.isFlow());
		p2.setWord(p.getWord());
		p2.setRaces(p.getRaces());
		//p2.setColor(p.getColor());
		
	}
	
	public void deletePiece(int id) {
		pieces.remove(id);
		this.pieces = fillPieceId(pieces); // reload piece id after remove id
	}
	
	abstract public Vector<ChessPiece> initialized();
	abstract public Color getColorByRaces(int iRaces);
	
}
