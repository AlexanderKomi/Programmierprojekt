package de.hsh.kevin.logic.myActor;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.hsh.kevin.logic.Config;

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

    public static Paket getBadPaket(double x, double y) {
	return createPaket(x, y, enmPaketTyp.bad, badPaketImages);
    }

    public static Paket getGoodPaket(double x, double y) {
	return createPaket(x, y, enmPaketTyp.good, goodPaketImages);
    }

    private static Paket createPaket(double x, double y, enmPaketTyp typ, ArrayList<String> images) {
	Paket p = null;
	try {
	    p = new Paket(images, typ);
	    p.setPos(x, y);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return p;
    }

}
