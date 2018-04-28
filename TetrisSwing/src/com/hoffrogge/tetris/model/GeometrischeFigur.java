package com.hoffrogge.tetris.model;

import java.awt.Graphics;

public interface GeometrischeFigur {

	void setMittelpunkt(int x, int y);

	Punkt getMittelPunkt();

	int getHoechstesY();

	int getTiefstesY();

	int getKanteLinksX();

	int getKanteRechtsX();

	void setDurchmesser(int d);

	void setLinienFarbe(Farbe farbe);

	void zeichnen(Graphics graphics);
}