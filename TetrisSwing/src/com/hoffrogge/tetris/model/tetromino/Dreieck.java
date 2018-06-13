package com.hoffrogge.tetris.model.tetromino;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;
import com.hoffrogge.tetris.model.TetrisKonstanten;

public class Dreieck implements TetrominoSpielstein {

	private static final Farbe LINIEN_FARBE = new Farbe(0, 0, 0);

	private int x;
	private int y;
	private Farbe fuellFarbe;

	public Dreieck() {

		x = TetrisKonstanten.BLOCK_BREITE * 4;
		y = 0;
		fuellFarbe = new Farbe(255, 255, 255);
	}

	@Override
	public void zeichnen(Graphics graphics) {

		graphics.setColor(LINIEN_FARBE.konvertiereZuColor());

		graphics.drawLine(x, y, x - TetrisKonstanten.BLOCK_BREITE / 2, y + TetrisKonstanten.BLOCK_BREITE);
		graphics.drawLine(x, y, x + TetrisKonstanten.BLOCK_BREITE / 2, y + TetrisKonstanten.BLOCK_BREITE);
		graphics.drawLine(x - TetrisKonstanten.BLOCK_BREITE / 2, y + TetrisKonstanten.BLOCK_BREITE,
				x + TetrisKonstanten.BLOCK_BREITE / 2, y + TetrisKonstanten.BLOCK_BREITE);

		int[] xPoints = { x, x - TetrisKonstanten.BLOCK_BREITE / 2, x + TetrisKonstanten.BLOCK_BREITE / 2 };
		int[] yPoints = { y, y + TetrisKonstanten.BLOCK_BREITE, y + TetrisKonstanten.BLOCK_BREITE };
		int nPoints = 3;

		graphics.setColor(fuellFarbe.konvertiereZuColor());
		graphics.fillPolygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void setMittelpunkt(int x, int y) {
		this.x = x;
		this.y = x;
	}

	@Override
	public Punkt getMittelPunkt() {
		return new Punkt(x, y);
	}

	@Override
	public void setDurchmesser(int d) {
		throw new IllegalStateException("Durchmesser soll TetrisKonstanten.BLOCK_BREITE sein!");
	}

	@Override
	public Farbe getLinienFarbe() {
		return LINIEN_FARBE;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		throw new IllegalStateException("Die Linienfarbe soll schwarz bleiben!");
	}

	@Override
	public int compareTo(TetrominoSpielstein o) {
		return x - o.getX();
	}

	@Override
	public int getHoechstesY() {
		return y;
	}

	@Override
	public int getTiefstesY() {
		return y + TetrisKonstanten.BLOCK_BREITE / 2;
	}

	@Override
	public int getKanteLinksX() {
		return x - TetrisKonstanten.BLOCK_BREITE / 2;
	}

	@Override
	public int getKanteRechtsX() {
		return x + TetrisKonstanten.BLOCK_BREITE / 2;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void bewegeNachUnten() {

		if (y + TetrisKonstanten.BLOCK_BREITE + TetrisKonstanten.BLOCK_BREITE > TetrisKonstanten.SPIELFELD_HOEHE)
			y = TetrisKonstanten.SPIELFELD_HOEHE - TetrisKonstanten.BLOCK_BREITE;
		else
			y += TetrisKonstanten.BLOCK_BREITE;
	}

	@Override
	public void bewegeNachRechts() {

		if (x + TetrisKonstanten.BLOCK_BREITE / 2 >= TetrisKonstanten.SPIELFELD_BREITE)
			x = TetrisKonstanten.SPIELFELD_BREITE - TetrisKonstanten.BLOCK_BREITE / 2;
		else
			x += TetrisKonstanten.BLOCK_BREITE / 2;
	}

	@Override
	public void bewegeNachLinks() {

		if (x - TetrisKonstanten.BLOCK_BREITE / 2 <= 0)
			x = TetrisKonstanten.BLOCK_BREITE / 2;
		else
			x -= TetrisKonstanten.BLOCK_BREITE / 2;
	}

	@Override
	public boolean faelltAuf(TetrominoSpielstein block) {

		if (x == block.getX() && getTiefstesY() >= block.getHoechstesY())
			return true;

		return false;
	}

	@Override
	public void rotiereNachLinks() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TetrominoSpielstein> getViertelBloecke() {
		return Arrays.asList(this);
	}

	@Override
	public Farbe getFuellFarbe() {
		return fuellFarbe;
	}

	@Override
	public void setFuellFarbe(Farbe farbe) {
		fuellFarbe = farbe;
	}

}