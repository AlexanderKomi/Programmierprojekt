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


    Movement movement = new Movement();
    private List<Actor> collisionActors = Collections.synchronizedList( new ArrayList<>() );

    public Actor( String pictureFileName ) {
        this( pictureFileName, 0, 0 );
    }

    public Actor( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

    public Actor( String pictureFileName, ArrayList<Actor> collisionActors ) {
        super( pictureFileName );
        this.collisionActors = collisionActors;
    }

    public Actor( String pictureFileName, double x, double y, ArrayList<Actor> collisionActors ) {
        super( pictureFileName, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( String mustHavePicture, String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
    }

    public Actor( String[] pictureFilePaths ) {
        super( pictureFilePaths );
    }

    public Actor( ArrayList<Actor> collisionActors, String mustHavePicture, String... pictureFilePaths ) {
        super( mustHavePicture, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths, ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths, double x, double y, ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths, x, y );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths,
                  double x,
                  double y,
                  int delay,
                  ArrayList<Actor> collisionActors ) {
        super( pictureFilePaths, x, y, delay );
        this.collisionActors = collisionActors;
    }

    public Actor( double x,
                  double y,
                  int delay,
                  ArrayList<Actor> collisionActors,
                  String mustHave,
                  String... pictureFilePaths ) {
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

            for ( Actor a : this.getCollisionActors() ) {
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
            //cme.printStackTrace();
            //Logger.log(this.getClass() +": Exception : " + " : " + cme.getMessage());
            return true;
        }

    }

    public boolean doesCollide( Actor other ) {
        boolean b = CollisionCheck.doesCollide( this, other ) ||
                    CollisionCheck.doesCollide( other, this );
        if ( b ) {
            if ( other instanceof Collectable ) {
                Collectable c = (Collectable) other;
                c.wasCollected( this );
                return false;
            }
        }
        return b;
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
        return this.collisionActors.add( a );
    }

}
