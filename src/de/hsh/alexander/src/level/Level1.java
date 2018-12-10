package de.hsh.alexander.src.level;

import common.actor.CollisionCheck;
import common.util.Logger;
import de.hsh.alexander.src.actor.ResourcePaths;
import de.hsh.alexander.src.actor.collectables.DataCoin;
import de.hsh.alexander.src.actor.level_elements.SMD;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;
import javafx.scene.canvas.Canvas;

public class Level1 extends PacManLevel {

    private static final String backgroundImage = ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

    public Level1( Canvas gameCanvas ) {super( gameCanvas );}

    @Override
    public void createLevel( Canvas gameCanvas ) {
        setBackgroundImage( backgroundImage, 950, 800 );
        addPlayers();
        addLevelElements();
        createCollectables( gameCanvas );
    }

    private void createCollectables( Canvas gameCanvas ) {
        for ( int y = 0 ; y < 2000 ; y += 50 ) {
            for ( int x = 0 ; x < 2000 ; x += 50 ) {
                DataCoin  d  = new DataCoin( x, y );
                boolean[] xy = CollisionCheck.isInBounds( d, gameCanvas, x, y );
                if ( xy[ 0 ] && xy[ 1 ] ) {
                    addCollectable( d );
                }
            }
        }
        //addCollectable( new DataCoin( 350, 50 ) );
    }

    private void addLevelElements() {
        for ( int x = 700 ; x > 200 ; x = x - 200 ) {
            addLevelElement( new SMD( x, 500 ) );
        }
    }

    private void addPlayers() {
        Logger.log( "Add Players" );
        addPlayer( new PacMan1( 250, 250 ) );
        addPlayer( new PacMan2( 150, 150 ) );
    }

}
