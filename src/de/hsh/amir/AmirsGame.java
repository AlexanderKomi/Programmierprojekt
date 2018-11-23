package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import de.hsh.amir.controller.MainMenu_controller;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirsGame extends Game {

    private AmirFxmlChanger changer;

    public AmirsGame( Observer o ) {
        super(o, WindowConfig.amir_title);
        changer = new AmirFxmlChanger(this, "view/AmirsMenu.fxml", new MainMenu_controller());
    }


    @Override
    public void update(Observable o, Object arg ) {
        changer.changeFxml(o, (String) arg);
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
