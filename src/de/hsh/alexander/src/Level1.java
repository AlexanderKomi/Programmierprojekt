package de.hsh.alexander.src;

import common.actor.Collectable;
import common.actor.Level;
import common.util.Logger;
import de.hsh.alexander.src.actor.ActorCreator;
import de.hsh.alexander.src.actor.DataCoin;
import de.hsh.alexander.src.actor.PacMan;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.Observable;

public class Level1 extends Level {

    public Level1() throws FileNotFoundException {
    }

    @Override
    public void createLevel() throws FileNotFoundException {
        addPlayer( PacMan.initPacMan1() );
        addPlayer( PacMan.initPacMan2() );

        addCollectable( new DataCoin( 50, 50 ) );

        addLevelElement( ActorCreator.initTestWall() );

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
                    Logger.log( "Collected : " + c );
                    boolean success = this.collected( c );
                    if ( !success ) {
                        Logger.log( this.getClass() + ": FATAL ERROR : CAN NOT REMOVE : " + c );
                    }
                }
            }
        }
        else {
            Logger.log( this.getClass() + ": unknown observable=" + o + " , arg=" + arg );
        }
    }
}
