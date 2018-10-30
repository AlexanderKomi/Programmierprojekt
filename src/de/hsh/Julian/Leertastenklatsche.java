package de.hsh.Julian;
import java.util.Observer;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

public class Leertastenklatsche extends Game {

    protected Leertastenklatsche(Observer o, String name) {
        super(o, name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Pane initGameContentWindow() {
        Pane p = new Pane();
        return p;
    }

}
