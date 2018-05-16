package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hoffrogge.tetris.logik.Spiel;

@SuppressWarnings("serial")
public class Spielfeld extends Canvas {

	private transient Tetromino fallenderSpielstein;
	private TetrominoTyp naechsterSpielsteinTyp;
	private List<ViertelBlock> gefalleneSteine;
	private Spiel spiel;

	public Spielfeld() {
		/* Konstruktor */
		gefalleneSteine = new CopyOnWriteArrayList<>();
	}

	public Tetromino getFallenderSpielstein() {
		return fallenderSpielstein;
	}

	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;
	}

	public void spielerEingabenVerarbeiten() {
		/* Hier passiert noch nichts. */
	}

	public void aktualisieren() {

		loescheVolleReihen();

		if (fallenderSpielstein == null)
			fallenderSpielstein = neuerZufaelligerSpielstein();

		if (fallenderSpielstein != null) {

			fallenderSpielstein.bewegeNachUnten();

			if (hatFallenderSteinBodenErreicht() || faelltFallenderSteinAufAnderenStein()) {

				gefalleneSteine.addAll(fallenderSpielstein.getViertelBloecke());

				fallenderSpielstein = null;

				spiel.erhoehePunkte();
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

	public boolean istSpielfeldVoll() {

		for (GeometrischeFigur gefallenerStein : gefalleneSteine) {

			if (gefallenerStein.getHoechstesY() <= 0)
				return true;
		}

		return false;
	}

	private static void zeichneSpielfeld(Graphics g) {

		/* Hintergrund des Spielfeldes */
		g.setColor(TetrisKonstanten.HINTERGRUND.konvertiereZuColor());
		g.fillRect(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);
	}

	private Tetromino neuerZufaelligerSpielstein() {

		/* bei Spielstart gibt es noch keinen naechsten Stein */
		if (naechsterSpielsteinTyp == null)
			naechsterSpielsteinTyp = TetrominoFactory.erstelleZufaelligenTetrominoTyp();

		Tetromino tetromino = TetrominoFactory.erstelleTetromino(naechsterSpielsteinTyp);

		naechsterSpielsteinTyp = TetrominoFactory.erstelleZufaelligenTetrominoTyp();

		return tetromino;
	}

	public TetrominoTyp getNaechsterSpielsteinTyp() {
		return naechsterSpielsteinTyp;
	}

	private boolean hatFallenderSteinBodenErreicht() {
		return fallenderSpielstein.getTiefstesY() == TetrisKonstanten.SPIELFELD_HOEHE;
	}

	private boolean faelltFallenderSteinAufAnderenStein() {

		if (gefalleneSteine.isEmpty())
			return false;

		for (ViertelBlock block : gefalleneSteine)
			if (fallenderSpielstein.faelltAuf(block))
				return true;

		return false;
	}

	@SuppressWarnings("boxing")
	private void loescheVolleReihen() {

		Collections.sort(gefalleneSteine);

		Map<Integer, List<ViertelBlock>> bloeckeProReihe = new HashMap<>();

		for (ViertelBlock block : gefalleneSteine) {

			List<ViertelBlock> blockListe = bloeckeProReihe.get(block.getY());

			if (blockListe == null)
				blockListe = new ArrayList<>();

			blockListe.add(block);

			bloeckeProReihe.put(block.getY(), blockListe);
		}

		for (Entry<Integer, List<ViertelBlock>> reihe : bloeckeProReihe.entrySet()) {

			List<ViertelBlock> blockListe = reihe.getValue();

			if (blockListe.size() == TetrisKonstanten.SPIELFELD_BREITE / TetrisKonstanten.BLOCK_BREITE)
				loescheReihe(blockListe);
		}
	}

	private void loescheReihe(List<ViertelBlock> blockListe) {

		int hoehe = 0;

		for (ViertelBlock block : blockListe) {

			block.setFuellFarbe(new Farbe(255, 60, 255));
			gefalleneSteine.remove(block);
			hoehe = block.getY();
		}

		for (ViertelBlock block : gefalleneSteine)
			if (block.getY() < hoehe)
				block.bewegeNachUnten();

		spiel.erhoeheReihen();
	}
}