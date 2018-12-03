package common.actor;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
class CollisionCheck {

    static final double speed = 10;

    static boolean doesCollide( Actor a, Actor b ) {
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
    private static boolean checkUpperLeftCorner( Actor a, Actor b ) {
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
    private static boolean checkUpperRightCorner( Actor a, Actor b ) {
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
    private static boolean checkLowerRightCorner( Actor a, Actor b ) {
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
    private static boolean checkLowerLeftCorner( Actor a, Actor b ) {
        if ( a.getX() <= b.getX() && a.getX() + a.width >= b.getX() ) {
            return a.getY() <= b.getY() + b.height && a.getY() + a.height >= b.getY() + b.height;
        }
        return false;
    }

}
