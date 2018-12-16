package de.hsh.alexander.src.level.level1;

import common.actor.Collectable;
import common.actor.CollisionCheck;
import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.alexander.src.actor.ResourcePaths;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.level_elements.Fan;
import de.hsh.alexander.src.actor.level_elements.SMD;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import de.hsh.alexander.src.level.PacManLevel;
import javafx.scene.canvas.Canvas;

public final class Level1 extends PacManLevel {

    private static final String backgroundImage = ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

    public Level1( Canvas gameCanvas ) {super( gameCanvas );}

    @Override
    public void createLevel( Canvas gameCanvas ) {
        setBackgroundImage( backgroundImage, 950, 800 );
        addPlayers();
        addLevelElements( gameCanvas );
        createCollectables( gameCanvas );
        Logger.log( this.getClass() + ": created Level" );
    }

    private void createCollectables( Canvas gameCanvas ) {
        for ( int y = 0 ; y < WindowConfig.window_height ; y += 50 ) {
            for ( int x = 0 ; x < WindowConfig.window_width ; x += 50 ) {
                final DataCoin  d  = new DataCoin( x, y );
                final boolean[] xy = CollisionCheck.isInBounds( d, gameCanvas );
                if ( xy[ 0 ] && xy[ 1 ] ) {
                    if ( !collidesWithPlayer( d ) ) {
                        if ( !collidesWithLevelElement( d ) ) {
                            addCollectable( d );
                        }
                    }
                }
            }
        }
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
        addLevelElement( new Fan( 500, 50 ) );

        int smd_offset = 200;

        for ( int y = 0 ; y < WindowConfig.window_height ; y = y + smd_offset )
            for ( int x = 0 ; x < WindowConfig.window_width ; x = x + smd_offset ) {
                SMD smd = new SMD( x, y );
                synchronized ( smd ) {
                    boolean[] xy = CollisionCheck.isInBounds( smd, gameCanvas );
                    if ( xy[ 0 ] && xy[ 1 ] ) {
                        addLevelElement( smd );
                    }
                }
            }
    }

    private void addPlayers() {
        addPlayer( new PacMan1( 250, 250 ) );
        addPlayer( new PacMan2( 150, 150 ) );
    }

}
