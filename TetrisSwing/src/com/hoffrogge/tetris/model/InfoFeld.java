package com.hoffrogge.tetris.model;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoFeld extends JPanel {

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Font f = new Font("Helvetica", Font.PLAIN, 20);

		g.setFont(f);
		g.drawString("Tetris Infofeld", 10, 20);

	}
}