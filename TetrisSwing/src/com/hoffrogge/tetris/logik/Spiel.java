package com.hoffrogge.tetris.logik;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.hoffrogge.tetris.model.Spielfeld;
import com.hoffrogge.tetris.model.TetrisKonstanten;
import com.hoffrogge.tetris.model.Vorschau;

public class Spiel implements Runnable {

	private Spielfeld spielfeld;
	private Vorschau vorschau;

	private boolean spielLaeuft;
	private Thread spielThread;

	public Spiel(Spielfeld spielfeld, Vorschau vorschau) {

		this.spielfeld = spielfeld;
		this.vorschau = vorschau;

		spielLaeuft = true;
	}

	@Override
	public void run() {

		while (spielLaeuft) {

			spielfeld.spielerEingabenVerarbeiten();
			spielfeld.aktualisieren();
			spielfeld.darstellen();

			vorschau.aktualisieren(spielfeld.getNaechsterSpielstein());
			vorschau.darstellen();

			if (spielfeld.istSpielfeldVoll())
				beendeSpiel();

			try {

				Thread.sleep(TetrisKonstanten.SPIEL_GESCHWINDIGKEIT);

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
}