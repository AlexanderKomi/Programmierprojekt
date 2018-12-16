package de.hsh.kevin.logic.myActor;

import java.util.ArrayList;

import de.hsh.kevin.logic.Config;

/**
 * Erstellt Paktete
 * @author Kevin
 *
 */
public class PaketFactory {
    private static ArrayList<String> badPaketImages;
    private static ArrayList<String> goodPaketImages;

    static {
        if (badPaketImages == null) {
            badPaketImages = new ArrayList<>();
            badPaketImages.add(Config.resLocation + "ordner_red/ordner1.png");
            badPaketImages.add(Config.resLocation + "ordner_red/ordner2.png");
            badPaketImages.add(Config.resLocation + "ordner_red/ordner3.png");
            badPaketImages.add(Config.resLocation + "ordner_red/ordner4.png");
        }
        if (goodPaketImages == null) {
            goodPaketImages = new ArrayList<>();
            goodPaketImages.add(Config.resLocation + "ordner_black/ordner1.png");
            goodPaketImages.add(Config.resLocation + "ordner_black/ordner2.png");
            goodPaketImages.add(Config.resLocation + "ordner_black/ordner3.png");
            goodPaketImages.add(Config.resLocation + "ordner_black/ordner4.png");

        }
    }

    /**
     * Erstellt ein BadPaket an Positon (x,y)
     * @param x - Wert der Positon des Pakets
     * @param y - Wert der Positon des Pakets
     * @return Paket
     */
    public static Paket getBadPaket(double x, double y) {
        return createPaket(x, y, enmPaketTyp.bad, badPaketImages);
    }

    /**
     * Erstellt ein GoodPaket an Positon (x,y)
     * @param x - Wert der Positon des Pakets
     * @param y - Wert der Positon des Pakets
     * @return Paket
     */
    public static Paket getGoodPaket(double x, double y) {
        return createPaket(x, y, enmPaketTyp.good, goodPaketImages);
    }

    /**
     * Erstellt ein Paket an Positon (x,y)
     * @param x - Wert der Positon des Pakets
     * @param y - Wert der Positon des Pakets
     * @return Paket
     */
    private static Paket createPaket(double x, double y, enmPaketTyp typ, ArrayList<String> images) {
        Paket p = new Paket(images, typ);
        p.setPos(x, y);

        return p;
    }

    /**
     * Erstellt ein BadPaket
     * @return Paket
     */
    public static Paket getBadPaket() {
        return createPaket(enmPaketTyp.bad, badPaketImages);
    }

    /**
     * Erstellt ein GoodPaket
     * @return Paket
     */
    public static Paket getGoodPaket() {
        return createPaket(enmPaketTyp.good, goodPaketImages);
    }

    /**
     * Erstellt ein Paket
     * @return Paket
     */
    private static Paket createPaket(enmPaketTyp typ, ArrayList<String> images) {
        return createPaket(0, 0, typ, images);
    }

}
