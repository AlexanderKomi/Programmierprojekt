/**
 * @author Julian Sender
 */
package de.hsh.Julian;

import common.actor.Actor;
import common.actor.Collectable;

/**
 * Main actor of Leertastenklatsche
 */
final class TheDude extends Actor {

    private static final String firstImage  = Leertastenklatsche.location + "thedude.png";
    private static final String secondImage = Leertastenklatsche.location + "thedude_turned.png";

    boolean turnedleft = true;

    TheDude( double x, double y ) {
        super( firstImage, x, y );
    }

    /**
     * GETTERS AND SETTERS
     */
    public static String getFirstImage() {
        return firstImage;
    }

    public static String getSecondImage() {
        return secondImage;
    }

    public boolean isTurnedleft() {
        return turnedleft;
    }

    public void setTurnedleft(boolean turnedleft) {
        this.turnedleft = turnedleft;
    }

    /**
     * Swapping image depending on actors view-direction
     */
    void swapImage() {
        if ( this.getName().equals( firstImage ) ) {
            this.setCurrentImage( secondImage );
        }
        else {
            this.setCurrentImage( firstImage );
        }
    }

    /**
     *
     * @param other checks if collectable was collected
     * @return returns false or instance of collected item
     */
    @Override
    public synchronized boolean collisionModifier( Actor other ) {
        if ( other instanceof Collectable ) {
            final Collectable c = (Collectable) other;
            c.wasCollected( this );
            return false;
        }
        return true;
    }
}
