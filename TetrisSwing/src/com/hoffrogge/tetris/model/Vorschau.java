package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Vorschau extends Canvas {

	private Tetromino naechsterSpielstein;

	@Override
	public void paint(Graphics g) {

		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.drawString("Tetris Infofeld", 10, 20);
	}

	public void aktualisieren(Tetromino naechsterSpielstein) {
		this.naechsterSpielstein = naechsterSpielstein;
	}

	public void darstellen() {

		Graphics g = null;

		try {

			g = getBufferStrategy().getDrawGraphics();

			zeichneVorschauFeld(g);

			if (naechsterSpielstein != null)
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
		g.fillRect(0, 0, TetrisKonstanten.BLOCK_BREITE * 4, TetrisKonstanten.BLOCK_BREITE * 4);

		/* Text auf dem Spielfeld */
		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Vorschau", 10, 20);
	}
}