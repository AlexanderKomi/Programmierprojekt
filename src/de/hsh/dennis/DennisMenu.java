package de.hsh.dennis;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class DennisMenu extends GameMenu {
    public DennisMenu( Observer observer ) {
        super( observer );
    }

    @Override
    protected Pane initMenuPane() {
        return null;
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
