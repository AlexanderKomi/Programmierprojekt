package de.hsh.dennis;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import de.hsh.dennis.controller.MainMenu_controller;

import java.util.Observable;
import java.util.Observer;


public class DennisGame extends GameEntryPoint {

    private DennisFxmlChanger changer;


    public DennisGame(Observer o){
        super(o, WindowConfig.dennis_title);
        changer = new DennisFxmlChanger(this, "view/mainMenu.fxml", new MainMenu_controller());
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


    @Override
    public void update(Observable o, Object arg) {

        changer.changeFxml(o, (String) arg);
    }

}
