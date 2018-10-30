package de.hsh.Julian;
import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class Leertastenklatsche extends Game {

    public Leertastenklatsche( Observer o ) {
        super( o, "Julian" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        Pane p = new Pane();
        return p;
    }
}
