package com.hoffrogge.tetris.model.tetromino;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;
import com.hoffrogge.tetris.model.TetrisKonstanten;

public abstract class Tetromino implements TetrominoGeometrie {

	int durchmesser;
	int x;
	int y;
	Farbe linienFarbe;

	protected List<ViertelBlock> viertelBloecke = new ArrayList<>(4);

	public List<ViertelBlock> getViertelBloecke() {
		return viertelBloecke;
	}

	/* gegen den Uhrzeigersinn */
	public void rotiereNachLinks() {

		if (viertelBloecke.size() != 4)
			throw new IllegalStateException("Der Tetromino hat keine vier Bloecke!");

		ViertelBlock zweiterBlock = viertelBloecke.get(1);

		int xMitte = zweiterBlock.getX();
		int yMitte = zweiterBlock.getY();
		int breite = TetrisKonstanten.BLOCK_BREITE;

		/* 4x4 Matrix */
		Punkt[][] tetrominoMatrix = new Punkt[4][4];

		tetrominoMatrix[0][0] = new Punkt(xMitte - breite, yMitte - breite);
		tetrominoMatrix[0][1] = new Punkt(xMitte, yMitte - breite);
		tetrominoMatrix[0][2] = new Punkt(xMitte + breite, yMitte - breite);
		tetrominoMatrix[0][3] = new Punkt(xMitte + breite * 2, yMitte - breite);

		tetrominoMatrix[1][0] = new Punkt(xMitte - breite, yMitte);
		tetrominoMatrix[1][1] = new Punkt(xMitte, yMitte);
		tetrominoMatrix[1][2] = new Punkt(xMitte + breite, yMitte);
		tetrominoMatrix[1][3] = new Punkt(xMitte + breite * 2, yMitte);

		tetrominoMatrix[2][0] = new Punkt(xMitte - breite, yMitte + breite);
		tetrominoMatrix[2][1] = new Punkt(xMitte, yMitte + breite);
		tetrominoMatrix[2][2] = new Punkt(xMitte + breite, yMitte + breite);
		tetrominoMatrix[2][3] = new Punkt(xMitte + breite * 2, yMitte + breite);

		tetrominoMatrix[3][0] = new Punkt(xMitte - breite, yMitte + breite * 2);
		tetrominoMatrix[3][1] = new Punkt(xMitte, yMitte + breite * 2);
		tetrominoMatrix[3][2] = new Punkt(xMitte + breite, yMitte + breite * 2);
		tetrominoMatrix[3][3] = new Punkt(xMitte + breite * 2, yMitte + breite * 2);

		for (ViertelBlock block : viertelBloecke) {

			boolean blockRotiert = false;

			for (int i = 0; i <= 3; i++) {

				if (blockRotiert)
					break;

				for (int j = 0; j <= 3; j++) {

					if (blockRotiert)
						break;

					int xBlock = block.getX();
					int yBlock = block.getY();

					Punkt punkt = tetrominoMatrix[i][j];

					if (punkt.getX() == xBlock && punkt.getY() == yBlock) {

						Punkt neuerPunkt = this.findePunkt(tetrominoMatrix, i, j);

						if (neuerPunkt == null)
							throw new IllegalStateException("Punkt nicht gefunden!");

						block.setX(neuerPunkt.getX());
						block.setY(neuerPunkt.getY());

						blockRotiert = true;
					}
				}
			}
		}
	}

	@SuppressWarnings("static-method")
	Punkt findePunkt(Punkt[][] tetrominoMatrix, int i, int j) {

		Punkt neuerPunkt = null;

		if (i == 0 && j == 0)
			neuerPunkt = tetrominoMatrix[0][2];
		else if (i == 0 && j == 1)
			neuerPunkt = tetrominoMatrix[1][2];
		else if (i == 0 && j == 2)
			neuerPunkt = tetrominoMatrix[2][2];

		else if (i == 1 && j == 0)
			neuerPunkt = tetrominoMatrix[0][1];
		else if (i == 1 && j == 1)
			neuerPunkt = tetrominoMatrix[1][1];
		else if (i == 1 && j == 2)
			neuerPunkt = tetrominoMatrix[2][1];

		else if (i == 2 && j == 0)
			neuerPunkt = tetrominoMatrix[0][0];
		else if (i == 2 && j == 1)
			neuerPunkt = tetrominoMatrix[1][0];
		else if (i == 2 && j == 2)
			neuerPunkt = tetrominoMatrix[2][0];

		return neuerPunkt;
	}

	public boolean faelltAuf(ViertelBlock block) {

		if (viertelBloecke.isEmpty())
			return false;

		for (ViertelBlock fallenderBlock : viertelBloecke)
			if (fallenderBlock.getX() == block.getX() && fallenderBlock.getTiefstesY() == block.getHoechstesY())
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

	public void bewegeNachLinks() {

		if (viertelBloecke.isEmpty())
			return;

		if (getKanteLinksX() == 0)
			return;

		for (ViertelBlock block : viertelBloecke) {

			int neuesBlockX = block.getX() - TetrisKonstanten.BLOCK_BREITE;

			if (neuesBlockX >= 0)
				block.setX(neuesBlockX);
		}
	}

	public void bewegeNachRechts() {

		if (viertelBloecke.isEmpty())
			return;

		if (getKanteRechtsX() == TetrisKonstanten.SPIELFELD_BREITE)
			return;

		for (ViertelBlock block : viertelBloecke) {

			int neuesBlockX = block.getX() + TetrisKonstanten.BLOCK_BREITE;

			if (neuesBlockX <= TetrisKonstanten.SPIELFELD_BREITE)
				block.setX(neuesBlockX);
		}
	}

	public void bewegeNachUnten() {

		if (viertelBloecke.isEmpty())
			return;

		for (ViertelBlock block : viertelBloecke)
			block.setY(block.getY() + TetrisKonstanten.TETROMINO_FALL_HOEHE);
	}
}