package de.hsh.alexander.src.level;

import de.hsh.alexander.src.actor.DataCoin;
import de.hsh.alexander.src.actor.ResourcePaths;
import de.hsh.alexander.src.actor.level_elements.SMD;
import de.hsh.alexander.src.actor.player.PacMan1;
import de.hsh.alexander.src.actor.player.PacMan2;

import java.io.FileNotFoundException;

public class Level1 extends PacManLevel {

    private static final String backgroundImage = ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

    @Override
    public void createLevel() throws FileNotFoundException {
        setBackgroundImage( backgroundImage, 950, 800 );
        addPlayers();
        addLevelElements();
        addCollectable( new DataCoin( 50, 50 ) );
        addCollectable( new DataCoin( 350, 50 ) );
    }

    private void addLevelElements() {
        for ( int x = 700 ; x > 200 ; x = x - 200 ) {
            addLevelElement( new SMD( x, 500 ) );
        }
    }

    private void addPlayers() {
        addPlayer( new PacMan1( 250, 250 ) );
        addPlayer( new PacMan2( 150, 150 ) );
    }

}
