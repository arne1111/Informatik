package com.hoffrogge.tetris.model.tetromino;

import com.hoffrogge.tetris.model.GeometrischeFigur;

public interface TetrominoGeometrie extends GeometrischeFigur {

	int getHoechstesY();

	int getTiefstesY();

	int getKanteLinksX();

	int getKanteRechtsX();
}