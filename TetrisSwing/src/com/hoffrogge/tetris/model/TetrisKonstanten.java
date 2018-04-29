package com.hoffrogge.tetris.model;

public class TetrisKonstanten {

	private TetrisKonstanten() {
	}

	public static final int BLOCK_BREITE = 40;
	public static final int SPIELFELD_BREITE = BLOCK_BREITE * 10;
	public static final int SPIELFELD_HOEHE = BLOCK_BREITE * 20;

	public static final int SPIEL_GESCHWINDIGKEIT = 500;
	public static final int TETROMINO_FALL_HOEHE = BLOCK_BREITE;
}