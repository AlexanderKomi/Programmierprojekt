package common.actor;

import common.util.Logger;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
class BoundsChecks {

    static boolean doesCollide( Actor a, Actor b ) {
        return BoundsChecks.check1( a, b ) ||
               BoundsChecks.check2( a, b ) ||
               BoundsChecks.check3( a, b ) ||
               BoundsChecks.check4( a, b ) ||
               BoundsChecks.check5( a, b ) ||
               BoundsChecks.check6( a, b )
                ;
    }

    /**
     * Untere rechte Ecke von a, schneidet obere Linke Ecke von b.
     */
    private static boolean check1( Actor a, Actor b ) {
        if ( a.getX() < b.getX() ) {
            if ( a.getX() + a.width > b.getX() ) {
                if ( a.getX() < b.getY() ) {
                    if ( a.getX() + a.height > b.getY() ) {
                        Logger.log( "Check 1" );
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Untere linke Ecke von a, schneidet obere rechte Ecke von b.
     */
    private static boolean check2( Actor a, Actor b ) {
        if ( a.getX() < b.getX() + b.width ) {
            if ( a.getY() + a.height > b.getY() ) {

                Logger.log( "Check 2" );
                Logger.log( a );
                Logger.log( b );
                Logger.log( "\n" );

                return true;
            }
        }
        return false;
    }

    /**
     * b schneidet a mittig von links.
     */
    private static boolean check3( Actor a, Actor b ) {
        if ( a.getX() < b.getX() + b.width ) {
            if ( a.getY() + a.height > b.getX() + b.width ) {
                Logger.log( "Check 3" );
                return true;
            }
        }
        return false;
    }


    private static boolean check4( Actor a, Actor b ) {
        if ( a.getX() < b.getX() + b.width ) {
            if ( a.getY() < b.getX() + b.height + b.width ) {
                Logger.log( "Check 4" );
                return true;
            }
        }
        return false;
    }

    private static boolean check5( Actor a, Actor b ) {
        return false;
    }

    private static boolean check6( Actor a, Actor b ) {
        return false;
    }

}
