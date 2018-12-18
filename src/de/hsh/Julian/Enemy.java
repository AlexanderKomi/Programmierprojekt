package de.hsh.Julian;

import common.actor.Collectable;
import common.config.WindowConfig;

final class Enemy extends Collectable {

    private static final String imageLocation  = Leertastenklatsche.location + "enemyvirus.png";
    private static final String imageLocation2 = Leertastenklatsche.location + "enemyvirus_turned.png";

    private Enemy( final String imagePath ) {
        super( imagePath );
    }

    // Gegner erstellen und zufällig links oder rechts starten lassen
    static Enemy createEnemy() {

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
        enemyvirus.setPos( px, WindowConfig.window_height * 0.85 );
        return enemyvirus;
    }
}
