package common.actor;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Bounds checking for collision.
 *
 * @author Kevin Jeske
 * @author Alexander Komischke
 */
class CollisionCheck {

    static boolean doesCollide( Actor a, Actor b ) {
        return checkUpperLeftCorner( a, b ) || checkUpperRightCorner( a, b ) || checkLowerRightCorner( a, b )
               || checkLowerLeftCorner( a, b );
    }

    static boolean applyCollision( Actor a ){
        AtomicBoolean collided = new AtomicBoolean( false );
        a.getCollisionActors().forEach( collisionActor -> {
            if(a.doesCollide( collisionActor)){
                a.setX( collideX(a, collisionActor) );
                a.setY( collideY(a, collisionActor) );
                collided.set( true );
            }
        } );
        return collided.get();
    }

    private static double collideX(Actor a, Actor collisionActor){
        double speed = 10;
        double new_x = a.getX();
        if(new_x + a.getWidth() >= collisionActor.getX() + collisionActor.getWidth()){
            new_x = ( new_x + speed );
        }
        else if(new_x + a.getWidth() >= collisionActor.getX()){
            new_x = ( new_x - speed );
        }
        return new_x;
    }

    private static double collideY(Actor a, Actor collisionActor){
        double speed = 10;
        double new_y = a.getY();
        if(new_y <= collisionActor.getY() + collisionActor.getHeight()){
            new_y = ( new_y - speed );
        }
        else if(new_y + a.getHeight() >= collisionActor.getY()){
            new_y = ( new_y + speed );
        }
        return new_y;
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
