package com.hoffrogge.tetris.model;

import java.awt.Graphics;

public interface GeometrischeFigur {

	void setMittelpunkt(int x, int y);

	Punkt getMittelPunkt();

	void setDurchmesser(int d);

	Farbe getLinienFarbe();

	void setLinienFarbe(Farbe farbe);

	void zeichnen(Graphics graphics);
}