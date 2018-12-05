package de.hsh.alexander.src.level;

import common.actor.Collectable;
import common.actor.Level;
import common.util.Logger;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.Observable;

abstract public class PacManLevel extends Level {

    public static final String gameFinishedMessage = "PacMan : Game finished";

    PacManLevel() throws FileNotFoundException {
    }

    @Override
    public void createLevel() throws FileNotFoundException {

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
                    Logger.log( "Collected : " + c + "\n" +
                                "By : " + c.getCollector() );
                    this.collected( c );
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
        }
        else {
            Logger.log( this.getClass() + ": unknown observable=" + o + " , arg=" + arg );
        }
    }
}
