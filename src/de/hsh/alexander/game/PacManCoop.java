package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

public class PacManCoop extends Game {

    private GameMenu gameMenu;

    protected PacManCoop( Observer o ) {
        super( o );
    }

    @Override
    public Pane initGameContentWindow() {
        BorderPane bp = new BorderPane();
        bp.setCenter( new Text( "Placeholder" ) );
        return bp;
    }
}
