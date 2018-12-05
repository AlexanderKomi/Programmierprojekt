package common.actor;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Actor extends Drawable {


    protected Movement       movement        = new Movement();
    private   HashSet<Actor> collisionActors = new HashSet<>();

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

    public Actor( double x, double y, int delay, HashSet<Actor> collisionActors, String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
        this.collisionActors = collisionActors;
    }

    public Actor( List<String> pictureFilePaths ) {
        this( pictureFilePaths, 0, 0, 0 );
    }

    public Actor( List<String> pictureFilePaths, double x, double y, int delay ) {
        super( pictureFilePaths, x, y, delay );
    }

    public Actor( double x, double y, int delay, String... pictureFilePaths ) {
        super( x, y, delay, pictureFilePaths );
    }

    @Override
    protected double[] beforeDrawing( double[] current_pos, double[] new_pos ) {
        if ( this.doesCollide() ) {
            return current_pos;
        }
        return new_pos;
    }

    public boolean doesCollide() {
        try {
            return this.getCollisionActors()
                       .stream()
                       .anyMatch( this::doesCollide );
        }
        catch ( ConcurrentModificationException cme ) {
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
        }
        return b ||
               CollisionCheck.doesCollide( other, this )
                ;
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
