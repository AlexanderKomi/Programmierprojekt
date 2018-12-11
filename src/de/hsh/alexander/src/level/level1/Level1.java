package de.hsh.alexander.src.level.level1;

import common.actor.Collectable;
import common.actor.CollisionCheck;
import common.config.WindowConfig;
import de.hsh.alexander.src.actor.ResourcePaths;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.level_elements.Fan;
import de.hsh.alexander.src.actor.level_elements.SMD;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import de.hsh.alexander.src.level.PacManLevel;
import javafx.scene.canvas.Canvas;

public class Level1 extends PacManLevel {

    private static final String backgroundImage = ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

    public Level1( Canvas gameCanvas ) {super( gameCanvas );}

    @Override
    public void createLevel( Canvas gameCanvas ) {
        setBackgroundImage( backgroundImage, 950, 800 );
        addPlayers();
        addLevelElements( gameCanvas );
        createCollectables( gameCanvas );
    }

    private void createCollectables( Canvas gameCanvas ) {
        for ( int y = 0 ; y < WindowConfig.window_height ; y += 250 ) {
            for ( int x = 0 ; x < WindowConfig.window_width ; x += 250 ) {
                DataCoin  d  = new DataCoin( x, y );
                boolean[] xy = CollisionCheck.isInBounds( d, gameCanvas );
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
    protected boolean addCollectable( Collectable c ) {
        if ( !collidesWithCollectable( c ) ) {
            return super.addCollectable( c );
        }
        return false;
    }

    private void addLevelElements( Canvas gameCanvas ) {
        Fan f = new Fan( 200, 50 );
        addLevelElement( f );

        for ( int y = 0 ; y < WindowConfig.window_height ; y = y + 200 ) {
            for ( int x = 0 ; x < WindowConfig.window_width ; x = x + 200 ) {
                SMD       smd = new SMD( x, y );
                boolean[] xy  = CollisionCheck.isInBounds( smd, gameCanvas );
                if ( xy[ 0 ] && xy[ 1 ] ) {
                    if ( !collidesWithPlayer( smd ) ) {
                        if ( !collidesWithLevelElement( smd ) ) {
                            addLevelElement( smd );
                        }
                    }
                }
            }
        }
    }

    private void addPlayers() {
        addPlayer( new PacMan1( 250, 250 ) );
        addPlayer( new PacMan2( 150, 150 ) );
    }

}
