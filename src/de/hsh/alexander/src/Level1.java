package de.hsh.alexander.src;

import common.actor.Level;
import de.hsh.alexander.src.actor.ActorCreator;
import de.hsh.alexander.src.actor.PacMan;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;

public class Level1 extends Level {

    @Override
    public void createLevel() throws FileNotFoundException {
        players.add( PacMan.initPacMan1() );
        players.add( PacMan.initPacMan2() );

        levelElements.add( ActorCreator.initTestWall() );
        levelElements.forEach( levelElement -> players.forEach( pacMan -> pacMan.addCollidingActor( levelElement ) ) );
    }

    @Override
    public void keyboardInput( KeyEvent keyEvent ) {
        players.forEach( pacMan -> pacMan.move( keyEvent ) );
    }

    @Override
    public void render( Canvas canvas, int fps ) {
        levelElements.forEach( levelElement -> levelElement.draw( canvas ) );
        players.forEach( pacMan -> pacMan.draw( canvas ) );
    }
}
