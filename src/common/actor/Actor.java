package common.actor;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Actor is a drawable with a few added features.
 * A commonly used feature is collision with other actors or the movement of an actor, when it should be drawn.
 */
abstract public class Actor extends Drawable {


    Movement movement = new Movement();
    private HashSet<Actor> collisionActors = new HashSet<>();

    public Actor( String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    public Actor( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    public Actor( String pictureFileName, HashSet<Actor> collisionActors ) {
        super( pictureFileName );
        this.collisionActors = collisionActors;
    }

    public Actor( String pictureFileName, double x, double y, HashSet<Actor> collisionActors ) {
        super( pictureFileName, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( String mustHavePicture, String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
    }

    public Actor( String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public Actor( HashSet<Actor> collisionActors, String mustHavePicture, String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths, HashSet<Actor> collisionActors ) {
        super( pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths, double x, double y, HashSet<Actor> collisionActors ) {
        super( pictureFilePaths, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths,
                  double x,
                  double y,
                  int delay,
                  HashSet<Actor> collisionActors ) {
        super( pictureFilePaths, x, y, delay );
        this.collisionActors = collisionActors;
    }

    public Actor( double x, double y, int delay, HashSet<Actor> collisionActors, String mustHave, String... pictureFilePaths ) {
        super( x, y, delay, mustHave, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths ) {
        this( pictureFilePaths, 0, 0, 0 );
    }

    public Actor( List<String> pictureFilePaths, double x, double y, int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public Actor( double x, double y, int delay, String mustHave, String... pictureFilePaths ) {
        super( x, y, delay, mustHave, pictureFilePaths );
    }

    public Actor( String mustHave, List<String> asList, double x, double y, int delay ) {
        super( mustHave, asList, x, y, delay );
    }

    public Actor( double x, double y, int delay, String[] pictureFileName ) {
        super( x, y, delay, pictureFileName );
    }

    @Override
    protected double[] beforeDrawing( double[] current_pos, double[] new_pos ) {
        if ( this.doesCollide() ) {
            return current_pos;
        }
        else {
            return new_pos;
        }
    }

    public boolean doesCollide() {
        try {
            for ( Actor a : this.collisionActors ) {
                if ( this.doesCollide( a ) ) {
                    return true;
                }
            }
            return false;
        }
        catch ( NullPointerException npe ) {
            npe.printStackTrace();
            return true;
        }
        catch ( ConcurrentModificationException cme ) {
            cme.printStackTrace();
            //Logger.log(this.getClass() +": Exception : " + " : " + cme.getMessage());
            return true;
        }
    }

    public boolean doesCollide( Actor other ) {
        boolean b = CollisionCheck.doesCollide( this, other );
        if ( b ) {
            if ( other instanceof Collectable ) {
                Collectable c = (Collectable) other;
                c.wasCollected( this );
            }
            return true;
        }
        boolean b2 = CollisionCheck.doesCollide( other, this );
        if ( b2 ) {
            if ( other instanceof Collectable ) {
                Collectable c = (Collectable) other;
                c.wasCollected( this );
            }
            return true;
        }
        return false;
        //return CollisionCheck.doesCollide( other, this );
    }

    public List<Actor> getCollidingActors() throws ConcurrentModificationException {
        return this.getCollisionActors()
                   .stream()
                   .filter( this::doesCollide )
                   .collect( Collectors.toList() );
    }

    // ----------------------------------- GETTER AND SETTER -----------------------------------
    public void setSpeed( double speed ) {
        this.movement.setVelocity( speed );
    }

    public double getSpeed() {
        return this.movement.getVelocity();
    }

    public HashSet<Actor> getCollisionActors() {
        return collisionActors;
    }

    public boolean addCollidingActor( Actor a ) {
        return this.collisionActors.add( a );
    }

}
