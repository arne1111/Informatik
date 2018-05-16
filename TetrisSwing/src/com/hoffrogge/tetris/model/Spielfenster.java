package com.hoffrogge.tetris.model;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;
	private Vorschau vorschau;

	public Spielfenster() {

		setTitle("Hoffrogge Tetris Klon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(new Dimension((int) (TetrisKonstanten.SPIELFELD_BREITE * 1.5),
				(int) (TetrisKonstanten.SPIELFELD_HOEHE * 1.03)));

		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("N\u00E4chster Spielstein");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(406, 11, 120, 25);
		getContentPane().add(lblNewLabel);

		spielfeld = new Spielfeld();
		spielfeld.setBackground(Color.WHITE);
		spielfeld.setBounds(0, 0, TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);
		getContentPane().add(spielfeld);

		addKeyListener(new TetrisKeyListener(spielfeld));

		vorschau = new Vorschau();
		vorschau.setBackground(Color.WHITE);
		vorschau.setForeground(Color.WHITE);
		vorschau.setBounds(416, 42, TetrisKonstanten.BLOCK_BREITE * 4, TetrisKonstanten.BLOCK_BREITE * 4);
		getContentPane().add(vorschau);

		spielfeld.addVorschauCanvas(vorschau);

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