package de.hsh.daniel;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import static de.hsh.alexander.util.Path.getExecutionLocation;


public class RAM extends Game {

    public RAM( Observer o ) {
        super( o, "!R.A.M" );
        this.setGameContentPane( this.gameWindow(o) );
    }

    @Override
    public void update( Observable o, Object arg ) {

    }

    public Pane gameWindow(Observer observer) {
        Pane root = new Pane();

        Canvas canvas = new Canvas();
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Arial", FontWeight.BOLD, 24);

        return root;

    }
}
