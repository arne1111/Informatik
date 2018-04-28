package com.hoffrogge.tetris.model;

public class TetrominoT extends Tetromino {

	public TetrominoT() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 2);
	}

	public TetrominoT(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE * 3;

		viertelBloecke.add(new ViertelBlock(x, y));
		viertelBloecke.add(new ViertelBlock(x, y + TetrisKonstanten.BLOCK_BREITE));
		viertelBloecke.add(new ViertelBlock(x - TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE));
		viertelBloecke.add(new ViertelBlock(x + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE));
	}

}