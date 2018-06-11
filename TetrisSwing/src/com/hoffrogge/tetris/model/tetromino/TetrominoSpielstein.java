package com.hoffrogge.tetris.model.tetromino;

import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.GeometrischeFigur;

public interface TetrominoSpielstein extends GeometrischeFigur, Comparable<TetrominoSpielstein> {

	int getHoechstesY();

	int getTiefstesY();

	int getKanteLinksX();

	int getKanteRechtsX();

	int getX();

	void setX(int x);

	int getY();

	void setY(int y);

	void bewegeNachUnten();

	void bewegeNachRechts();

	void bewegeNachLinks();

	boolean faelltAuf(TetrominoSpielstein block);

	void rotiereNachLinks();

	List<TetrominoSpielstein> getViertelBloecke();

	Farbe getFuellFarbe();

	void setFuellFarbe(Farbe farbe);
}