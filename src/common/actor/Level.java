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
            synchronized ( npcs ) {
                npcs.forEach( npc -> {
                    synchronized ( npc ) {
                        npc.draw( canvas );
                    }
                } );
            }
            synchronized ( levelElements ) {
                for ( LevelElement levelElement : levelElements ) {
                    synchronized ( levelElement ) {
                        levelElement.draw( canvas );
                    }
                }
            }
            synchronized ( collectables ) {
                for ( Collectable c : collectables ) {
                    if ( c != null ) {
                        synchronized ( c ) {
                            c.draw( canvas );
                        }
                    }
                }
            }
            synchronized ( players ) {
                for ( ControlableActor c : players ) {
                    synchronized ( c ) {
                        c.draw( canvas );
                    }
                }
            }
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
        for ( Collectable coll : getCollectables() ) {
            if ( coll != null ) {
                if ( coll.doesCollide( a ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean collected( Collectable collectable ) {
        collectable.deleteObservers();
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
        boolean result = false;
        synchronized ( collectables ) {
            result = collectables.remove( collectable );
        }
        return result;
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
