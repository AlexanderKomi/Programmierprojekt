package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class PacManCoop extends Game {

    private PacManMenu gameMenu;

    public PacManCoop( Observer o ) {
        super( o, "Pacman Coop" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {
        initRessources();
        gameMenu = new PacManMenu( observer );
        return gameMenu.getMenuPane();
    }

    private void initRessources() {

    }

}
