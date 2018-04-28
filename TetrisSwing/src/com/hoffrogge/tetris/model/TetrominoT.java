package com.hoffrogge.tetris.model;

import java.awt.Graphics;

public class TetrominoT extends Tetromino {

	public TetrominoT() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 2);
	}

	public TetrominoT(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE * 3;

		viertelBlock1 = new ViertelBlock(x, y);
		viertelBlock2 = new ViertelBlock(x, y + TetrisKonstanten.BLOCK_BREITE);
		viertelBlock3 = new ViertelBlock(x - TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE);
		viertelBlock4 = new ViertelBlock(x + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE);
	}

	@Override
	public int getHoechstesY() {
		return viertelBlock1.getHoechstesY();
	}

	@Override
	public int getTiefstesY() {
		return viertelBlock2.getTiefstesY();
	}

	@Override
	public int getKanteLinksX() {
		return viertelBlock3.getKanteLinksX();
	}

	@Override
	public int getKanteRechtsX() {
		return viertelBlock4.getKanteRechtsX();
	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		viertelBlock1.setMittelpunkt(x, y);
		viertelBlock2.setMittelpunkt(x, y + TetrisKonstanten.BLOCK_BREITE);
		viertelBlock3.setMittelpunkt(x - TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE);
		viertelBlock4.setMittelpunkt(x + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE);

		zeichneViertelBloecke(graphics);
	}
}