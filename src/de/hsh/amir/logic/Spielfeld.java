package de.hsh.amir.logic;


import common.actor.Level;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;
import java.util.Observable;

public class Spielfeld extends Level{

    public Spielfeld( Canvas gameCanvas ) {
        super( gameCanvas );
    }

    @Override
    public void createLevel() throws FileNotFoundException {

    }

    @Override
    public void keyboardInput(KeyEvent keyEvent) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
