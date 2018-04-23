package com.hoffrogge.tetris.logik;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.hoffrogge.tetris.model.Spielfeld;

public class GameLoop implements Runnable {

	private Spielfeld spielfeld;
	private boolean spielLaeuft;
	private Thread gameLoopThread;

	public GameLoop(Spielfeld spielfeld) {

		this.spielfeld = spielfeld;
		spielLaeuft = true;
	}

	@Override
	public void run() {

		while (spielLaeuft) {

			// TODO Spielereingaben verarbeiten
			spielfeld.aktualisieren();
			spielfeld.darstellen();

			try {

				Thread.sleep(10);

			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.OFF, e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
		}

	}

	public void starteSpiel() {
		gameLoopThread = new Thread(this);
		gameLoopThread.start();
	}

	public void beendeSpiel() {

		spielLaeuft = false;

		try {
			gameLoopThread.join();
		} catch (InterruptedException e) {
			Logger.getGlobal().log(Level.OFF, e.getMessage(), e);
			Thread.currentThread().interrupt();
		}
	}
}