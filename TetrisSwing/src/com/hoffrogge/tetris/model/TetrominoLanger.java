package com.hoffrogge.tetris.model;

import java.awt.Graphics;

import com.hoffrogge.lehreinheit04.GeometrischeFigur;

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

		viertelBlock1 = new ViertelBlock(x, y);
		viertelBlock2 = new ViertelBlock(x, y + kantenLaengeViertelBlock);
		viertelBlock3 = new ViertelBlock(x, y + kantenLaengeViertelBlock * 2);
		viertelBlock4 = new ViertelBlock(x, y + kantenLaengeViertelBlock * 3);
	}

	@Override
	public int getHoechstesY() {
		return viertelBlock1.getHoechstesY() - TetrisKonstanten.BLOCK_BREITE;
	}

	@Override
	public int getTiefstesY() {
		return viertelBlock4.getTiefstesY();
	}

	@Override
	public int getKanteLinksX() {
		return viertelBlock1.getKanteLinksX();
	}

	@Override
	public int getKanteRechtsX() {
		return viertelBlock1.getKanteRechtsX();
	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		viertelBlock1.setMittelpunkt(x, y);
		viertelBlock2.setMittelpunkt(x, y + kantenLaengeViertelBlock);
		viertelBlock3.setMittelpunkt(x, y + kantenLaengeViertelBlock * 2);
		viertelBlock4.setMittelpunkt(x, y + kantenLaengeViertelBlock * 3);

		zeichneViertelBloecke(graphics);
	}

	@Override
	public boolean faelltAuf(GeometrischeFigur gefallenerStein) {

		int hoechstesY = gefallenerStein.getHoechstesY();
		int kanteLinksX = gefallenerStein.getKanteLinksX();
		int kanteRechtsX = gefallenerStein.getKanteRechtsX();

		if (getTiefstesY() >= hoechstesY && kanteLinksX < getKanteRechtsX() && kanteRechtsX > getKanteLinksX())
			return true;

		return false;
	}
}