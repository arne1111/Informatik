package com.hoffrogge.tetris.model;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;

	public Spielfenster() {

		setTitle("Hoffrogge Tetris Klon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(new Dimension((int) (TetrisKonstanten.SPIELFELD_BREITE * 1.5), TetrisKonstanten.SPIELFELD_HOEHE));
		setResizable(true);
		setLocationRelativeTo(null);

		spielfeld = new Spielfeld();

		add(spielfeld);

		setVisible(true);

		spielfeld.createBufferStrategy(2);
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}
}