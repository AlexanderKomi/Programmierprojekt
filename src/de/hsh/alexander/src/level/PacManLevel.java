package de.hsh.alexander.src.level;

import common.actor.*;
import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.player.PacMan;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.Observable;

abstract public class PacManLevel extends Level {

    public static final String gameFinishedMessage = "PacMan : Game finished";

    protected PacManLevel( Canvas gameCanvas ) {
        super( gameCanvas );
    }

    @Override
    public void reset( Canvas gameCanvas ) {
        super.reset( gameCanvas );
        gameCanvas.setOnMouseClicked( this::onMouseClick );
        Logger.log( this.getClass() + ": Resettet Level" );
        isGameFinished();
    }

    private void onMouseClick( final MouseEvent clickEvent ) {
        double x = clickEvent.getX();
        double y = clickEvent.getY();
        Logger.log( this.getClass() + ": Clicked at : (" + x + ", " + y + ")" );
        this.addCollectable(
                new DataCoin( x, y )
                           );

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
            final Collectable c = (Collectable) o;
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

    private void coinCollected( final Collectable c ) {
        if ( c instanceof DataCoin ) {
            final Actor a = c.getCollector();
            if ( a instanceof PacMan ) {
                final PacMan p = (PacMan) a;
                p.addPoint();
            }
        }
        this.collected( c );

        if ( !isGameFinished() ) {
            Logger.log( this.getClass() + ": Still " + this.getCollectables().size() + " to collect." );
        }
    }

    protected boolean isGameFinished() {
        if ( this.getCollectables().size() == 0 ) {
            this.setChanged();
            this.notifyObservers( gameFinishedMessage );
            return true;
        }
        return false;
    }

    protected void createDataCoins( Canvas gameCanvas ) {
        for ( int y = 0 ; y < WindowConfig.window_height ; y += 50 ) {
            for ( int x = 0 ; x < WindowConfig.window_width ; x += 50 ) {
                final DataCoin  d  = new DataCoin( x, y );
                final boolean[] xy = CollisionCheck.isInBounds( d, gameCanvas );
                if ( xy[ 0 ] && xy[ 1 ] ) {
                    boolean created = addCollectable( d );
                }
            }
        }
    }

    @Override
    protected boolean addCollectable( Collectable c ) {
        if ( !collidesWithPlayer( c ) ) {
            if ( !collidesWithLevelElement( c ) ) {
                if ( !collidesWithCollectable( c ) ) {
                    return super.addCollectable( c );
                }
            }
        }
        return false;
    }

    @Override
    protected boolean addLevelElement( LevelElement levelElement ) {
        if ( !collidesWithPlayer( levelElement ) ) {
            if ( !collidesWithLevelElement( levelElement ) ) {
                return super.addLevelElement( levelElement );
            }
        }
        return false;
    }
}
