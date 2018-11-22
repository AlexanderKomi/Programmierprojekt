package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirsGame extends Game implements Initializable {

    private AmirsMenu gameMenu;

    public AmirsGame( Observer o ) {
        super(o, WindowConfig.amir_title);

    }


    @Override
    public void update(Observable o, Object arg ) {

    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

    }

    @Override
    public void update( KeyEventManager keyEventManager, Object arg ) {

    }

    @Override
    public void update( MouseEventManager mouseEventManagerManager, Object arg ) {

    }

    @Override
    public void render() {

    }
}
