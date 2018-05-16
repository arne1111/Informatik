package com.hoffrogge.tetris.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Spielfenster extends JFrame {

	private Spielfeld spielfeld;
	private Vorschau vorschau;
	private JLabel levelWertLabel;
	private JLabel reihenWertLabel;
	private JLabel punkteWertLabel;

	private TetrisKeyListener tetrisKeyListener;
	private JLabel lblPauseMitP;
	private JLabel lblDrehenMitQ;
	private JLabel lblBewegenMitPfeiltasten;

	public Spielfenster() {

		setTitle("Hoffrogge Tetris Klon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(new Dimension(TetrisKonstanten.FENSTER_BREITE, TetrisKonstanten.FENSTER_HOEHE));

		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		spielfeld = new Spielfeld();
		spielfeld.setBackground(TetrisKonstanten.HINTERGRUND.konvertiereZuColor());
		spielfeld.setBounds(TetrisKonstanten.SPIELFELD_POS_X, TetrisKonstanten.SPIELFELD_POS_Y,
				TetrisKonstanten.SPIELFELD_BREITE, TetrisKonstanten.SPIELFELD_HOEHE);
		getContentPane().add(spielfeld);

		tetrisKeyListener = new TetrisKeyListener(spielfeld);
		addKeyListener(tetrisKeyListener);

		vorschau = new Vorschau();
		vorschau.setBackground(TetrisKonstanten.HINTERGRUND.konvertiereZuColor());
		vorschau.setForeground(TetrisKonstanten.VORDERGRUND.konvertiereZuColor());
		vorschau.setBounds(TetrisKonstanten.VORSCHAU_POS_X, TetrisKonstanten.VORSCHAU_POS_Y,
				TetrisKonstanten.VORSCHAU_BREITE, TetrisKonstanten.VORSCHAU_HOEHE);
		getContentPane().add(vorschau);

		Font font = new Font("Arial Black", Font.BOLD, 16);

		JLabel levelLabel = new JLabel("Level");
		levelLabel.setFont(font);
		levelLabel.setBounds(411, 287, 100, 25);
		levelLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(levelLabel);

		JLabel reihenLabel = new JLabel("Reihen");
		reihenLabel.setFont(font);
		reihenLabel.setBounds(411, 399, 100, 25);
		reihenLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(reihenLabel);

		JLabel punkteLabel = new JLabel("Punkte");
		punkteLabel.setFont(font);
		punkteLabel.setBounds(411, 508, 100, 25);
		punkteLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(punkteLabel);

		levelWertLabel = new JLabel("New label");
		levelWertLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		levelWertLabel.setFont(font);
		levelWertLabel.setBounds(510, 323, 100, 25);
		levelWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(levelWertLabel);

		reihenWertLabel = new JLabel("New label");
		reihenWertLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		reihenWertLabel.setFont(font);
		reihenWertLabel.setBounds(510, 435, 100, 25);
		reihenWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(reihenWertLabel);

		punkteWertLabel = new JLabel("New label");
		punkteWertLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		punkteWertLabel.setFont(font);
		punkteWertLabel.setBounds(510, 545, 100, 25);
		punkteWertLabel.setForeground(TetrisKonstanten.TEXT.konvertiereZuColor());
		getContentPane().add(punkteWertLabel);

		lblPauseMitP = new JLabel("Pause mit P");
		lblPauseMitP.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPauseMitP.setForeground(new Color(55, 63, 81));
		lblPauseMitP.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPauseMitP.setBounds(460, 780, 150, 25);
		getContentPane().add(lblPauseMitP);

		lblDrehenMitQ = new JLabel("Drehen mit Q");
		lblDrehenMitQ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDrehenMitQ.setForeground(new Color(55, 63, 81));
		lblDrehenMitQ.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDrehenMitQ.setBounds(460, 744, 150, 25);
		getContentPane().add(lblDrehenMitQ);

		lblBewegenMitPfeiltasten = new JLabel("Bewegen mit Pfeiltasten");
		lblBewegenMitPfeiltasten.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBewegenMitPfeiltasten.setForeground(new Color(55, 63, 81));
		lblBewegenMitPfeiltasten.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBewegenMitPfeiltasten.setBounds(450, 708, 160, 25);
		getContentPane().add(lblBewegenMitPfeiltasten);

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

	public TetrisKeyListener getTetrisKeyListener() {
		return tetrisKeyListener;
	}
}