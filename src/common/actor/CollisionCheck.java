package common.actor;

import javafx.scene.canvas.Canvas;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
public class CollisionCheck {

    public static boolean[] isInBounds( Drawable a, Canvas canvas ) {
        return isInBounds( a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas );
    }

    public static boolean[] isInBounds( double x,
                                        double y,
                                        double width,
                                        double height,
                                        double canvas_widht,
                                        double canvas_height,
                                        double new_x,
                                        double new_y ) {
        boolean[] temp = new boolean[] {
                false, false
        };

        if ( (x + new_x) >= 0 &&
             (x + new_x + width) <= canvas_widht ) {
            temp[ 0 ] = true;
        }
        if ( (y + new_y) >= 0 &&
             (y + new_y + height) <= canvas_height ) {
            temp[ 1 ] = true;
        }
        return temp;
    }

    public static boolean[] isInBounds( double x, double y, double width, double height, Canvas canvas ) {
        return isInBounds( x, y, width, height, canvas.getWidth(), canvas.getHeight(), 0, 0 );
    }

    /**
     * Returns an boolean Array with index 0 equals x coordinate and
     * index 1 equals y coordinate
     *
     * @author Alex
     * @author Kevin
     */
    public static boolean[] isInBounds( Drawable a, Canvas canvas, double new_x, double new_y ) {
        return isInBounds( a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas.getWidth(), canvas.getHeight(), new_x, new_y );
    }

    /**
     * Returns an boolean Array with index 0 equals x coordinate and
     * index 1 equals y coordinate
     *
     * @author Alex
     * @author Kevin
     */
    public static boolean[] isInBounds( Drawable a, double canvas_widht, double canvas_height, double new_x, double new_y ) {
        return isInBounds( a.getX(), a.getY(), a.getWidth(), a.getHeight(), canvas_widht, canvas_height, new_x, new_y );
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
        if ( a.getX() <= b.getX() && a.getX() + a.getWidth() >= b.getX() ) {
            return a.getY() <= b.getY() && a.getY() + a.getHeight() >= b.getY();
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
        if ( a.getX() <= b.getX() + b.getWidth() && a.getX() + a.getWidth() >= b.getX() + b.getWidth() ) {
            return a.getY() <= b.getY() && a.getY() + a.getHeight() >= b.getY();
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
        if ( a.getX() <= b.getX() + b.getWidth() && a.getX() + a.getWidth() >= b.getX() + b.getWidth() ) {
            return a.getY() <= b.getY() + b.getHeight() && a.getY() + a.getHeight() >= b.getY() + b.getHeight();
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
        if ( a.getX() <= b.getX() && a.getX() + a.getWidth() >= b.getX() ) {
            return a.getY() <= b.getY() + b.getHeight() && a.getY() + a.getHeight() >= b.getY() + b.getHeight();
        }
        return false;
    }

}
