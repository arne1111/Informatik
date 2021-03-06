package com.hoffrogge.tetris.model.tetromino;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StandardTetrominoFactory implements TetrominoFactory {

    private static final List<TetrominoTyp> TYPEN  = Collections.unmodifiableList(Arrays.asList(TetrominoTyp.values()));
    private static final int                ANZAHL = TYPEN.size();
    private static final Random             ZUFALL = new Random();

    @Override
    public TetrominoTyp erstelleZufaelligenTetrominoTyp() {
        return TYPEN.get(ZUFALL.nextInt(ANZAHL));
    }

    @Override
    public TetrominoSpielstein erstelleTetromino(TetrominoTyp typ) {

        switch (typ) {

            case BLOCK:
                return new TetrominoBlock();

            case LANGER:
                return new TetrominoLanger();

            case L:
                return new TetrominoL();

            case UMGEDREHTES_L:
                return new TetrominoUmgedrehtesL();

            case T:
                return new TetrominoT();

            case Z:
                return new TetrominoZ();

            case UMGEDREHTES_Z:
                return new TetrominoUmgedrehtesZ();

            default:
                throw new IllegalStateException("TetrominoTyp " + typ + " ist nicht bekannt!");
        }
    }

    @Override
    public TetrominoSpielstein erstelleTetromino(TetrominoTyp typ, int x, int y) {

        switch (typ) {

            case BLOCK:
                return new TetrominoBlock(x, y);

            case LANGER:
                return new TetrominoLanger(x, y);

            case L:
                return new TetrominoL(x, y);

            case UMGEDREHTES_L:
                return new TetrominoUmgedrehtesL(x, y);

            case T:
                return new TetrominoT(x, y);

            case Z:
                return new TetrominoZ(x, y);

            case UMGEDREHTES_Z:
                return new TetrominoUmgedrehtesZ(x, y);

            default:
                throw new IllegalStateException("TetrominoTyp " + typ + " ist nicht bekannt!");
        }
    }
}