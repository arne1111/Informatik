package com.hoffrogge.tetris.model;

public class TetrominoLanger extends Tetromino {

	private int kantenLaengeViertelBlock;

	public TetrominoLanger() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 4);
	}

	public TetrominoLanger(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE;
		kantenLaengeViertelBlock = durchmesser;

		viertelBloecke.add(new ViertelBlock(x, y));
		viertelBloecke.add(new ViertelBlock(x, y + kantenLaengeViertelBlock));
		viertelBloecke.add(new ViertelBlock(x, y + kantenLaengeViertelBlock * 2));
		viertelBloecke.add(new ViertelBlock(x, y + kantenLaengeViertelBlock * 3));
	}

}