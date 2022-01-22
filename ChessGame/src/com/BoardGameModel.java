package com;

abstract public class BoardGameModel {
	
	private ModelVO modelVO = null;
	private BoardGame boardGame = null;
	
	public BoardGameModel(BoardGame boardGame, ModelVO modelVO){
		this.boardGame = boardGame;
		this.modelVO = modelVO;
	}

	public ModelVO getModelVO() {
		return modelVO;
	}

	public void setModelVO(ModelVO modelVO) {
		this.modelVO = modelVO;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}

	abstract public void updateModel();
}
