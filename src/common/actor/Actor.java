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

    public Actor( final String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    public Actor( final String pictureFileName,
                  final double x,
                  final double y ) {
        super( pictureFileName, x, y );
    }

    public Actor( final String pictureFileName,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFileName );
        this.collisionActors = collisionActors;
    }

    public Actor( final String pictureFileName,
                  final double x,
                  final double y,
                  final ArrayList<Actor> collisionActors ) {
        super( pictureFileName, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( final String mustHavePicture,
                  final String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
    }

    public Actor( final String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public Actor( final ArrayList<Actor> collisionActors,
                  final String mustHavePicture,
                  final String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

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

    @Override
    protected double[] beforeDrawing( final double[] current_pos, final double[] new_pos ) {
        if ( this.doesCollide() ) {
            return current_pos;
        }
        else {
            return new_pos;
        }
    }

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

    public synchronized boolean doesCollide( final Actor other ) {
        boolean b = CollisionCheck.doesCollide( this, other ) ||
                    CollisionCheck.doesCollide( other, this );
        if ( b ) {
            b = collisionModifier( other );
        }
        return b;
    }

    public boolean isInBounds( double canvas_width, double canvas_height ) {
        boolean[] temp = CollisionCheck.isInBounds( this, canvas_width, canvas_height );
        return (temp[ 0 ] && temp[ 1 ]);
    }

    protected synchronized boolean collisionModifier( final Actor other ) {
        return true;
    }

    protected synchronized void playSound( final String filePath ) {
        PlaySound.playSound( filePath );
    }

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

    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    private List<Actor> getCollisionActors() {
        return this.collisionActors;
    }

    public void addCollidingActor( Actor a ) {
        if ( !this.collisionActors.contains( a ) ) {
            this.collisionActors.add( a );
        }
    }
}
