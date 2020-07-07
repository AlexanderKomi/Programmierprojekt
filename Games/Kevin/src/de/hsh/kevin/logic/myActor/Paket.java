package de.hsh.kevin.logic.myActor;

import common.actor.Actor;
import java.util.List;

/**
 * Erstellt ein Paket
 * @author Kevin
 *
 */
public class Paket extends Actor {

    private static final double startX = 100;
    private static final double startY = 10;
    private static final int changePictureDelay = 15;
    private double speed = 3;
    private enmPaketTyp paketTyp;

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param paketTyp
     */
    public Paket(String pictureFileName, enmPaketTyp paketTyp) {
        this(pictureFileName, startX, startY, paketTyp);
    }

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param x
     * @param y
     * @param paketTyp
     */
    public Paket(String pictureFileName, double x, double y, enmPaketTyp paketTyp) {
        super(pictureFileName, x, y);
        this.paketTyp = paketTyp;
    }

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param paketTyp
     */
    public Paket(List<String> pictureFileName, enmPaketTyp paketTyp) {
        this(pictureFileName, startX, startY, paketTyp);
    }

    /**
     * Erstellt ein Paket
     * @param pictureFileName
     * @param x
     * @param y
     * @param paketTyp
     */
    public Paket(List<String> pictureFileName, double x, double y, enmPaketTyp paketTyp) {
        super(pictureFileName, x, y, changePictureDelay);
        this.paketTyp = paketTyp;
    }

    /**
     * Liefert den Typen des Pakets
     * @return Typ des Pakets
     */
    public enmPaketTyp getPaketTyp() {
        return paketTyp;
    }

    /**
     * Bewegt das Paket
     */
    public void move() {
        setPos(this.getX(), this.getY() + speed);
    }

    public String toString() {
        return super.toString();
    }

}
