package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import javafx.scene.layout.Pane;

import java.util.Observer;

public class PacManCoop extends Game {

    private GameMenu gameMenu;

    public PacManCoop( Observer o ) {
        super( o, "Pacman Coop" );
    }

    @Override
    public Pane initGameContentWindow() {
        initRessources();
        gameMenu = new GameMenu();
        return gameMenu.getMenuPane();
    }

    private void initRessources() {

    }

}
