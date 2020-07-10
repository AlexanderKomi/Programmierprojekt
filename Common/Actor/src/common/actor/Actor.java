/**
 * @author Julian Sender
 */
package common.actor;

import common.util.Logger;
import common.util.PlaySound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Actor is a drawable with a few added features.
 * A commonly used feature is collision with other actors or the movement of an actor, when it should be drawn.
 */
abstract public class Actor extends Drawable {

    final   Movement    movement        = new Movement();
    private List<Actor> collisionActors = new ArrayList<>();

    /**
     * Many overloaded constructors following up now...
     * @param pictureFileName Filename of picture for actor
     */
    public Actor( final String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    /**
     *
     * @param pictureFileName  Filename of picture for actor
     * @param x Position X for Actor
     * @param y Position Y for Actor
     */
    public Actor( final String pictureFileName,
                  final double x,
                  final double y ) {
        super( pictureFileName, x, y );
    }

    public Actor( final List<String> pictureFilePaths, final double x, final double y, final int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public Actor( String mustHave, List<String> asList, final double x, final double y, final int delay ) {
        super( mustHave, asList, x, y, delay );
    }

    public Actor( double x, double y, int delay, String[] pictureFileName ) {
        super( x, y, delay, pictureFileName );
    }

    public Actor( double x, double y, double scale, String picturePath ) {super( x, y, scale, picturePath );}

    /**
     * Returning position based on collisioncheck
     * @param current_pos
     *         The current position of the Drawable
     * @param new_pos
     *         The next position of the Drawable
     *
     * @return
     */
    @Override
    protected double[] beforeDrawing( final double[] current_pos, final double[] new_pos ) {
        if ( this.doesCollide() ) {
            return current_pos;
        } else {
            return new_pos;
        }
    }

    /**
     * Checks actor and a list of collisionActors for collision
     * @return returns true, if collided, else false
     */
    private boolean doesCollide() {
        final List<Actor> list = Collections.synchronizedList(this.collisionActors);
        int size = list.size();
        for ( int i = 0 ; i < size ; i++ ) {
            if ( this.doesCollide( list.get( i ) ) ) {
                return true;
            }
            size = list.size();
        }
        return false;
    }

    /**
     *
     * @param other instance of actor
     * @return returns if collisioned or not
     */
    public synchronized boolean doesCollide( final Actor other ) {
        if ( CollisionCheck.doesCollide( this, other ) ||
             CollisionCheck.doesCollide( other, this )) {
            return collisionModifier( other );
        }
        return false;
    }

    /**
     * returns if two objects are in bounds of each other by checking coordinates
     * @param canvas_width width
     * @param canvas_height height
     * @return returns if
     */
    public boolean isInBounds(double canvas_width, double canvas_height ) {
        return CollisionCheck.isInBounds( this, canvas_width, canvas_height );
    }

    /**
     * Collision Modifier
     * @param other the Actor
     * @return always true
     */
    protected boolean collisionModifier( final Actor other ) {
        return true;
    }

    /**
     * Plays a sound
     * @param filePath Path of soundfile
     */
    protected void playSound( final String filePath ) {
        PlaySound.playSound( filePath );
    }

    public void removeCollisionActor( Collectable collectable ) {
        final List<Actor> l = Collections.synchronizedList( this.collisionActors );
        if (l.remove(collectable)) {
            onRemove( collectable );
        } else {
            Logger.log( "------>" + this.getClass() + " FATAL ERROR : Can not delete: " + collectable );
        }
        this.setCollisionActors( l );
    }

    protected void onRemove( final Collectable collectable ) {}

    // ----------------------------------- GETTER AND SETTER -----------------------------------
    /**
     *  Sets the velocity
     * @param speed parameter for speed as a double
     */
    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    private void setCollisionActors( final List<Actor> list ) {
        this.collisionActors = list;
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    /**
     * adds a colliding actor
     * @param a Actor
     */
    public void addCollidingActor( Actor a ) {
        if ( !this.collisionActors.contains( a ) ) {
            this.collisionActors.add( a );
        }
    }
}
