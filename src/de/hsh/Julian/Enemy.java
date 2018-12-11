package de.hsh.Julian;

import common.actor.Collectable;
import common.config.WindowConfig;

import java.io.FileNotFoundException;

public class Enemy extends Collectable {

    public static final String imageLocation = Leertastenklatsche.location + "enemyvirus.png";
    public static final String imageLocation2 = Leertastenklatsche.location + "enemyvirus_turned.png";

    public Enemy( String imagePath ) throws FileNotFoundException {
        super( imagePath );
    }

    // Gegner erstellen und zufällig links oder rechts starten lassen
    static Enemy createEnemy() throws FileNotFoundException {

        Enemy  enemyvirus = new Enemy( imageLocation );
        double px         = WindowConfig.window_width - enemyvirus.getWidth();
        /*double px = 350 * Math.random() + 50;
        double py = 350 * Math.random() + 50;*/
        double rng = Math.random();
        if ( rng < 0.5 ) {
            px = enemyvirus.getWidth();
            enemyvirus.setCurrentImage(imageLocation2);
        }
        //Logger.log(this.getClass() + ": rng=" + rng);
        enemyvirus.setPos( px, WindowConfig.window_height * 0.9 );
        return enemyvirus;
    }
}
