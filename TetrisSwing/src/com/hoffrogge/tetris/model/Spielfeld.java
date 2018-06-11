package com.hoffrogge.tetris.model;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import com.hoffrogge.tetris.logik.Spiel;
import com.hoffrogge.tetris.model.tetromino.Quadrat;
import com.hoffrogge.tetris.model.tetromino.TetrominoFactory;
import com.hoffrogge.tetris.model.tetromino.TetrominoSpielstein;
import com.hoffrogge.tetris.model.tetromino.TetrominoTyp;
import com.hoffrogge.tetris.model.tetromino.ViertelBlock;

@SuppressWarnings("serial")
public class Spielfeld extends Canvas {

	private transient TetrominoSpielstein fallenderSpielstein;
	private TetrominoTyp naechsterSpielsteinTyp;
	private List<ViertelBlock> gefalleneSteine;
	private Spiel spiel;

	public Spielfeld() {
		/* Konstruktor */
		gefalleneSteine = new CopyOnWriteArrayList<>();
	}

	public TetrominoSpielstein getFallenderSpielstein() {
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

				List<ViertelBlock> viertelBloecke = fallenderSpielstein.getViertelBloecke();

				if (viertelBloecke != null)
					gefalleneSteine.addAll(viertelBloecke);

				fallenderSpielstein = null;

				spiel.erhoehePunkte();
			}
		}
	}

	/* Baut hier eure eigenen Spielsteine ein */
	private TetrominoSpielstein neuerZufaelligerSpielstein() {

		/* bei Spielstart gibt es noch keinen naechsten Stein */
		if (naechsterSpielsteinTyp == null)
			naechsterSpielsteinTyp = TetrominoFactory.erstelleZufaelligenTetrominoTyp();

		TetrominoSpielstein tetromino = TetrominoFactory.erstelleTetromino(naechsterSpielsteinTyp);

		naechsterSpielsteinTyp = TetrominoFactory.erstelleZufaelligenTetrominoTyp();

		// return tetromino;

		return new Quadrat();
	}

	public void darstellen() {

		Graphics g = null;

		try {

			g = getBufferStrategy().getDrawGraphics();

			zeichneSpielfeld(g);

			if (!spiel.isPause()) {

				if (fallenderSpielstein != null)
					fallenderSpielstein.zeichnen(g);

				for (GeometrischeFigur gefallenerStein : gefalleneSteine)
					gefallenerStein.zeichnen(g);

			} else {
				zeichnePauseSchriftzug(g);
			}

		} finally {
			if (g != null)
				g.dispose();
		}

		getBufferStrategy().show();
	}

	public boolean istSpielfeldVoll() {

		for (TetrominoSpielstein gefallenerStein : gefalleneSteine) {

			if (gefallenerStein.getHoechstesY() <= 0)
				return true;
		}

		return false;
	}

	private static void zeichneSpielfeld(Graphics g) {

		/* Hintergrund des Spielfeldes */
		g.setColor(TetrisKonstanten.VORDERGRUND.konvertiereZuColor());
		g.fillRect(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);
	}

	private void zeichnePauseSchriftzug(Graphics g) {

		Font font = new Font("Arial Black", Font.BOLD, TetrisKonstanten.BLOCK_BREITE);

		g.setColor(TetrisKonstanten.AKZENT.konvertiereZuColor());
		g.setFont(font);

		g.drawString("Pause", TetrisKonstanten.SPIELFELD_BREITE / 2 - TetrisKonstanten.BLOCK_BREITE * 2,
				TetrisKonstanten.SPIELFELD_HOEHE / 2);
	}

	public TetrominoTyp getNaechsterSpielsteinTyp() {
		return naechsterSpielsteinTyp;
	}

	private boolean hatFallenderSteinBodenErreicht() {
		return fallenderSpielstein.getTiefstesY() >= TetrisKonstanten.SPIELFELD_HOEHE;
	}

	private boolean faelltFallenderSteinAufAnderenStein() {

		if (gefalleneSteine.isEmpty())
			return false;

		for (ViertelBlock block : gefalleneSteine)
			if (fallenderSpielstein.faelltAuf(block))
				return true;

		return false;
	}

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