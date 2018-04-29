package com.hoffrogge.tetris.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

	private Spielfeld spielfeld;

	public TetrisKeyListener(Spielfeld spielfeld) {

		this.spielfeld = spielfeld;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		/* nichts zu tun */
	}

	@Override
	public void keyPressed(KeyEvent e) {

		Tetromino fallenderSpielstein = spielfeld.getFallenderSpielstein();

		if (fallenderSpielstein == null)
			return;

		int keyCode = e.getKeyCode();

		switch (keyCode) {

		case KeyEvent.VK_LEFT:
			fallenderSpielstein.bewegeNachLinks();
			break;

		case KeyEvent.VK_RIGHT:
			fallenderSpielstein.bewegeNachRechts();
			break;

		case KeyEvent.VK_DOWN:
			spielfeld.aktualisieren();
			break;

		default:
			break;
		}

		spielfeld.darstellen();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		/* nichts zu tun */
	}
}