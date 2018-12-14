package common.actor;

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
    private List<Actor> collisionActors = Collections.synchronizedList( new ArrayList<>() );

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

    @Override
    protected double[] beforeDrawing( final double[] current_pos, final double[] new_pos ) {
        if ( this.doesCollide() ) {
            return current_pos;
        }
        else {
            return new_pos;
        }
    }

    public boolean doesCollide() {
        try {
            final List<Actor> list = Collections.synchronizedList( this.getCollisionActors() );
            synchronized ( list ) {
                int size = list.size();
                for ( int i = 0 ; i < size ; i++ ) {
                    final Actor a = list.get( i );
                    synchronized ( a ) {
                        if ( this.doesCollide( a ) ) {
                            return true;
                        }
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
        final boolean b = CollisionCheck.doesCollide( this, other ) ||
                          CollisionCheck.doesCollide( other, this );
        if ( b ) {
            if ( this instanceof ControlableActor && other instanceof Collectable ) {
                final Collectable c = (Collectable) other;
                c.wasCollected( this );
                return false;
            }
        }
        return b;
    }

    public void setCollisionActors( final List<Actor> list ) {
        this.collisionActors = list;
    }

    // ----------------------------------- GETTER AND SETTER -----------------------------------
    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    public List<Actor> getCollisionActors() {
        return this.collisionActors;
    }

    public boolean addCollidingActor( Actor a ) {
        if ( !this.collisionActors.contains( a ) ) {
            return this.collisionActors.add( a );
        }
        return false;
    }

}
