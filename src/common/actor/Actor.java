package common.actor;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Actor extends Drawable {


    private HashSet<Actor>   collisionActors = new HashSet<>();

    protected Actor( String pictureFileName ) throws FileNotFoundException {
        this( pictureFileName, 0, 0 );
    }

    public Actor( String pictureFileName, double x, double y ) throws FileNotFoundException {
        super( pictureFileName, x, y );
    }

    protected Actor( List<String> pictureFilePaths, double x, double y, int delay )
            throws FileNotFoundException {
        super( pictureFilePaths, x, y, delay );
    }

    Actor( double x, double y, int delay, String... pictureFilePaths ) throws FileNotFoundException {
        this( Arrays.asList( pictureFilePaths ), x, y, delay );
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

    public HashSet<Actor> getCollisionActors() {
        return collisionActors;
    }

    boolean addCollidingActor( Actor a ) {
        return this.collisionActors.add( a );
    }

}
