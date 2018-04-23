package com.hoffrogge.tetris.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.hoffrogge.lehreinheit04.GeometrischeFigur;
import com.hoffrogge.lehreinheit04.Punkt;

@SuppressWarnings("serial")
public class Spielfeld extends JPanel {

	private transient GeometrischeFigur fallenderSpielstein;

	public Spielfeld() {

		setBorder(BorderFactory.createLineBorder(Color.black));

		setBackground(Color.GRAY);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(750, 760);
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.drawString("Tetris Spielfeld", 10, 20);
	}

	public void aktualisieren() {

		if (fallenderSpielstein == null)
			fallenderSpielstein = neuerZufaelligerSpielstein();

		Punkt mittelPunkt = fallenderSpielstein.getMittelPunkt();
		fallenderSpielstein.setMittelpunkt(mittelPunkt.getX(), mittelPunkt.getY() + 1);

	}

	public void darstellen() {

		Graphics g = getGraphics();

		paintComponent(g);

		if (fallenderSpielstein != null)
			fallenderSpielstein.zeichnen(g);
	}

	private GeometrischeFigur neuerZufaelligerSpielstein() {

		TetrominoBlock block = new TetrominoBlock(this.getWidth() / 2, 100);
		block.setDurchmesser(100);

		return block;
	}
}