package com.hoffrogge.tetris.model;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;

	public Spielfenster() {

		setTitle("Hoffrogge Tetris Klon");
		setSize(new Dimension(1024, 768));
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		spielfeld = new Spielfeld();
		add(spielfeld);

		// pack();
		setVisible(true);
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}
}
