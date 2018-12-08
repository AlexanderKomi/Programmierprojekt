package de.hsh.alexander.src.level;

import common.actor.Actor;
import common.actor.Collectable;
import common.actor.Level;
import common.util.Logger;
import de.hsh.alexander.src.actor.player.PacMan;
import de.hsh.alexander.src.actor.player.PacMan1;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyEvent;

import java.util.Observable;

abstract public class PacManLevel extends Level {

    public static final String gameFinishedMessage = "PacMan : Game finished";

    @Override
    public void reset() {
        super.reset();
    }

    public SimpleIntegerProperty getPacMan1Property() {
        return getPlayers().stream()
                           .filter( player -> player instanceof PacMan )
                           .map( player -> (PacMan) player )
                           .filter( pacman -> pacman instanceof PacMan1 )
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
        Logger.log( "\tCollected : " + c + "\n" +
                    "\t\tBy : " + c.getCollector() );
        this.collected( c );
        Actor a = c.getCollector();
        if ( a instanceof PacMan ) {
            PacMan p = (PacMan) a;
            p.addPoint();
        }
        if ( this.getCollectables().isEmpty() ) {
            Logger.log( this.getClass() + ": Collect every collectable, so game finished screen should be shown" );
            this.setChanged();
            this.notifyObservers( gameFinishedMessage );
        }
        else {
            Logger.log( this.getClass() + ": Still " + this.getCollectables().size() + " to collect." );
        }
    }
}
