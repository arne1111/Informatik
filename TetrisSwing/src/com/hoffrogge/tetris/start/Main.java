package com.hoffrogge.tetris.start;

import com.hoffrogge.tetris.logik.Spiel;
import com.hoffrogge.tetris.model.Spielfenster;

public class Main {

	public static void main(String[] args) {

		Spielfenster spielfenster = new Spielfenster();

		Spiel spiel = new Spiel(spielfenster);

		spiel.starteSpiel();
	}
}