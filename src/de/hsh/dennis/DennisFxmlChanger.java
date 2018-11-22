package de.hsh.dennis;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.Game;
import common.util.Logger;
import de.hsh.dennis.controller.LevelMenu_controller;
import de.hsh.dennis.controller.MainMenu_controller;
import de.hsh.dennis.controller.Tutorial_controller;

import java.util.Observable;

public class DennisFxmlChanger extends FxmlChanger {


    public DennisFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    public void handle_Tutorial_controller(String code) {
        switch (code) {
            case "b_left":
                Logger.log("handle_Tutorial_controller: b_left erreicht");

                break;

            case "b_right":
                Logger.log("handle_Tutorial_controller:  b_right erreicht");

                break;

            case "b_exit":
                Logger.log("handle_Tutorial_controller:  b_exit erreicht");
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;
            default:
                Logger.log("handle_Tutorial_controller:  default erreicht");
        }
    }

    public void handle_LevelMenu_controller(String code) {

        switch (code) {
            case "b_easy":
                Logger.log("handle_LevelMenu_controller: b_easy erreicht");
                //starte einfaches Level
                break;

            case "b_medium":
                Logger.log("handle_LevelMenu_controller: b_medium erreicht");
                //starte mittel schweres Level
                break;

            case "b_hard":
                Logger.log("handle_LevelMenu_controller: b_hard erreicht");
                //starte schweres Level
                break;

            case "b_nightmare":
                Logger.log("handle_LevelMenu_controller: b_nightmare erreicht");
                //starte viel zu schweres Level
                break;

            case "b_back":
                Logger.log("handle_LevelMenu_controller: b_back erreicht");
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;

            default:
                Logger.log("handle_LevelMenu_controller: default erreicht");
        }
    }

    public void handle_BreakMenu_controller(String code) {
        switch (code) {
            case "b_replay":
                Logger.log("handle_BreakMenu_controller: b_replay erreicht");
                //starte das Level von neuem
                break;

            case "b_main_menu":
                Logger.log("handle_BreakMenu_controller: b_main_menu erreicht");
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;

            case "b_continue":
                Logger.log("handle_BreakMenu_controller: b_continue erreicht");
                //setze das Level fort
                break;

            default:
                Logger.log("handle_BreakMenu_controller: default erreicht");
        }
    }

    public void handle_Level_controller(String code) {
        //TODO: implement later
    }

    public void handle_MainMenu_controller(String code) {


        switch (code) {
            case "b_play":
                Logger.log("handle_MainMenu_controller: b_play erreicht");
                //lade das Level-Menu
                changeScene("view/levelMenu.fxml", new LevelMenu_controller());
                break;

            case "b_tutorial":
                Logger.log("handle_MainMenu_controller: b_tutorial erreicht");
                //lade das Tutorial-Menu
                changeScene("view/tutorial.fxml", new Tutorial_controller());
                break;

            case "b_exit":
                Logger.log("handle_MainMenu_controller: b_exit erreicht");
                //kehre zur Haupt-Gui zurück
                ((Game) getFxModul()).exitToMainGUI();
                break;

            default:
                Logger.log("handle_MainMenu_controller: default erreicht");
        }

    }

    @Override
    public void changeFxml(Observable o, String msg) {


    }


}
