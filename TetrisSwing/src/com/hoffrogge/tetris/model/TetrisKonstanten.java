package com.hoffrogge.tetris.model;

public class TetrisKonstanten {

	private TetrisKonstanten() {
	}

	public static final boolean MUSIK_AN = false;

	public static final int BLOCK_BREITE = 40;

	public static final int ABSTAND = BLOCK_BREITE / 8;

	public static final int SPIELFELD_BREITE = BLOCK_BREITE * 10;
	public static final int SPIELFELD_HOEHE = BLOCK_BREITE * 20;
	public static final int SPIELFELD_POS_X = ABSTAND;
	public static final int SPIELFELD_POS_Y = ABSTAND;

	public static final int VORSCHAU_BREITE = BLOCK_BREITE * 5;
	public static final int VORSCHAU_HOEHE = BLOCK_BREITE * 6;
	public static final int VORSCHAU_POS_X = SPIELFELD_BREITE + ABSTAND * 2;
	public static final int VORSCHAU_POS_Y = ABSTAND;

	public static final int LABEL_LINKS_POS_X = SPIELFELD_BREITE + ABSTAND * 2;
	public static final int LABEL_LINKS_POS_Y = VORSCHAU_HOEHE + BLOCK_BREITE;

	public static final int LABEL_RECHTS_POS_X = SPIELFELD_BREITE + VORSCHAU_BREITE / 2;
	public static final int LABEL_RECHTS_POS_Y = VORSCHAU_HOEHE + BLOCK_BREITE * 2;

	public static final int FENSTER_BREITE = SPIELFELD_BREITE + VORSCHAU_BREITE + BLOCK_BREITE;
	public static final int FENSTER_HOEHE = SPIELFELD_HOEHE + BLOCK_BREITE;

	public static final int SPIEL_GESCHWINDIGKEIT = 500;
	public static final int SPIEL_GESCHWINDIGKEIT_MIN = 50;
	public static final int TETROMINO_FALL_HOEHE = BLOCK_BREITE;

	public static final Farbe HINTERGRUND = new Farbe(251, 246, 242);
	public static final Farbe VORDERGRUND = new Farbe(250, 250, 250);
	public static final Farbe TEXT = new Farbe(55, 63, 81);
	public static final Farbe RAHMEN = new Farbe(141, 167, 190);
	public static final Farbe AKZENT = new Farbe(200, 70, 48);

}