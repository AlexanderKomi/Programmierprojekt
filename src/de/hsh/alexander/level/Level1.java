package de.hsh.alexander.level;

import de.hsh.alexander.actor.DataCoin;
import de.hsh.alexander.actor.ResourcePaths;
import de.hsh.alexander.actor.level_elements.SMD;
import de.hsh.alexander.actor.level_elements.Wall;
import de.hsh.alexander.actor.player.PacMan;

import java.io.FileNotFoundException;

public class Level1 extends de.hsh.alexander.level.PacManLevel {

    private static final String backgroundImage = ResourcePaths.Actor.LevelElements.Backgrounds.leeresFenster;

    @Override
    public void createLevel() throws FileNotFoundException {
        setBackgroundImage( backgroundImage );
        addPlayer( PacMan.initPacMan1() );
        addPlayer( PacMan.initPacMan2() );

        addCollectable( new DataCoin( 50, 50 ) );

        addLevelElement( Wall.initTestWall() );
        addLevelElement( new SMD( 700, 500 ) );
    }
}
