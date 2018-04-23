package com.hoffrogge.tetris.start;

import com.hoffrogge.tetris.logik.GameLoop;
import com.hoffrogge.tetris.model.Spielfenster;

public class Main {

	public static void main(String[] args) {

		Spielfenster spielfenster = new Spielfenster();

		GameLoop gameLoop = new GameLoop(spielfenster.getSpielfeld());

		gameLoop.starteSpiel();
	}
}
