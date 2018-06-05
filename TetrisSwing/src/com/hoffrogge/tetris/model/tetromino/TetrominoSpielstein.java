package com.hoffrogge.tetris.model.tetromino;

import java.util.List;

import com.hoffrogge.tetris.model.GeometrischeFigur;

public interface TetrominoSpielstein extends GeometrischeFigur {

	int getHoechstesY();

	int getTiefstesY();

	int getKanteLinksX();

	int getKanteRechtsX();

	void bewegeNachUnten();

	void bewegeNachRechts();

	void bewegeNachLinks();

	boolean faelltAuf(ViertelBlock block);

	void rotiereNachLinks();

	List<ViertelBlock> getViertelBloecke();
}