package de.hsh.alexander.game;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

public class GameMenu extends MainMenu {

    public GameMenu( Observer sceneController, Game[] games ) {
        super( sceneController, games );
    }

    @Override
    protected Pane initScene( Game[] games ) {
        return new BorderPane( new Text( " PacMan Menu " ) );
    }


}
