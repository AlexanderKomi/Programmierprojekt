package common.actor;

import javafx.scene.canvas.Canvas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

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


    public Level() {
        reset();
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
        backgroundImage.draw( canvas );
        npcs.forEach( npc -> npc.draw( canvas ) );
        levelElements.forEach( levelElement -> levelElement.draw( canvas ) );
        collectables.forEach( collectable -> collectable.draw( canvas ) );
        players.forEach( pacMan -> pacMan.draw( canvas ) );
    }

    protected boolean collected( Collectable collectable ) {
        players.forEach( p -> p.getCollisionActors().remove( collectable ) );
        return collectables.remove( collectable );
    }

    protected boolean addCollectable( Collectable c ) {
        c.addObserver( this );
        return this.collectables.add( c );
    }

    protected boolean addPlayer( ControlableActor player ) {
        return this.players.add( player );
    }

    protected boolean addLevelElement( Actor levelElement ) {
        return this.levelElements.add( levelElement );
    }

    public void reset() {
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
}
