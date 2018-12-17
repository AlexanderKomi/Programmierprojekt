package de.hsh.dennis;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.dennis.controller.LevelMenu_controller;
import de.hsh.dennis.controller.MainMenu_controller;
import de.hsh.dennis.model.GameModel;
import de.hsh.dennis.model.KeyLayout;
import de.hsh.dennis.model.NpcLogic.Config;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;

import java.util.Observable;
import java.util.Observer;


public class DennisGameEntryPoint extends GameEntryPoint {

    private DennisFxmlChanger changer;
    private GameModel gm;
    private boolean rendering = false;
    private Config.Level.Difficulty lastGameMode;


    public DennisGameEntryPoint(Observer o) {
        super(o, WindowConfig.dennis_title);
        changer = new DennisFxmlChanger(this, "view/mainMenu.fxml", new MainMenu_controller());
        gm = new GameModel();
        gm.addObserver(this);
    }

    @Override
    public void render(int fps) {
        if(gm.fps == -1){gm.setFps(fps);}
        if (rendering) {
            Platform.runLater( () -> {
                gm.act();
            } );
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if(arg instanceof Config.Level.Difficulty){
            lastGameMode = (Config.Level.Difficulty) arg;
            gm.setDifficulty((Config.Level.Difficulty)arg);
        }

        if (o instanceof GameModel) {
            if (arg instanceof String) {
                switch ((String)arg) {
                    case UpdateCodes.Dennis.gameLost:
                        Logger.log("!!! YOU LOSE !!!");
                        rendering = false;
                        gm.reset();
                        changer.changeFxml(o, (String) arg);

                        break;
                    case UpdateCodes.Dennis.gameWon:
                        Logger.log("!!! YOU WIN !!!");
                        rendering = false;
                        gm.reset();
                        changer.changeFxml(o, (String) arg);



                        break;
                }
            }


        } else if (arg instanceof Canvas) {
            gm.setCanvas((Canvas) arg);


        } else if (arg instanceof KeyCode) {
            if (arg == KeyLayout.Control.ESC) {
                //changer.changeFxml(o, KeyLayout.Control.ESC.toString());      //no game pausing
            } else {
                gm.userInput((KeyCode) arg);
            }

        } else if (arg instanceof String) {
            if (arg.equals(UpdateCodes.Dennis.gameReady)) {
                rendering = true;
            }else if(arg.equals(UpdateCodes.Dennis.replay)){
                loadReplay();
            }else {
                changer.changeFxml(o, (String) arg);
            }
        }
    }

    private void loadReplay() {
        switch (lastGameMode){
            case EASY:
                changer.changeFxml(new LevelMenu_controller(), "b_easy");
                break;
            case MEDIUM:
                changer.changeFxml(new LevelMenu_controller(), "b_medium");
                break;
            case HARD:
                changer.changeFxml(new LevelMenu_controller(), "b_hard");
                break;
            case NIGHTMARE:
                changer.changeFxml(new LevelMenu_controller(), "b_nightmare");
                break;
        }
    }

}