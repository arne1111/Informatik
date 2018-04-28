package com.hoffrogge.tetris.model;

public class TetrominoBlock extends Tetromino {

	private int kantenLaengeViertelBlock;

	public TetrominoBlock() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 2);
	}

	public TetrominoBlock(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE * 2;
		kantenLaengeViertelBlock = durchmesser / 2;

		viertelBloecke.add(new ViertelBlock(x, y));
		viertelBloecke.add(new ViertelBlock(x + kantenLaengeViertelBlock, y));
		viertelBloecke.add(new ViertelBlock(x, y + kantenLaengeViertelBlock));
		viertelBloecke.add(new ViertelBlock(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock));

	}

}