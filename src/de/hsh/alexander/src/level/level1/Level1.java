package de.hsh.alexander.src.level.level1;

import common.actor.Collectable;
import common.actor.CollisionCheck;
import common.actor.LevelElement;
import common.config.WindowConfig;
import de.hsh.alexander.src.actor.level_elements.Condensator;
import de.hsh.alexander.src.actor.level_elements.Fan;
import de.hsh.alexander.src.actor.level_elements.SMD;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import de.hsh.alexander.src.level.PacManLevel;
import javafx.scene.canvas.Canvas;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

public final class Level1 extends PacManLevel {


    public Level1( Canvas gameCanvas ) {super( gameCanvas );}

    @Override
    public void createLevel( Canvas gameCanvas ) {
        setBackgroundImage( microChip, 950, 800 );
        addPlayers();
        addLevelElements( gameCanvas );
        createDataCoins( gameCanvas );
    }

    @Override
    protected boolean addCollectable( final Collectable c ) {
        if ( !collidesWithCollectable( c ) ) {
            return super.addCollectable( c );
        }
        return false;
    }

    private void addLevelElements( Canvas gameCanvas ) {

        addLevelElement( new Fan( 200, 50 ) );
        addLevelElement( new Fan( 300, 50 ) );

        fillPins( gameCanvas );
        addSMDs( gameCanvas );
    }

    private void addSMDs( Canvas gameCanvas ) {
        final int smd_offset = 200;

        for ( int y = 0 ; y < WindowConfig.window_height ; y += smd_offset ) {
            for ( int x = 0 ; x < WindowConfig.window_width ; x += smd_offset ) {
                SMD smd = new SMD( x, y );
                addLevelElement( gameCanvas, smd );
            }
        }
    }

    private void fillPins( Canvas gameCanvas ) {

        addLevelElement( new Condensator( 200, 600, 1 ) );

        for ( int i = 350 ; i < 500 ; i += 40 ) {
            addLevelElement( new Condensator( 700, i, 0 ) );
        }


        final int  end       = 580;
        final byte increment = 19;

        for ( int y = 285 ; y < end ; y += increment ) {
            addLevelElement( gameCanvas, new Condensator( 525, y ) );
            addLevelElement( gameCanvas, new Condensator( 525 + increment, y ) );
        }
        addLevelElement( gameCanvas, new Condensator( 525 + increment, end + increment ) );
    }

    private boolean addLevelElement( Canvas gameCanvas, final LevelElement levelElement ) {
        boolean[] xy = CollisionCheck.isInBounds( levelElement, gameCanvas );
        if ( xy[ 0 ] && xy[ 1 ] ) {
            return super.addLevelElement( levelElement );
        }
        return false;
    }

    private void addPlayers() {
        addPlayer( new PacMan1( 250, 250 ) );
        addPlayer( new PacMan2( 150, 150 ) );
    }

}