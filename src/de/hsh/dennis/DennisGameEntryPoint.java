package de.hsh.dennis;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.dennis.controller.MainMenu_controller;
import de.hsh.dennis.model.GameModel;
import de.hsh.dennis.model.KeyLayout;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

import java.util.Observable;
import java.util.Observer;


public class DennisGameEntryPoint extends GameEntryPoint {

    private DennisFxmlChanger changer;
    private GameModel gm;
    private boolean rendering = false;


    public DennisGameEntryPoint(Observer o) {
        super(o, WindowConfig.dennis_title);
        changer = new DennisFxmlChanger(this, "view/mainMenu.fxml", new MainMenu_controller());
        gm = new GameModel();
    }

    @Override
    public void render() {
        if (rendering) {
            gm.render();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Canvas) {
            gm.setCanvas((Canvas) arg);
        } else if (arg instanceof KeyCode) {
            if (arg == KeyLayout.Control.ESC) {
                changer.changeFxml(o, KeyLayout.Control.ESC.toString());
            } else {
                gm.userInput((KeyCode) arg);
            }
        } else if (arg instanceof String) {
            if (((String) arg).equals(UpdateCodes.Dennis.gameReady)) {
                rendering = true;
            } else {
                changer.changeFxml(o, (String) arg);
            }
        }
    }

}