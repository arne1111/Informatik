package com.hoffrogge.tetris.model.tetromino;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;
import com.hoffrogge.tetris.model.TetrisKonstanten;

public class ViertelBlock implements TetrominoSpielstein, Comparable<ViertelBlock> {

	private int x;
	private int y;
	private int kantenLaengeViertelBlock;
	private Farbe linienFarbe;
	private Farbe fuellFarbe;

	public ViertelBlock(int x, int y) {

		this.x = x;
		this.y = y;
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

	public Farbe getLinienFarbe() {
		return linienFarbe;
	}

	public Farbe getFuellFarbe() {
		return fuellFarbe;
	}

	public void setFuellFarbe(Farbe fuellFarbe) {
		this.fuellFarbe = fuellFarbe;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	@Override
	public int compareTo(ViertelBlock o) {
		return this.getX() - o.getX();
	}

	public void bewegeNachUnten() {
		y += TetrisKonstanten.TETROMINO_FALL_HOEHE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViertelBlock other = (ViertelBlock) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
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