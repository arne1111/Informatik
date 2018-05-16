package com.hoffrogge.tetris.logik;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

import com.hoffrogge.tetris.model.Spielfeld;
import com.hoffrogge.tetris.model.Spielfenster;
import com.hoffrogge.tetris.model.TetrisKeyListener;
import com.hoffrogge.tetris.model.TetrisKonstanten;
import com.hoffrogge.tetris.model.Vorschau;

public class Spiel implements Runnable {

	private Spielfeld spielfeld;
	private Vorschau vorschau;

	private boolean spielLaeuft;
	private Thread spielThread;
	private JLabel punkteWertLabel;
	private JLabel levelWertLabel;
	private JLabel reihenWertLabel;

	private TetrisKeyListener tetrisKeyListener;

	private int level = 1;
	private int punkte = 0;
	private int reihen = 0;

	public Spiel(Spielfenster spielfenster) {

		spielfeld = spielfenster.getSpielfeld();
		vorschau = spielfenster.getVorschau();
		tetrisKeyListener = spielfenster.getTetrisKeyListener();

		spielfeld.setSpiel(this);

		punkteWertLabel = spielfenster.getPunkteWertLabel();
		levelWertLabel = spielfenster.getLevelWertLabel();
		reihenWertLabel = spielfenster.getReihenWertLabel();

		spielLaeuft = true;
	}

	@Override
	public void run() {

		while (spielLaeuft) {

			punkteLevelReihenAktualisieren();

			spielfeld.spielerEingabenVerarbeiten();
			spielfeld.aktualisieren();

			vorschau.aktualisieren(spielfeld.getNaechsterSpielsteinTyp());

			spielfeld.darstellen();
			vorschau.darstellen();

			if (spielfeld.istSpielfeldVoll())
				beendeSpiel();

			try {

				int spielBeschleuniger = (level - 1) * 50;

				int spielGeschwindigkeit = Math.max(TetrisKonstanten.SPIEL_GESCHWINDIGKEIT - spielBeschleuniger,
						TetrisKonstanten.SPIEL_GESCHWINDIGKEIT_MIN);

				Thread.sleep(spielGeschwindigkeit);

			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.OFF, e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
		}

	}

	public void starteSpiel() {

		spielThread = new Thread(this);
		spielThread.start();
	}

	public void beendeSpiel() {

		spielLaeuft = false;

		try {
			spielThread.join();
		} catch (InterruptedException e) {
			Logger.getGlobal().log(Level.OFF, e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
	}

	public void erhoehePunkteFreierFall() {

		if (tetrisKeyListener.isBeschleunigterFall())
			punkte += level * 3 + 21;
		else
			punkte += level * 3 + 3;

		pruefeUndSetzeLevel();
	}

	public void erhoeheReihen() {
		reihen++;
	}

	private void pruefeUndSetzeLevel() {

		if (punkte / level >= 5)
			level++;
	}

	private void punkteLevelReihenAktualisieren() {

		levelWertLabel.setText(String.valueOf(level));
		punkteWertLabel.setText(String.valueOf(punkte));
		reihenWertLabel.setText(String.valueOf(reihen));
	}
}