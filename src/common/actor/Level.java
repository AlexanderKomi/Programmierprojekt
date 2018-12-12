package common.actor;

import common.util.Logger;
import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * A simple level structure.
 *
 * @author Alex
 */
abstract public class Level extends Observable implements Observer, ILevel {

    private BackgroundImage        backgroundImage = new BackgroundImage();
    private List<Actor>            npcs            = Collections.synchronizedList( new ArrayList<>() );
    private List<ControlableActor> players         = Collections.synchronizedList( new LinkedList<>() );
    private List<LevelElement>     levelElements   = Collections.synchronizedList( new ArrayList<>() );
    private List<Collectable>      collectables    = Collections.synchronizedList( new ArrayList<>() );

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
                        player -> player.addCollidingActor( collectable ) ) );
    }

    @Override
    public void render( Canvas canvas, int fps ) {
        try {
            synchronized ( backgroundImage ) {
                backgroundImage.draw( canvas );
            }
            this.draw( npcs, canvas );
            this.draw( levelElements, canvas );
            this.draw( collectables, canvas );
            this.draw( players, canvas );
        }
        catch ( ConcurrentModificationException cme ) {
            cme.printStackTrace();
        }
    }

    private synchronized <T extends Actor> void draw( final List<T> list, Canvas canvas ) {
        synchronized ( list ) {
            int size = list.size();
            for ( int i = 0 ; i < size ; i++ ) {
                final T t = list.get( i );
                if ( t != null ) {
                    t.draw( canvas );
                }
                size = list.size();
            }
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


    protected synchronized boolean collidesWithPlayer( Actor d ) {
        List<ControlableActor> l = Collections.synchronizedList( getPlayers() );
        synchronized ( l ) {
            for ( ControlableActor controlableActor : l ) {
                synchronized ( controlableActor ) {
                    if ( controlableActor.doesCollide( d ) ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    protected boolean collidesWithCollectable( Actor a ) {
        for ( Collectable coll : getCollectables() ) {
            if ( coll != null ) {
                if ( coll.doesCollide( a ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    protected synchronized boolean collected( Collectable collectable ) {
        collectable.deleteObservers();
        synchronized ( players ) {
            players.forEach(
                    p -> {
                        List<Actor> list = Collections.synchronizedList( p.getCollisionActors() );
                        synchronized ( list ) {
                            boolean b = list.remove( collectable );
                            if ( !b ) {
                                Logger.log( "------>" + this.getClass() + " FATAL ERROR : Can not delete: " + collectable );
                            }
                        }
                    } );
        }
        synchronized ( collectables ) {
            boolean result;
            result = collectables.remove( collectable );
            return result;
        }
    }

    protected void addCollectables( Collection<Collectable> c ) {
        for ( Collectable collectable : c ) {
            addCollectable( collectable );
        }
    }

    protected boolean addCollectable( Collectable c ) {
        c.addObserver( this );
        players.forEach( player -> player.addCollidingActor( c ) );
        return this.collectables.add( c );
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

    public List<ControlableActor> getPlayers() {
        return players;
    }

    public List<LevelElement> getLevelElements() {
        return levelElements;
    }

    public List<Actor> getNpcs() {
        return npcs;
    }

    public List<Collectable> getCollectables() {
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
