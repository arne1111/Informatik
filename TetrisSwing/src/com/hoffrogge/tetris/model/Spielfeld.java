package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.hoffrogge.lehreinheit04.GeometrischeFigur;
import com.hoffrogge.lehreinheit04.Punkt;

@SuppressWarnings("serial")
public class Spielfeld extends Canvas {

	private transient GeometrischeFigur fallenderSpielstein;

	public Spielfeld() {
		/* Konstruktor */
	}

	public void spielerEingabenVerarbeiten() {
		/* Hier passiert noch nichts. */
	}

	public void aktualisieren() {

		if (fallenderSpielstein == null)
			fallenderSpielstein = neuerZufaelligerSpielstein();

		if (fallenderSpielstein != null) {

			Punkt mittelPunkt = fallenderSpielstein.getMittelPunkt();
			fallenderSpielstein.setMittelpunkt(mittelPunkt.getX(),
					mittelPunkt.getY() + TetrisKonstanten.TETROMINO_FALL_HOEHE);
		}
	}

	public void darstellen() {

		Graphics g = null;

		try {

			g = getBufferStrategy().getDrawGraphics();

			zeichneSpielfeld(g);

			if (fallenderSpielstein != null)
				fallenderSpielstein.zeichnen(g);

		} finally {
			if (g != null)
				g.dispose();
		}

		getBufferStrategy().show();

	}

	private void zeichneSpielfeld(Graphics g) {

		/* Hintergrund des Spielfeldes */
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);

		/* Text auf dem Spielfeld */
		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Tetris Spielfeld", 10, 20);
	}

	private GeometrischeFigur neuerZufaelligerSpielstein() {

		return null;
	}
}