package common.actor;

import common.util.Logger;
import javafx.scene.canvas.Canvas;

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
        synchronized ( this.levelElements ) {
            this.levelElements.forEach(
                    levelElement -> players.forEach(
                            pacMan -> pacMan.addCollidingActor( levelElement ) ) );
        }
    }

    private void addCollectables() {
        synchronized ( this.collectables ) {
            this.collectables.forEach(
                    collectable -> players.forEach(
                            player -> player.addCollidingActor( collectable ) ) );
        }
    }

    protected boolean addLevelElement( Canvas gameCanvas, final LevelElement levelElement ) {
        boolean[] xy = CollisionCheck.isInBounds( levelElement, gameCanvas );
        if ( xy[ 0 ] && xy[ 1 ] ) {
            return addLevelElement( levelElement );
        }
        return false;
    }

    @Override
    public synchronized void deleteObservers() {
        this.backgroundImage.deleteObservers();
        for ( Actor npc : this.npcs ) {
            npc.deleteObservers();
        }
        for ( ControlableActor player : this.players ) {
            player.deleteObservers();
        }
        for ( LevelElement levelElement : this.levelElements ) {
            levelElement.deleteObservers();
        }
        for ( Collectable collectable : this.collectables ) {
            collectable.deleteObservers();
        }
        super.deleteObservers();
    }

    @Override
    public void render( Canvas canvas, int fps ) {
        try {
            this.backgroundImage.draw( canvas );
            this.draw( npcs, canvas );
            this.draw( levelElements, canvas );
            this.draw( collectables, canvas );
            this.draw( players, canvas );
        }
        catch ( ConcurrentModificationException cme ) {
            cme.printStackTrace();
        }
    }

    private <T extends Actor> void draw( final List<T> list, Canvas canvas ) {
        int size = list.size();
        for ( int i = 0 ; i < size ; i++ ) {
            final T t = list.get( i );
            if ( t != null ) {
                t.draw( canvas );
            }
            size = list.size();
        }
    }

    protected boolean collidesWithLevelElement( final Actor a ) {
        for ( LevelElement levelElement : getLevelElements() ) {
            if ( levelElement.doesCollide( a ) ) {
                return true;
            }
        }
        return false;
    }


    protected boolean collidesWithPlayer( final Actor d ) {
        for ( ControlableActor controlableActor : getPlayers() ) {
            if ( controlableActor.doesCollide( d ) ) {
                return true;
            }
        }
        return false;
    }

    protected boolean collidesWithCollectable( final Actor a ) {
        for ( Collectable coll : getCollectables() ) {
            if ( coll != null ) {
                if ( coll.doesCollide( a ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean collected( final Collectable collectable ) {
        collectable.deleteObservers();
        final List<ControlableActor> players = Collections.synchronizedList( this.players );
        synchronized ( players ) {
            int size = players.size();
            for ( int i = 0 ; i < size ; i++ ) {
                final ControlableActor c = players.get( i );
                final List<Actor>      l = Collections.synchronizedList( c.getCollisionActors() );
                synchronized ( l ) {
                    boolean b = l.remove( collectable );
                    if ( !b ) {
                        Logger.log( "------>" + this.getClass() + " FATAL ERROR : Can not delete: " + collectable );
                    }
                }
                c.setCollisionActors( l );

                size = players.size();
            }
        }
        final boolean result = getCollectables().remove( collectable );
        this.getCollectables().retainAll( getCollectables() );
        return result;
    }

    protected boolean addCollectable( final Collectable c ) {
        c.addObserver( this );
        final List<ControlableActor> players = Collections.synchronizedList( this.players );
        synchronized ( players ) {
            int size = players.size();
            for ( int i = 0 ; i < size ; i++ ) {
                players.get( i ).addCollidingActor( c );
                size = players.size();
            }
            this.players = players;
        }
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
        createLevel( gameCanvas );
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

    protected void setBackgroundImage( final String filepath, final int width, final int height ) {
        this.backgroundImage.setCurrentImage( filepath );
        this.backgroundImage.setWidth( width );
        this.backgroundImage.setHeight( height );
    }
}
