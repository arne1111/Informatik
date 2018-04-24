package com.hoffrogge.tetris.model;

import java.awt.Graphics;
import java.util.Random;

import com.hoffrogge.lehreinheit03.Farbe;
import com.hoffrogge.lehreinheit04.GeometrischeFigur;
import com.hoffrogge.lehreinheit04.Punkt;

public class ViertelBlock implements GeometrischeFigur {

	private int x;
	private int y;
	private int kantenLaengeViertelBlock;
	private Farbe linienFarbe;
	private Farbe fuellFarbe;

	public ViertelBlock() {

		x = TetrisKonstanten.BLOCK_BREITE;
		y = TetrisKonstanten.BLOCK_BREITE;
		kantenLaengeViertelBlock = TetrisKonstanten.BLOCK_BREITE;

		Random r = new Random();

		fuellFarbe = new Farbe(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		linienFarbe = new Farbe(r.nextInt(255), r.nextInt(255), r.nextInt(255));
	}

	@Override
	public void setMittelpunkt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public Punkt getMittelPunkt() {
		return new Punkt(x, y);
	}

	@Override
	public void setDurchmesser(int d) {
		this.kantenLaengeViertelBlock = d;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		this.linienFarbe = farbe;
	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		if (linienFarbe != null)
			graphics.setColor(linienFarbe.konvertiereZuColor());

		graphics.drawRect(x, y, kantenLaengeViertelBlock, kantenLaengeViertelBlock);

		graphics.setColor(fuellFarbe.konvertiereZuColor());

		graphics.fillRect(x + 1, y + 1, kantenLaengeViertelBlock - 1, kantenLaengeViertelBlock - 1);

	}

	@Override
	public int getHoechstesY() {
		return y;
	}

	@Override
	public int getTiefstesY() {
		return y + kantenLaengeViertelBlock;
	}

	@Override
	public int getKanteLinksX() {
		return x;
	}

	@Override
	public int getKanteRechtsX() {
		return x + kantenLaengeViertelBlock;
	}
}