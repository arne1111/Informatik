package com.hoffrogge.tetris.model;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;
	private Vorschau vorschau;

	public Spielfenster() {

		setTitle("Hoffrogge Tetris Klon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(new Dimension(620, 824));

		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		spielfeld = new Spielfeld();
		spielfeld.setBackground(TetrisKonstanten.HINTERGRUND.konvertiereZuColor());
		spielfeld.setBounds(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);
		getContentPane().add(spielfeld);

		addKeyListener(new TetrisKeyListener(spielfeld));

		vorschau = new Vorschau();
		vorschau.setBackground(TetrisKonstanten.HINTERGRUND.konvertiereZuColor());
		vorschau.setForeground(TetrisKonstanten.VORDERGRUND.konvertiereZuColor());
		vorschau.setBounds(405, 5, TetrisKonstanten.VORSCHAU_BREITE, TetrisKonstanten.VORSCHAU_HOEHE);
		getContentPane().add(vorschau);

		setVisible(true);

		spielfeld.createBufferStrategy(2);
		vorschau.createBufferStrategy(2);
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public Vorschau getVorschau() {
		return vorschau;
	}
}