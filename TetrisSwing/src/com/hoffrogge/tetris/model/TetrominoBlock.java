package com.hoffrogge.tetris.model;

import java.awt.Graphics;

import com.hoffrogge.lehreinheit03.Farbe;
import com.hoffrogge.lehreinheit04.GeometrischeFigur;
import com.hoffrogge.lehreinheit04.Punkt;

public class TetrominoBlock implements GeometrischeFigur {

	private int durchmesser = TetrisKonstanten.BLOCK_BREITE * 2;
	private int x;
	private int y;
	private ViertelBlock viertelBlock;

	public TetrominoBlock(int x, int y) {

		this.x = x;
		this.y = y;

		viertelBlock = new ViertelBlock();
	}

	@Override
	public void setDurchmesser(int d) {
		this.durchmesser = d;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
	}

	@Override
	public void setMittelpunkt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		int kantenLaengeViertelBlock = durchmesser / 2;

		viertelBlock.setMittelpunkt(x, y);
		viertelBlock.zeichnen(graphics);

		viertelBlock.setMittelpunkt(x + kantenLaengeViertelBlock, y);
		viertelBlock.zeichnen(graphics);

		viertelBlock.setMittelpunkt(x, y + kantenLaengeViertelBlock);
		viertelBlock.zeichnen(graphics);

		viertelBlock.setMittelpunkt(x + kantenLaengeViertelBlock, y + kantenLaengeViertelBlock);
		viertelBlock.zeichnen(graphics);
	}

	@Override
	public Punkt getMittelPunkt() {
		return new Punkt(x, y);
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
		return x - durchmesser / 2;
	}

	@Override
	public int getKanteRechtsX() {
		return x + durchmesser / 2;
	}
}