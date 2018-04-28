package com.hoffrogge.tetris.model;

import java.awt.Graphics;

import com.hoffrogge.lehreinheit04.GeometrischeFigur;

public class TetrominoBlock extends Tetromino {

	private int kantenLaengeViertelBlock;

	public TetrominoBlock() {
		this(TetrisKonstanten.SPIELFELD_BREITE / 2, -TetrisKonstanten.BLOCK_BREITE * 2);
	}

	public TetrominoBlock(int x, int y) {

		this.x = x;
		this.y = y;

		durchmesser = TetrisKonstanten.BLOCK_BREITE * 2;
		kantenLaengeViertelBlock = durchmesser / 2;

		viertelBlock1 = new ViertelBlock(x, y);
		viertelBlock2 = new ViertelBlock(x + kantenLaengeViertelBlock, y);
		viertelBlock3 = new ViertelBlock(x, y + kantenLaengeViertelBlock);
		viertelBlock4 = new ViertelBlock(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock);

	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		viertelBlock1.setMittelpunkt(x, y);
		viertelBlock2.setMittelpunkt(x + kantenLaengeViertelBlock, y);
		viertelBlock3.setMittelpunkt(x, y + kantenLaengeViertelBlock);
		viertelBlock4.setMittelpunkt(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock);

		zeichneViertelBloecke(graphics);
	}

	@Override
	public int getHoechstesY() {
		return y - durchmesser / 2;
	}

	@Override
	public int getTiefstesY() {
		return y + durchmesser / 2;
	}

	@Override
	public int getKanteLinksX() {
		return x;
	}

	@Override
	public int getKanteRechtsX() {
		return x + durchmesser;
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