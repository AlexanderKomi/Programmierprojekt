package de.hsh.alexander.examples;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Observer;

/**
 * @author Alexander Komischke
 */
public class ExampleGame extends Game {

    /**
     * @param o
     *         observer of the current game. In general its a game container.
     *
     * @author Alexander Komischke
     */
    ExampleGame( Observer o ) {
        super( o, "ExampleGame" );
    }

    /**
     * @return The pane getting drawn, when game should be shown.
     *
     * @author Alexander Komischke
     */
    @Override
    public Pane initGameContentWindow() {
        Canvas c = new Canvas( 400, 300 );

        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill( Color.PAPAYAWHIP );
        gc.fillRect( 75, 75, 100, 100 );
        c.setOnMouseClicked( this::notifyObservers );
        FlowPane f = new FlowPane( c );
        return f;
    }
}
