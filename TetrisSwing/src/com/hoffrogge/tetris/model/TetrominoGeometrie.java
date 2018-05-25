package com.hoffrogge.tetris.model;

public interface TetrominoGeometrie extends GeometrischeFigur {

	int getHoechstesY();

	int getTiefstesY();

	int getKanteLinksX();

	int getKanteRechtsX();
}