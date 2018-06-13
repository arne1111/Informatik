package com.hoffrogge.tetris.logik;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;

import com.hoffrogge.tetris.model.Spielfeld;
import com.hoffrogge.tetris.model.TetrisKeyListener;
import com.hoffrogge.tetris.model.TetrisKonstanten;
import com.hoffrogge.tetris.model.TetrisMusikSpieler;
import com.hoffrogge.tetris.view.Spielfenster;
import com.hoffrogge.tetris.view.Vorschau;

public class Spiel implements Runnable {

	private Spielfeld spielfeld;
	private Vorschau vorschau;

	private boolean spielLaeuft;
	private Thread spielThread;
	private Thread soundThread;

	private JLabel punkteWertLabel;
	private JLabel levelWertLabel;
	private JLabel reihenWertLabel;
	private JLabel highscoreLabel;

	private TetrisKeyListener tetrisKeyListener;

	private int level = 1;
	private int punkte = 0;
	private int highscore = 0;
	private int reihen = 0;
	private boolean isPause;

	public Spiel(Spielfenster spielfenster) {

		spielfeld = spielfenster.getSpielfeld();
		vorschau = spielfenster.getVorschau();
		tetrisKeyListener = spielfenster.getTetrisKeyListener();

		/* Das koennte man mit einem Oberserver viel schoener loesen */
		tetrisKeyListener.setSpiel(this);

		spielfeld.setSpiel(this);

		punkteWertLabel = spielfenster.getPunkteWertLabel();
		levelWertLabel = spielfenster.getLevelWertLabel();
		reihenWertLabel = spielfenster.getReihenWertLabel();
		highscoreLabel = spielfenster.getHighscoreLabel();

		spielLaeuft = true;
	}

	@Override
	public void run() {

		while (spielLaeuft) {

			punkteLevelReihenAktualisieren();

			spielfeld.spielerEingabenVerarbeiten();

			if (!isPause())
				spielfeld.aktualisieren();

			vorschau.aktualisieren(spielfeld.getNaechsterSpielsteinTyp());

			spielfeld.darstellen();
			vorschau.darstellen();

			if (spielfeld.istSpielfeldVoll()) {

				beendeSpiel();
				continue;
			}

			try {

				int spielBeschleuniger = (level - 1) * 50;

				int spielGeschwindigkeit = Math.max(TetrisKonstanten.SPIEL_GESCHWINDIGKEIT - spielBeschleuniger,
						TetrisKonstanten.SPIEL_GESCHWINDIGKEIT_MIN);

				Thread.sleep(spielGeschwindigkeit);

			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
		}

	}

	public void starteSpiel() {

		highscoreLaden();

		spielThread = new Thread(this);
		spielThread.start();

		if (TetrisKonstanten.MUSIK_AN) {

			soundThread = new Thread(new TetrisMusikSpieler());
			soundThread.start();
		}
	}

	private void beendeSpiel() {

		spielLaeuft = false;

		try {

			if (TetrisKonstanten.MUSIK_AN)
				soundThread.join();

		} catch (InterruptedException e) {
			Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
			Thread.currentThread().interrupt();
		}

		highscoreSpeichern();
	}

	public void erhoehePunkte() {

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

		if (reihen / level >= 10)
			level++;
	}

	private void punkteLevelReihenAktualisieren() {

		levelWertLabel.setText(String.valueOf(level));
		punkteWertLabel.setText(String.valueOf(punkte));
		reihenWertLabel.setText(String.valueOf(reihen));

		highscore = Math.max(punkte, highscore);
		highscoreLabel.setText(String.valueOf(highscore));
	}

	private void highscoreSpeichern() {

		File highscoreCsv = new File("highscore.csv");

		if (!highscoreCsv.exists()) {

			try {
				highscoreCsv.createNewFile();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.WARNING, "Highscore-Datei konnte nicht angelegt werden! " + e.getMessage(),
						e);
				e.printStackTrace();
			}
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.csv"))) {

			int aktuellerHighscore = Math.max(punkte, highscore);
			String content = String.valueOf(aktuellerHighscore);

			bw.write(content);

		} catch (IOException e) {
			Logger.getGlobal().log(Level.WARNING, "Konnte Highscore nicht speichern! " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	private void highscoreLaden() {

		File highscoreCsv = new File("highscore.csv");

		if (!highscoreCsv.exists())
			return;

		try (BufferedReader br = new BufferedReader(new FileReader("highscore.csv"))) {

			String line = br.readLine();

			highscore = Integer.parseInt(line);

		} catch (FileNotFoundException e) {
			Logger.getGlobal().log(Level.WARNING, "Konnte Highscore-Datei nicht finden! " + e.getMessage(), e);
			e.printStackTrace();
		} catch (IOException | NumberFormatException e) {
			Logger.getGlobal().log(Level.WARNING, "Konnte Highscore nicht lesen! " + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	public void togglePause() {
		isPause = !isPause;
	}

	public boolean isPause() {
		return isPause;
	}
}