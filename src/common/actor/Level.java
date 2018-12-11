package common.actor;

import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.*;

/**
 * A simple level structure.
 *
 * @author Alex
 */
abstract public class Level extends Observable implements Observer, ILevel {

    private BackgroundImage                       backgroundImage = new BackgroundImage();
    private ArrayList<Actor>                      npcs            = new ArrayList<>();
    private LinkedList<ControlableActor>          players         = new LinkedList<>();
    private ArrayList<LevelElement>               levelElements   = new ArrayList<>();
    private ArrayList<WeakReference<Collectable>> collectables    = new ArrayList<>();


    public Level( Canvas gameCanvas ) {
        reset( gameCanvas );
    }

    private void addCollision() {
        levelElements.forEach(
                levelElement -> players.forEach(
                        pacMan -> pacMan.addCollidingActor( levelElement ) ) );
    }

    private void addCollectables() {
        collectables.forEach(
                collectable -> players.forEach(
                        player -> player.addCollidingActor( collectable.get() ) ) );
    }

    @Override
    public void render( Canvas canvas, int fps ) {
        try {
            backgroundImage.draw( canvas );
            npcs.forEach( npc -> npc.draw( canvas ) );
            for ( LevelElement levelElement : levelElements ) {
                levelElement.draw( canvas );
            }
            for ( WeakReference<Collectable> c : collectables ) {
                Objects.requireNonNull( c.get() ).draw( canvas );
            }
            for ( ControlableActor c : players ) {
                c.draw( canvas );
            }
            //collectables.forEach( collectable -> collectable.draw( canvas ) );
            //players.forEach( pacMan -> pacMan.draw( canvas ) );
        }
        catch ( ConcurrentModificationException cme ) {
            cme.printStackTrace();
        }
    }

    protected boolean collidesWithLevelElement( Actor a ) {
        for ( LevelElement levelElement : getLevelElements() ) {
            if ( levelElement.doesCollide( a ) ) {
                return true;
            }
        }
        return false;
    }


    protected boolean collidesWithPlayer( Actor d ) {
        for ( ControlableActor controlableActor : getPlayers() ) {
            if ( controlableActor.doesCollide( d ) ) {
                return true;
            }
        }
        return false;
    }

    protected boolean collidesWithCollectable( Actor a ) {
        for ( WeakReference<Collectable> coll : getCollectables() ) {
            if ( coll.get().doesCollide( a ) ) {
                return true;
            }
        }
        return false;
    }

    protected boolean collected( Collectable collectable ) {
        players.forEach(
                p -> {
                    p.getCollisionActors().remove( collectable );
                } );
        collectable.deleteObservers();
        return collectables.remove( collectable );
    }

    protected void addCollectables( Collection<Collectable> c ) {
        for ( Collectable collectable : c ) {
            addCollectable( collectable );
        }
    }

    protected boolean addCollectable( Collectable c ) {
        c.addObserver( this );
        players.forEach( player -> player.addCollidingActor( c ) );
        return this.collectables.add( new WeakReference<>( c ) );
    }

    protected boolean addPlayer( ControlableActor player ) {
        return this.players.add( player );
    }

    protected boolean addLevelElement( LevelElement levelElement ) {
        return this.levelElements.add( levelElement );
    }

    public void reset( Canvas gameCanvas ) {
        backgroundImage = new BackgroundImage();
        npcs = new ArrayList<>();
        players = new LinkedList<>();
        levelElements = new ArrayList<>();
        collectables = new ArrayList<>();
        try {
            createLevel( gameCanvas );
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
        addCollision();
        addCollectables();
    }

    public LinkedList<ControlableActor> getPlayers() {
        return players;
    }

    public ArrayList<LevelElement> getLevelElements() {
        return levelElements;
    }

    public ArrayList<Actor> getNpcs() {
        return npcs;
    }

    public ArrayList<WeakReference<Collectable>> getCollectables() {
        return this.collectables;
    }

    public void setBackgroundImage( BackgroundImage backgroundImage ) {
        this.backgroundImage = backgroundImage;
    }

    public void setBackgroundImage( String filepath ) {
        this.backgroundImage.setCurrentImage( filepath );
    }

    protected void setBackgroundImage( String filepath, int width, int height ) {
        this.backgroundImage.setCurrentImage( filepath );
        this.backgroundImage.setWidth( width );
        this.backgroundImage.setHeight( height );
    }
}
