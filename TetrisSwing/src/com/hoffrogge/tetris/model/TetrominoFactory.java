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

		switch (typ) {

		case BLOCK:
			return new TetrominoBlock();

		case LANGER:
			return new TetrominoLanger();

		case L:
			return new TetrominoL();

		case UMGEDREHTES_L:
			return new TetrominoUmgedrehtesL();

		case T:
			return new TetrominoT();

		case Z:
			return new TetrominoZ();

		case UMGEDREHTES_Z:
			return new TetrominoUmgedrehtesZ();

		default:
			throw new IllegalStateException("TetrominoTyp " + typ + " ist nicht bekannt!");
		}
	}
}