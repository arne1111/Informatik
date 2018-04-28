package com.hoffrogge.tetris.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Tetromino implements GeometrischeFigur {

	int durchmesser;
	int x;
	int y;
	Farbe linienFarbe;

	List<ViertelBlock> viertelBloecke = new ArrayList<>(4);

	public Tetromino() {
		super();
	}

	public boolean faelltAuf(Tetromino gefallenerStein) {

		if (viertelBloecke.isEmpty())
			return false;

		List<ViertelBlock> gefalleneViertelBloecke = gefallenerStein.getViertelBloecke();

		for (ViertelBlock fallenderBlock : viertelBloecke)
			for (ViertelBlock gefallenerBlock : gefalleneViertelBloecke)
				if (fallenderBlock.getX() == gefallenerBlock.getX()
						&& fallenderBlock.getTiefstesY() == gefallenerBlock.getHoechstesY())
					return true;

		return false;
	}

	private List<ViertelBlock> getViertelBloecke() {
		return Collections.unmodifiableList(viertelBloecke);
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

	@Override
	public int getHoechstesY() {

		int hoechstesY = TetrisKonstanten.SPIELFELD_HOEHE;

		for (ViertelBlock block : viertelBloecke)
			if (block.getHoechstesY() < hoechstesY)
				hoechstesY = block.getHoechstesY();

		return hoechstesY;
	}

	@Override
	public int getTiefstesY() {

		int tiefstesY = 0;

		for (ViertelBlock block : viertelBloecke)
			if (block.getTiefstesY() > tiefstesY)
				tiefstesY = block.getTiefstesY();

		return tiefstesY;
	}

	@Override
	public int getKanteLinksX() {

		int kanteLinksX = TetrisKonstanten.SPIELFELD_BREITE;

		for (ViertelBlock block : viertelBloecke)
			if (block.getKanteLinksX() < kanteLinksX)
				kanteLinksX = block.getKanteLinksX();

		return kanteLinksX;
	}

	@Override
	public int getKanteRechtsX() {

		int kanteRechtsX = 0;

		for (ViertelBlock block : viertelBloecke)
			if (block.getKanteRechtsX() > kanteRechtsX)
				kanteRechtsX = block.getKanteRechtsX();

		return kanteRechtsX;
	}

	@Override
	public void zeichnen(Graphics graphics) {

		if (graphics == null)
			return;

		zeichneViertelBloecke(graphics);
	}

	void zeichneViertelBloecke(Graphics graphics) {

		if (viertelBloecke.isEmpty())
			return;

		if (linienFarbe == null)
			linienFarbe = new Farbe(0, 0, 0);

		Farbe fuellFarbe = viertelBloecke.get(0).getFuellFarbe();

		for (ViertelBlock block : viertelBloecke) {

			block.setLinienFarbe(linienFarbe);
			block.setFuellFarbe(fuellFarbe);
			block.zeichnen(graphics);
		}
	}

	public void falle() {

		if (viertelBloecke.isEmpty())
			return;

		for (ViertelBlock block : viertelBloecke) {

			block.setY(block.getY() + TetrisKonstanten.TETROMINO_FALL_HOEHE);
		}
	}
}