package de.hsh.Julian;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class LeertastenklatscherMenu extends GameMenu {

    public LeertastenklatscherMenu( Observer observer ) {
        super( observer );
    }

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

}
