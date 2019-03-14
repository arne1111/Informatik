package com.hoffrogge.tetris.model.tetromino;

public interface TetrominoFactory {

    TetrominoSpielstein erstelleTetromino(TetrominoTyp typ);

    TetrominoTyp erstelleZufaelligenTetrominoTyp();

    TetrominoSpielstein erstelleTetromino(TetrominoTyp typ, int x, int y);

}