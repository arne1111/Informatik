package com.hoffrogge.tetris.model.tetromino;

import java.awt.Graphics;
import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;
import com.hoffrogge.tetris.model.TetrisKonstanten;

public class Quadrat implements TetrominoSpielstein {

	private int mittelpunktX;
	private int mittelpunktY;
	private int durchmesser;

	private Farbe farbe;

	public Quadrat() {

		farbe = new Farbe(255, 0, 255);

		durchmesser = 50;

		mittelpunktX = TetrisKonstanten.SPIELFELD_BREITE / 2;
		mittelpunktY = durchmesser;
	}

	@Override
	public void zeichnen(Graphics graphics) {

		graphics.setColor(farbe.konvertiereZuColor());

		int halbeKantenlaenge = durchmesser / 2;

		int linksObenX = mittelpunktX - halbeKantenlaenge;
		int linksObenY = mittelpunktY - halbeKantenlaenge;

		int rechtsObenX = mittelpunktX + halbeKantenlaenge;
		int rechtsObenY = mittelpunktY - halbeKantenlaenge;

		int rechtsUntenX = mittelpunktX + halbeKantenlaenge;
		int rechtsUntenY = mittelpunktY + halbeKantenlaenge;

		int linksUntenX = mittelpunktX - halbeKantenlaenge;
		int linksUntenY = mittelpunktY + halbeKantenlaenge;

		graphics.drawLine(linksObenX, linksObenY, rechtsObenX, rechtsObenY);
		graphics.drawLine(rechtsObenX, rechtsObenY, rechtsUntenX, rechtsUntenY);
		graphics.drawLine(rechtsUntenX, rechtsUntenY, linksUntenX, linksUntenY);
		graphics.drawLine(linksUntenX, linksUntenY, linksObenX, linksObenY);

		graphics.fillRect(linksObenX, linksObenY, durchmesser, durchmesser);
	}

	@Override
	public void setMittelpunkt(int x, int y) {
		this.mittelpunktX = x;
		this.mittelpunktY = y;
	}

	@Override
	public void setLinienFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	@Override
	public void setDurchmesser(int durchmesser) {
		this.durchmesser = durchmesser;
	}

	@Override
	public Punkt getMittelPunkt() {
		return new Punkt(mittelpunktX, mittelpunktY);
	}

	@Override
	public int getHoechstesY() {
		return mittelpunktY - durchmesser / 2;
	}

	@Override
	public int getTiefstesY() {
		return mittelpunktY + durchmesser / 2;
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

		Punkt mittelPunkt = getMittelPunkt();
		int y = mittelPunkt.getY();
		int x = mittelPunkt.getX();

		setMittelpunkt(x, y + 10);
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
		// TODO Auto-generated method stub
		return null;
	}
}