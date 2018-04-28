package com.hoffrogge.tetris.model;

import java.awt.Graphics;

public abstract class Tetromino implements GeometrischeFigur {

	int durchmesser;
	int x;
	int y;
	Farbe linienFarbe;

	ViertelBlock viertelBlock1;
	ViertelBlock viertelBlock2;
	ViertelBlock viertelBlock3;
	ViertelBlock viertelBlock4;

	public Tetromino() {
		super();
	}

	public boolean faelltAuf(GeometrischeFigur gefallenerStein) {

		int hoechstesY = gefallenerStein.getHoechstesY();
		int kanteLinksX = gefallenerStein.getKanteLinksX();
		int kanteRechtsX = gefallenerStein.getKanteRechtsX();

		if (getTiefstesY() >= hoechstesY && kanteLinksX < getKanteRechtsX() && kanteRechtsX > getKanteLinksX())
			return true;

		return false;
	}

	@Override
	public void setDurchmesser(int d) {
		this.durchmesser = d;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		this.linienFarbe = farbe;
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

	void zeichneViertelBloecke(Graphics graphics) {

		if (linienFarbe == null)
			linienFarbe = new Farbe(0, 0, 0);

		viertelBlock1.setLinienFarbe(linienFarbe);
		viertelBlock2.setLinienFarbe(linienFarbe);
		viertelBlock3.setLinienFarbe(linienFarbe);
		viertelBlock4.setLinienFarbe(linienFarbe);

		Farbe fuellFarbe = viertelBlock1.getFuellFarbe();
		viertelBlock2.setFuellFarbe(fuellFarbe);
		viertelBlock3.setFuellFarbe(fuellFarbe);
		viertelBlock4.setFuellFarbe(fuellFarbe);

		viertelBlock1.zeichnen(graphics);
		viertelBlock2.zeichnen(graphics);
		viertelBlock3.zeichnen(graphics);
		viertelBlock4.zeichnen(graphics);
	}
}