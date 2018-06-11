package com.hoffrogge.tetris.model.tetromino;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;

public class Kreis implements TetrominoSpielstein {

	private int mittelpunktX;
	private int mittelpunktY;

	@Override
	public int getHoechstesY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTiefstesY() {
		return mittelpunktY + 100;
	}

	@Override
	public int getKanteLinksX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getKanteRechtsX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void bewegeNachUnten() {
		mittelpunktY += 15;
	}

	@Override
	public void bewegeNachRechts() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bewegeNachLinks() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean faelltAuf(ViertelBlock block) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rotiereNachLinks() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ViertelBlock> getViertelBloecke() {

		ViertelBlock viertelBlock = new ViertelBlock(mittelpunktX, mittelpunktY);

		return Arrays.asList(viertelBlock);
	}

	@Override
	public void setMittelpunkt(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Punkt getMittelPunkt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDurchmesser(int d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		// TODO Auto-generated method stub

	}

	@Override
	public void zeichnen(Graphics graphics) {

		graphics.setColor(Color.ORANGE);

		graphics.fillOval(mittelpunktX, mittelpunktY, 100, 100);

	}

	@Override
	public Farbe getLinienFarbe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farbe getFuellFarbe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFuellFarbe(Farbe farbe) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(TetrominoSpielstein o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}
}