package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Spielfeld extends Canvas {

	private transient Tetromino fallenderSpielstein;
	private List<Tetromino> gefalleneSteine;

	public Spielfeld() {
		/* Konstruktor */
		gefalleneSteine = new ArrayList<>();
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

			if (hatFallenderSteinBodenErreicht() || faelltFallenderSteinAufAnderenStein()) {

				gefalleneSteine.add(fallenderSpielstein);

				fallenderSpielstein = null;
			}

		}
	}

	public void darstellen() {

		Graphics g = null;

		try {

			g = getBufferStrategy().getDrawGraphics();

			zeichneSpielfeld(g);

			if (fallenderSpielstein != null)
				fallenderSpielstein.zeichnen(g);

			for (GeometrischeFigur gefallenerStein : gefalleneSteine)
				gefallenerStein.zeichnen(g);

		} finally {
			if (g != null)
				g.dispose();
		}

		getBufferStrategy().show();

	}

	private static void zeichneSpielfeld(Graphics g) {

		/* Hintergrund des Spielfeldes */
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);

		/* Text auf dem Spielfeld */
		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Tetris Spielfeld", 10, 20);
	}

	private static Tetromino neuerZufaelligerSpielstein() {
		return TetrominoFactory.erstelleZufaelligenTetromino();
	}

	public boolean istSpielfeldVoll() {

		for (GeometrischeFigur gefallenerStein : gefalleneSteine) {

			if (gefallenerStein.getHoechstesY() <= 0)
				return true;

		}

		return false;
	}

	private boolean hatFallenderSteinBodenErreicht() {
		return fallenderSpielstein.getTiefstesY() == TetrisKonstanten.SPIELFELD_HOEHE - TetrisKonstanten.BLOCK_BREITE;
	}

	private boolean faelltFallenderSteinAufAnderenStein() {

		if (gefalleneSteine.isEmpty())
			return false;

		for (GeometrischeFigur gefallenerStein : gefalleneSteine)
			if (fallenderSpielstein.faelltAuf(gefallenerStein))
				return true;

		return false;
	}
}