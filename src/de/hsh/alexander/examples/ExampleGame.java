package de.hsh.alexander.examples;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ExampleGame extends Game {

    @Override
    public GameMenu initMenu() {
        return new ExampleGameMenu();
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

        return c;
    }
}
