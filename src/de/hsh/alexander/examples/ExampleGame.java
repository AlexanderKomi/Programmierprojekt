package de.hsh.alexander.examples;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Observer;

public class ExampleGame extends Game {

    ExampleGame( Observer o ) {
        super( o );
    }

    @Override
    public Pane initGameContentWindow() {
        return new FlowPane();
    }

    @Override
    protected Node createGameContent() {
        Canvas c = new Canvas( 400, 300 );

        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill( Color.PAPAYAWHIP );
        gc.fillRect( 75, 75, 100, 100 );
        c.setOnMouseClicked( this::notifyObservers );

        return c;
    }
}
