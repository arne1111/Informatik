package com.hoffrogge.tetris.model;

import java.awt.Graphics;

public class TetrominoL extends Tetromino {

	private int kantenLaengeViertelBlock;

	public TetrominoL() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 4);
	}

	public TetrominoL(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE;
		kantenLaengeViertelBlock = durchmesser;

		viertelBlock1 = new ViertelBlock(x, y);
		viertelBlock2 = new ViertelBlock(x, y + kantenLaengeViertelBlock);
		viertelBlock3 = new ViertelBlock(x, y + kantenLaengeViertelBlock * 2);
		viertelBlock4 = new ViertelBlock(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock * 2);
	}

	@Override
	public int getHoechstesY() {
		return viertelBlock1.getHoechstesY();
	}

	@Override
	public int getTiefstesY() {
		return viertelBlock3.getTiefstesY();
	}

	@Override
	public int getKanteLinksX() {
		return viertelBlock1.getKanteLinksX();
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
		viertelBlock2.setMittelpunkt(x, y + kantenLaengeViertelBlock);
		viertelBlock3.setMittelpunkt(x, y + kantenLaengeViertelBlock * 2);
		viertelBlock4.setMittelpunkt(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock * 2);

		zeichneViertelBloecke(graphics);
	}
}