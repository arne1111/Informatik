package com.hoffrogge.tetris.model.tetromino;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import com.hoffrogge.tetris.model.Farbe;
import com.hoffrogge.tetris.model.Punkt;
import com.hoffrogge.tetris.model.TetrisKonstanten;

public class Quadrat implements TetrominoSpielstein {

    private int   mittelpunktX;
    private int   mittelpunktY;
    private int   durchmesser;

    private Farbe farbe;

    public Quadrat() {

        farbe = new Farbe(255, 0, 255);

        durchmesser = 50;

        mittelpunktX = TetrisKonstanten.SPIELFELD_BREITE / 2;
        mittelpunktY = durchmesser;
    }

    @Override
    public void zeichnen(Graphics graphics) {

        graphics.setColor(farbe.konvertiereZuColor());

        int halbeKantenlaenge = durchmesser / 2;

        int linksObenX = mittelpunktX - halbeKantenlaenge;
        int linksObenY = mittelpunktY - halbeKantenlaenge;

        int rechtsObenX = mittelpunktX + halbeKantenlaenge;
        int rechtsObenY = mittelpunktY - halbeKantenlaenge;

        int rechtsUntenX = mittelpunktX + halbeKantenlaenge;
        int rechtsUntenY = mittelpunktY + halbeKantenlaenge;

        int linksUntenX = mittelpunktX - halbeKantenlaenge;
        int linksUntenY = mittelpunktY + halbeKantenlaenge;

        graphics.drawLine(linksObenX, linksObenY, rechtsObenX, rechtsObenY);
        graphics.drawLine(rechtsObenX, rechtsObenY, rechtsUntenX, rechtsUntenY);
        graphics.drawLine(rechtsUntenX, rechtsUntenY, linksUntenX, linksUntenY);
        graphics.drawLine(linksUntenX, linksUntenY, linksObenX, linksObenY);

        graphics.fillRect(linksObenX, linksObenY, durchmesser, durchmesser);
    }

    @Override
    public void setMittelpunkt(Punkt mittelpunkt) {
        this.mittelpunktX = mittelpunkt.getX();
        this.mittelpunktY = mittelpunkt.getY();
    }

    @Override
    public void setLinienFarbe(Farbe farbe) {
        this.farbe = farbe;
    }

    @Override
    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    @Override
    public Punkt getMittelPunkt() {
        return new Punkt(mittelpunktX, mittelpunktY);
    }

    @Override
    public int getHoechstesY() {
        return mittelpunktY - durchmesser / 2;
    }

    @Override
    public int getTiefstesY() {
        return mittelpunktY + durchmesser / 2;
    }

    @Override
    public int getKanteLinksX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getKanteRechtsX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void bewegeNachUnten() {
        mittelpunktY += 50;
    }

    @Override
    public void bewegeNachRechts() {
        mittelpunktX += 50;
    }

    @Override
    public void bewegeNachLinks() {
        mittelpunktX -= 50;
    }

    @Override
    public boolean faelltAuf(TetrominoSpielstein block) {

        return mittelpunktX == block.getX() && mittelpunktY + durchmesser == block.getY();
    }

    @Override
    public void rotiereNachLinks() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<TetrominoSpielstein> getViertelBloecke() {
        return Arrays.asList(this);
    }

    @Override
    public Farbe getLinienFarbe() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Farbe getFuellFarbe() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFuellFarbe(Farbe farbe) {
        // TODO Auto-generated method stub

    }

    @Override
    public int compareTo(TetrominoSpielstein o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getX() {
        return mittelpunktX;
    }

    @Override
    public void setX(int x) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getY() {
        return mittelpunktY;
    }

    @Override
    public void setY(int y) {
        // TODO Auto-generated method stub

    }
}