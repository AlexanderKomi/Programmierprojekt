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

    private BackgroundImage           backgroundImage = new BackgroundImage();
    private HashSet<Actor>            npcs            = new HashSet<>();
    private HashSet<ControlableActor> players         = new HashSet<>();
    private ArrayList<Actor>          levelElements   = new ArrayList<>();
    private ArrayList<Collectable>    collectables    = new ArrayList<>();


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
            backgroundImage.draw( canvas );
            npcs.forEach( npc -> npc.draw( canvas ) );
            levelElements.forEach( levelElement -> levelElement.draw( canvas ) );
            for ( Collectable c : collectables ) {
                c.draw( canvas );
            }
            for ( ControlableActor c : players ) {
                c.draw( canvas );
            }
            //collectables.forEach( collectable -> collectable.draw( canvas ) );
            //players.forEach( pacMan -> pacMan.draw( canvas ) );

        }
        catch ( ConcurrentModificationException cme ) {
            Logger.log( "---------> " + this.getClass() + ": Exception : " + cme.getMessage() );
        }
    }

    protected boolean collected( Collectable collectable ) {
        players.forEach( p -> p.getCollisionActors().remove( collectable ) );
        return collectables.remove( collectable );
    }

    protected boolean addCollectable( Collectable c ) {
        c.addObserver( this );
        players.forEach( player -> player.addCollidingActor( c ) );
        return this.collectables.add( c );
    }

    protected boolean addPlayer( ControlableActor player ) {
        return this.players.add( player );
    }

    protected boolean addLevelElement( Actor levelElement ) {
        return this.levelElements.add( levelElement );
    }

    public void reset( Canvas gameCanvas ) {
        try {
            createLevel();
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
        addCollision();
        addCollectables();
    }

    public HashSet<ControlableActor> getPlayers() {
        return players;
    }

    public ArrayList<Actor> getLevelElements() {
        return levelElements;
    }

    public HashSet<Actor> getNpcs() {
        return npcs;
    }

    public ArrayList<Collectable> getCollectables() {
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
