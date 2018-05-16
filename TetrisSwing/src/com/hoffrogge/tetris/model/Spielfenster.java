package com.hoffrogge.tetris.model;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;
	private Vorschau vorschau;
	private JLabel levelWertLabel;
	private JLabel reihenWertLabel;
	private JLabel punkteWertLabel;

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

		Font font = new Font("Arial Black", Font.BOLD, 16);

		JLabel levelLabel = new JLabel("Level");
		levelLabel.setFont(font);
		levelLabel.setBounds(406, 419, 100, 25);
		levelLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(levelLabel);

		JLabel reihenLabel = new JLabel("Reihen");
		reihenLabel.setFont(font);
		reihenLabel.setBounds(406, 531, 100, 25);
		reihenLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(reihenLabel);

		JLabel punkteLabel = new JLabel("Punkte");
		punkteLabel.setFont(font);
		punkteLabel.setBounds(406, 640, 100, 25);
		punkteLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(punkteLabel);

		levelWertLabel = new JLabel("New label");
		levelWertLabel.setFont(font);
		levelWertLabel.setBounds(504, 455, 100, 25);
		levelWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(levelWertLabel);

		reihenWertLabel = new JLabel("New label");
		reihenWertLabel.setFont(font);
		reihenWertLabel.setBounds(504, 567, 100, 25);
		reihenWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(reihenWertLabel);

		punkteWertLabel = new JLabel("New label");
		punkteWertLabel.setFont(font);
		punkteWertLabel.setBounds(504, 676, 100, 25);
		punkteWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(punkteWertLabel);

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

	public JLabel getLevelWertLabel() {
		return levelWertLabel;
	}

	public JLabel getReihenWertLabel() {
		return reihenWertLabel;
	}

	public JLabel getPunkteWertLabel() {
		return punkteWertLabel;
	}
}