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

    /**
     *
     * @param pictureFileName Filename of picture for actor
     * @param collisionActors Gives a list of Collision"able" actors
     */
    public Actor( final String pictureFileName,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFileName );
        this.collisionActors = collisionActors;
    }

    /**
     *
     * @param pictureFileName Filename of picture for actor
     * @param x Position X for Actor
     * @param y Position Y for Actor

     * @param collisionActors Gives a list of Collision"able" actors
     */
    public Actor( final String pictureFileName,
                  final double x,
                  final double y,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFileName, x, y );
        this.collisionActors = collisionActors;
    }

    /**
     *
     * @param mustHavePicture forcing use of picture
     * @param pictureFilePaths Filename of picture for actor
     */
    public Actor( final String mustHavePicture,
                  final String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
    }

    /**
     *
     * @param pictureFilePaths Filename of picture for actor
     */
    public Actor( final String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    /**
     *
     * @param collisionActors Gives a list of Collision"able" actors
     * @param mustHavePicture forcing use of picture
     * @param pictureFilePaths Filename of picture for actor
     */
    public Actor( final ArrayList<Actor> collisionActors,
                  final String mustHavePicture,
                  final String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    /**
     *
     * @param pictureFilePaths Filename of picture for actor
     * @param collisionActors Filename of picture for actor
     */
    public Actor( final List<String> pictureFilePaths,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths );
        this.collisionActors = collisionActors;
    }


    public Actor( final List<String> pictureFilePaths,
                  final double x,
                  final double y,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( final List<String> pictureFilePaths,
                  final double x,
                  final double y,
                  final int delay,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths, x, y, delay );
        this.collisionActors = collisionActors;
    }

    public Actor( final double x,
                  final double y,
                  final int delay,
                  final ArrayList<Actor> collisionActors,
                  final String mustHave,
                  final String... pictureFilePaths ) {
        super( x, y, delay, mustHave, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( final List<String> pictureFilePaths ) {
        this( pictureFilePaths, 0, 0, 0 );
    }

    public Actor( final List<String> pictureFilePaths, final double x, final double y, final int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public Actor( final double x, final double y, final int delay, final String mustHave, final String... pictureFilePaths ) {
        super( x, y, delay, mustHave, pictureFilePaths );
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
        }
        else {
            return new_pos;
        }
    }

    /**
     * Checks actor and a list of collisionActors for collision
     * @return returns true, if collided, else false
     */
    private boolean doesCollide() {
        try {
            final List<Actor> list = Collections.synchronizedList( this.getCollisionActors() );
            synchronized ( list ) {
                int size = list.size();
                for ( int i = 0 ; i < size ; i++ ) {
                    if ( this.doesCollide( list.get( i ) ) ) {
                            return true;
                        }

                    size = list.size();
                }
            }
            return false;
        }
        catch ( NullPointerException | ConcurrentModificationException npe ) {
            npe.printStackTrace();
            return true;
        }
    }

    /**
     *
     * @param other instance of actor
     * @return returns if collisioned or not
     */
    public synchronized boolean doesCollide( final Actor other ) {
        boolean b = CollisionCheck.doesCollide( this, other ) ||
                    CollisionCheck.doesCollide( other, this );
        if ( b ) {
            b = collisionModifier( other );
        }
        return b;
    }

    /**
     * returns if two objects are in bounds of each other by checking coordinates
     * @param canvas_width width
     * @param canvas_height height
     * @return returns if
     */
    public boolean isInBounds( double canvas_width, double canvas_height ) {
        boolean[] temp = CollisionCheck.isInBounds( this, canvas_width, canvas_height );
        return (temp[ 0 ] && temp[ 1 ]);
    }

    /**
     * Collision Modifier
     * @param other the Actor
     * @return always true
     */
    protected synchronized boolean collisionModifier( final Actor other ) {
        return true;
    }

    /**
     * Plays a sound
     * @param filePath Path of soundfile
     */
    protected synchronized void playSound( final String filePath ) {
        PlaySound.playSound( filePath );
    }

    /**
     * Sets or removes collisionactors
     * @param list List of collisionactors
     */
    // ----------------------------------- GETTER AND SETTER -----------------------------------
    private void setCollisionActors( final List<Actor> list ) {
        this.collisionActors = list;
    }

    public void removeCollisionActor( Collectable collectable ) {
        final List<Actor> l = Collections.synchronizedList( this.getCollisionActors() );
        synchronized ( l ) {
            boolean b = l.remove( collectable );
            if ( !b ) {
                Logger.log( "------>" + this.getClass() + " FATAL ERROR : Can not delete: " + collectable );
            }
            else {
                onRemove( collectable );
            }
        }
        this.setCollisionActors( l );
    }

    protected void onRemove( final Collectable collectable ) {}

    /**
     *  Sets the velocity
     * @param speed parameter for speed as a double
     */
    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    private List<Actor> getCollisionActors() {
        return this.collisionActors;
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
