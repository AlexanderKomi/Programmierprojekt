/**
 * @author Julian Sender
 */
package de.hsh.Julian;

import common.actor.Collectable;
import common.config.WindowConfig;

/**
 * Enemyclass is collectable
 */
final class Enemy extends Collectable {

    private static final String imageLocation  = Leertastenklatsche.location + "enemyvirus.png";
    private static final String imageLocation2 = Leertastenklatsche.location + "enemyvirus_turned.png";

    private Enemy( final String imagePath ) {
        super( imagePath );
    }

    /**
     * GETTERS AND SETTERS
     *
     */
    public static String getImageLocation() {
        return imageLocation;
    }

    public static String getImageLocation2() {
        return imageLocation2;
    }

    /**
     * Create enemys and let them spawn randomly left or right
     * @return
     */
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
