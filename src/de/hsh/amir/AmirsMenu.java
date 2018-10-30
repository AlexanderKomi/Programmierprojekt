package de.hsh.amir;

import de.hsh.alexander.engine.game.GameMenu;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class AmirsMenu extends GameMenu {

    public AmirsMenu( Observer observer ) {
        super( observer );
    }

    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }
}
