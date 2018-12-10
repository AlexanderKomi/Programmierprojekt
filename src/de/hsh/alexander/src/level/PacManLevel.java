package de.hsh.alexander.src.level;

import common.actor.Actor;
import common.actor.Collectable;
import common.actor.Level;
import common.util.Logger;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.player.PacMan;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.Observable;

abstract public class PacManLevel extends Level {

    public static final String gameFinishedMessage = "PacMan : Game finished";

    PacManLevel( Canvas gameCanvas ) {
        super( gameCanvas );
    }

    @Override
    public void reset( Canvas gameCanvas ) {
        super.reset( gameCanvas );
        gameCanvas.setOnMouseClicked( clickEvent -> {
            double x = clickEvent.getX();
            double y = clickEvent.getY();
            Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
            this.addCollectable(
                    new DataCoin( x, y )
                               );
        } );
    }

    public SimpleIntegerProperty getPacMan1Property() {
        return getPlayers().stream()
                           .filter( player -> player instanceof PacMan )
                           .map( player -> (PacMan) player )
                           .filter( pacman -> pacman instanceof PacMan1 )
                           .map( PacMan::getPointProperty ).findFirst().get();
    }

    public SimpleIntegerProperty getPacMan2Property() {
        return getPlayers().stream()
                           .filter( player -> player instanceof PacMan )
                           .map( player -> (PacMan) player )
                           .filter( pacman -> pacman instanceof PacMan2 )
                           .map( PacMan::getPointProperty ).findFirst().get();
    }

    @Override
    public void keyboardInput( KeyEvent keyEvent ) {
        getPlayers().forEach( pacMan -> pacMan.move( keyEvent ) );
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof Collectable ) {
            Collectable c = (Collectable) o;
            if ( arg instanceof String ) {
                if ( arg.equals( Collectable.collected ) ) {
                    coinCollected( c );
                }
            }
        }
        else {
            Logger.log( this.getClass() + ": unknown observable=" + o + " , arg=" + arg );
        }
    }

    private void coinCollected( Collectable c ) {
        Actor a = c.getCollector();
        if ( a instanceof PacMan ) {
            PacMan p = (PacMan) a;
            p.addPoint();
        }
        this.collected( c );
        if ( this.getCollectables().isEmpty() ) {
            this.setChanged();
            this.notifyObservers( gameFinishedMessage );
        }
        else {
            Logger.log( this.getClass() + ": Still " + this.getCollectables().size() + " to collect." );
        }
    }
}
