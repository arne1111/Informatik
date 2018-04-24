package com.hoffrogge.tetris.model;

import java.awt.Graphics;

import com.hoffrogge.lehreinheit03.Farbe;
import com.hoffrogge.lehreinheit04.GeometrischeFigur;
import com.hoffrogge.lehreinheit04.Punkt;

public class TetrominoBlock implements GeometrischeFigur {

	int durchmesser = 10;
	Farbe farbe = new Farbe(0, 0, 0);
	int x;
	int y;

	public TetrominoBlock(int x, int y) {

		this.x = x;
		this.y = y;
	}

	@Override
	public void setDurchmesser(int d) {
		this.durchmesser = d;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		this.farbe = farbe;
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

		if (farbe != null)
			graphics.setColor(farbe.konvertiereZuColor());

		graphics.drawRect(x - kantenLaengeViertelBlock, y - kantenLaengeViertelBlock, kantenLaengeViertelBlock,
				kantenLaengeViertelBlock);

		graphics.drawRect(x, y - kantenLaengeViertelBlock, kantenLaengeViertelBlock, kantenLaengeViertelBlock);

		graphics.drawRect(x - kantenLaengeViertelBlock, y, kantenLaengeViertelBlock, kantenLaengeViertelBlock);

		graphics.drawRect(x, y, kantenLaengeViertelBlock, kantenLaengeViertelBlock);

	}

	@Override
	public Punkt getMittelPunkt() {
		return new Punkt(x, y);
	}
}