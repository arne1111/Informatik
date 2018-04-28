package com.hoffrogge.tetris.model;

public class TetrominoUmgedrehtesZ extends Tetromino {

	public TetrominoUmgedrehtesZ() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 4);
	}

	public TetrominoUmgedrehtesZ(int x, int y) {

		this.x = x;
		this.y = y;

		viertelBloecke.add(new ViertelBlock(x, y));
		viertelBloecke.add(new ViertelBlock(x, y + TetrisKonstanten.BLOCK_BREITE));
		viertelBloecke.add(new ViertelBlock(x + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE));
		viertelBloecke.add(new ViertelBlock(x + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE * 2));
	}

}