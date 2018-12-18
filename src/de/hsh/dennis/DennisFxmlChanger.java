package de.hsh.dennis;

import common.config.WindowConfig;
import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.dennis.controller.*;
import de.hsh.dennis.model.GameModel;
import de.hsh.dennis.model.KeyLayout;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public class DennisFxmlChanger extends FxmlChanger {

    Stage breakStage;
    private static final int BREAKMENU_HEIGHT = WindowConfig.window_height;
    private static final int BREAKMENU_WIDTH = WindowConfig.window_width;

    public DennisFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    private void handle_Tutorial_controller(String code) {
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

    private void handle_LevelMenu_controller(String code) {
        Level_controller levelController = new Level_controller();
        switch (code) {
            case "b_easy":
                Logger.log("handle_LevelMenu_controller: b_easy erreicht");
                changeScene("view/level.fxml", levelController);
                levelController.passCanvas();
                setChanged();
                notifyObservers(SkinConfig.Level.Difficulty.EASY);
                break;

            case "b_medium":
                Logger.log("handle_LevelMenu_controller: b_medium erreicht");
                changeScene("view/level.fxml", levelController);
                levelController.passCanvas();
                setChanged();
                notifyObservers(SkinConfig.Level.Difficulty.MEDIUM);
                break;

            case "b_hard":
                Logger.log("handle_LevelMenu_controller: b_hard erreicht");
                changeScene("view/level.fxml", levelController);
                levelController.passCanvas();
                setChanged();
                notifyObservers(SkinConfig.Level.Difficulty.HARD);
                break;

            case "b_nightmare":
                Logger.log("handle_LevelMenu_controller: b_nightmare erreicht");
                changeScene("view/level.fxml", levelController);
                levelController.passCanvas();
                setChanged();
                notifyObservers(SkinConfig.Level.Difficulty.NIGHTMARE);
                break;

            case "b_back":
                Logger.log("handle_LevelMenu_controller: b_back erreicht");
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;

            default:
                Logger.log("handle_LevelMenu_controller: default erreicht");
        }
    }

    private void handle_BreakMenu_controller(String code) {
        switch (code) {
            case "b_replay":
                Logger.log("handle_BreakMenu_controller: b_replay erreicht");
                breakStage.close();
                setChanged();
                notifyObservers(UpdateCodes.Dennis.replay);
                break;

            case "b_main_menu":
                Logger.log("handle_BreakMenu_controller: b_main_menu erreicht");
                breakStage.close();
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;

            case "b_continue":
                Logger.log("handle_BreakMenu_controller: b_continue erreicht");
                breakStage.close();
                //TODO: implement continuing current level
                break;

            default:
                Logger.log("handle_BreakMenu_controller: default erreicht");
        }
    }

    private void handle_Level_controller(String code) {
        if (code.equals(KeyLayout.Control.ESC.toString())) {
            openBreakMenu();
        }
    }

    private void handle_MainMenu_controller(String code) {


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
                ((GameEntryPoint) getFxModul()).exitToMainGUI();
                break;

            default:
                Logger.log("handle_MainMenu_controller: default erreicht");
        }

    }

    @Override
    public void changeFxml(Observable o, String msg) {

        if (o instanceof Level_controller) {
            handle_Level_controller(msg);
        } else if (o instanceof BreakMenu_controller) {
            handle_BreakMenu_controller(msg);
        } else if (o instanceof LevelMenu_controller) {
            handle_LevelMenu_controller(msg);
        } else if (o instanceof MainMenu_controller) {
            handle_MainMenu_controller(msg);
        } else if (o instanceof Tutorial_controller) {
            handle_Tutorial_controller(msg);
        } else if (o instanceof GameModel) {
            handle_GameModel((GameModel) o, msg);
        } else if (o instanceof EndScreen_controller) {
            handle_EndScreen_controller(msg);
        }
    }

    private void handle_EndScreen_controller(String msg) {
        switch (msg) {
            case "b_replay":
                setChanged();
                notifyObservers(UpdateCodes.Dennis.replay);
                break;
            case "b_main_menu":
                changeScene("view/mainMenu.fxml", new MainMenu_controller());
                break;
        }
    }

    private void handle_GameModel(GameModel gm, String msg) {
        String endTitle = "Score";
        switch (msg) {
            case UpdateCodes.Dennis.gameLost:
                endTitle = "YOU LOSE!";
                break;
            case UpdateCodes.Dennis.gameWon:
                endTitle = "YOU Win!";
                break;
        }
        EndScreen_controller c = new EndScreen_controller();
        changeScene("view/endScreen.fxml", c);
        c.setScore(gm.getScore());
        c.changeToEndScreen(endTitle);
    }

    private void openBreakMenu() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/breakMenu.fxml"));
            BreakMenu_controller controller = new BreakMenu_controller();
            controller.addObserver(getFxModul());
            fxmlLoader.setController(controller);
            Parent parent = fxmlLoader.load();
            breakStage = new Stage();
            breakStage.setTitle(" - PAUSING - ");
            Scene scene = new Scene(parent, BREAKMENU_WIDTH, BREAKMENU_HEIGHT);
            breakStage.initModality(Modality.APPLICATION_MODAL);
            breakStage.setScene(scene);
            breakStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
