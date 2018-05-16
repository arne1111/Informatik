package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Vorschau extends Canvas {

	private TetrominoTyp naechsterSpielsteinTyp;
	private static final Farbe FUELL_FARBE = new Farbe(80, 90, 80);

	@Override
	public void paint(Graphics g) {

		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.drawString("Tetris Infofeld", 10, 20);
	}

	public void aktualisieren(TetrominoTyp tetrominoTyp) {
		this.naechsterSpielsteinTyp = tetrominoTyp;
	}

	public void darstellen() {

		Graphics g = null;

		try {

			g = getBufferStrategy().getDrawGraphics();

			zeichneVorschauFeld(g);

			if (naechsterSpielsteinTyp == null)
				return;

			int xKoordinate = 0;
			int yKoordinate = 0;

			switch (naechsterSpielsteinTyp) {

			case BLOCK:
				xKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 1.5);
				yKoordinate = TetrisKonstanten.BLOCK_BREITE * 2;
				break;

			case L:
			case UMGEDREHTES_Z:
				xKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 1.5);
				yKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 1.5);
				break;

			case UMGEDREHTES_L:
				xKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 2.5);
				yKoordinate = TetrisKonstanten.BLOCK_BREITE;
				break;

			case LANGER:
				xKoordinate = TetrisKonstanten.BLOCK_BREITE * 2;
				yKoordinate = TetrisKonstanten.BLOCK_BREITE;
				break;

			case Z:
				xKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 2.5);
				yKoordinate = (int) (TetrisKonstanten.BLOCK_BREITE * 1.5);
				break;

			case T:
				xKoordinate = TetrisKonstanten.BLOCK_BREITE * 2;
				yKoordinate = TetrisKonstanten.BLOCK_BREITE * 2;
				break;

			default:
				throw new IllegalStateException("TetrominoTyp " + naechsterSpielsteinTyp + " ist nicht bekannt!");
			}

			Tetromino naechsterSpielstein = TetrominoFactory.erstelleTetromino(naechsterSpielsteinTyp, xKoordinate,
					yKoordinate);

			for (ViertelBlock block : naechsterSpielstein.getViertelBloecke())
				block.setFuellFarbe(FUELL_FARBE);

			naechsterSpielstein.zeichnen(g);

		} finally {
			if (g != null)
				g.dispose();
		}

		getBufferStrategy().show();
	}

	private void zeichneVorschauFeld(Graphics g) {

		/* Hintergrund des Feldes */
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, TetrisKonstanten.BLOCK_BREITE * 5, TetrisKonstanten.BLOCK_BREITE * 6);
	}
}