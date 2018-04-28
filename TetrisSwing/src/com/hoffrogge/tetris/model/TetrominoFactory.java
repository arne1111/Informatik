package com.hoffrogge.tetris.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TetrominoFactory {

	private static final List<TetrominoTyp> TYPEN = Collections.unmodifiableList(Arrays.asList(TetrominoTyp.values()));
	private static final int ANZAHL = TYPEN.size();
	private static final Random ZUFALL = new Random();

	private TetrominoFactory() {
		/* diese Factory hat nur statische Methoden */
	}

	public static Tetromino erstelleZufaelligenTetromino() {

		TetrominoTyp tetrominoTyp = TYPEN.get(ZUFALL.nextInt(ANZAHL));

		return erstelleTetromino(tetrominoTyp);
	}

	public static Tetromino erstelleTetromino(TetrominoTyp typ) {

		Random r = new Random();

		int multiplikator = TetrisKonstanten.SPIELFELD_BREITE / TetrisKonstanten.BLOCK_BREITE;
		int x = (r.nextInt(multiplikator)) * TetrisKonstanten.BLOCK_BREITE;
		int y = -TetrisKonstanten.BLOCK_BREITE * 4;

		switch (typ) {

		case BLOCK:
			return new TetrominoBlock(Math.max(0, x - TetrisKonstanten.BLOCK_BREITE), y);

		case LANGER:
			return new TetrominoLanger(x, y);

		case L:
			return new TetrominoL(Math.max(0, x - TetrisKonstanten.BLOCK_BREITE), y);

		case UMGEDREHTES_L:
			return new TetrominoUmgedrehtesL(Math.max(0, x - TetrisKonstanten.BLOCK_BREITE), y);

		default:
			throw new IllegalStateException("TetrominoTyp " + typ + " ist nicht bekannt!");
		}
	}
}