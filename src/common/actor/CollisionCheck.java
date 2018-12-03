package common.actor;

import javafx.scene.canvas.Canvas;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
class CollisionCheck {

    /**
     * Returns an boolean Array with index 0 equals x coordinate and
     * index 1 equals y coordinate
     *
     * @author Alex
     * @author Kevin
     */
    public static boolean[] isInBounds( Drawable a, Canvas canvas, double new_x, double new_y ) {
        boolean[] temp = new boolean[] {
                false, false
        };

        if ( (a.getX() + new_x) >= 0 &&
             (a.getX() + new_x + a.width) <= canvas.getWidth() ) {
            temp[ 0 ] = true;
        }
        if ( (a.getY() + new_y >= 0) &&
             (a.getY() + new_y + a.height) <= canvas.getHeight() ) {
            temp[ 1 ] = true;
        }
        return temp;
    }

    static boolean doesCollide( Drawable a, Drawable b ) {
        return checkUpperLeftCorner( a, b ) || checkUpperRightCorner( a, b ) || checkLowerRightCorner( a, b )
               || checkLowerLeftCorner( a, b );
    }


    /**
     * Obere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     *
     * @return
     */
    private static boolean checkUpperLeftCorner( Drawable a, Drawable b ) {
        if ( a.getX() <= b.getX() && a.getX() + a.width >= b.getX() ) {
            return a.getY() <= b.getY() && a.getY() + a.height >= b.getY();
        }
        return false;
    }


    /**
     * Obere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     *
     * @return
     */
    private static boolean checkUpperRightCorner( Drawable a, Drawable b ) {
        if ( a.getX() <= b.getX() + b.width && a.getX() + a.width >= b.getX() + b.width ) {
            return a.getY() <= b.getY() && a.getY() + a.height >= b.getY();
        }
        return false;
    }

    /**
     * Untere Rechte Ecke von b schneidet a
     *
     * @param a
     * @param b
     *
     * @return
     */
    private static boolean checkLowerRightCorner( Drawable a, Drawable b ) {
        if ( a.getX() <= b.getX() + b.width && a.getX() + a.width >= b.getX() + b.width ) {
            return a.getY() <= b.getY() + b.height && a.getY() + a.height >= b.getY() + b.height;
        }
        return false;
    }

    /**
     * Untere Linke Ecke von b schneidet a
     *
     * @param a
     * @param b
     *
     * @return
     */
    private static boolean checkLowerLeftCorner( Drawable a, Drawable b ) {
        if ( a.getX() <= b.getX() && a.getX() + a.width >= b.getX() ) {
            return a.getY() <= b.getY() + b.height && a.getY() + a.height >= b.getY() + b.height;
        }
        return false;
    }

}
