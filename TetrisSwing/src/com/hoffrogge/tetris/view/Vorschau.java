package com.hoffrogge.tetris.view;

import java.awt.Canvas;
import java.awt.Graphics;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.TetrisKonstanten;
import com.hoffrogge.tetris.model.tetromino.TetrominoFactory;
import com.hoffrogge.tetris.model.tetromino.TetrominoSpielstein;
import com.hoffrogge.tetris.model.tetromino.TetrominoTyp;
import com.hoffrogge.tetris.model.tetromino.ViertelBlock;

@SuppressWarnings("serial")
public class Vorschau extends Canvas {

	private TetrominoTyp naechsterSpielsteinTyp;
	private static final Farbe FUELL_FARBE = new Farbe(200, 240, 255);

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

			TetrominoSpielstein naechsterSpielstein = TetrominoFactory.erstelleTetromino(naechsterSpielsteinTyp, xKoordinate,
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
		g.setColor(TetrisKonstanten.VORDERGRUND.konvertiereZuColor());
		g.fillRect(0, 0, TetrisKonstanten.VORSCHAU_BREITE, TetrisKonstanten.VORSCHAU_HOEHE);
	}
}