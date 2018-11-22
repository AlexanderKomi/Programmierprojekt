package de.hsh.dennis.controller;

import common.util.Logger;
import de.hsh.dennis.DennisGame;
import de.hsh.dennis.DennisMenu;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;


/**
 * BigBrothers task is to work as the main controller for this game.
 * Every other sub controller is observed by this class.
 * If needed this class shell passes information up.
 */
public class BigBrother implements Observer {

    private final String breakMenu = "breakMenu";
    private final String level = "level";
    private final String levelMenu = "levelMenu";
    private final String mainMenu = "mainMenu";
    private final String tutorial = "tutorial";
    private final String exitToMain = "exitToMain";

    private Stage window;
    private DennisGame game;
    private DennisMenu gameMenu;
    private Parent root;            //root is set in the initializing phase and works as a reference for the stage so the system don't need to pass the stage down to this class.
    private Pane temp;

/*
    public BigBrother(DennisGame game) {

        this.game = game;
        gameMenu = new DennisMenu();
        gameMenu.addObserver(this);

        if (!initFXML()) { // In case FXML could not be loaded, a default pane is set
            game.setGameContentPane(new Pane());
        }
    }

    private boolean initFXML() {
        try {
            MainMenu_controller controller = new MainMenu_controller();
            controller.addObserver(this);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../view/mainMenu.fxml"));
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();

            this.gameMenu.setMenuPane((HBox) root);
            this.gameMenu.addObserver(this);

            game.setGameContentPane(this.gameMenu.getMenuPane());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadMenuFXML(String fxml) {

        //setting the Stage once !!!after!!! the fxml initializing phase is done!
        if(window == null){
            window = (Stage) (root.getScene().getWindow());
        }

        switch (fxml) {
            case mainMenu:
                try {
                    MainMenu_controller controller = new MainMenu_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../view/mainMenu.fxml"));
                    fxmlLoader.setController(controller);
                    temp = fxmlLoader.load();
                    window.setScene(new Scene(temp));
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case levelMenu:
                try {
                    LevelMenu_controller controller = new LevelMenu_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../view/levelMenu.fxml"));
                    fxmlLoader.setController(controller);
                    temp = fxmlLoader.load();
                    window.setScene(new Scene(temp));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case level:
                try {
                    Level_controller controller = new Level_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../view/level.fxml"));
                    fxmlLoader.setController(controller);
                    temp = fxmlLoader.load();
                    window.setScene(new Scene(temp));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case breakMenu:
                try {
                    BreakMenu_controller controller = new BreakMenu_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../view/breakMenu.fxml"));
                    fxmlLoader.setController(controller);
                    temp = fxmlLoader.load();
                    window.setScene(new Scene(temp));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case tutorial:
                try {
                    Tutorial_controller controller = new Tutorial_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("../view/tutorial.fxml"));
                    fxmlLoader.setController(controller);
                    temp = fxmlLoader.load();
                    window.setScene(new Scene(temp));
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case exitToMain:
                game.exitToMainGui();
                break;
            default:
                Logger.log("FXMLLoading Switchcase -> default");
                return false;
        }
        return false;
    }

    */

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
                //loadMenuFXML(mainMenu);
                break;
            default:
                Logger.log("handle_Tutorial_controller:  default erreicht");
        }
    }

    private void handle_LevelMenu_controller(String code) {

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
                //loadMenuFXML(mainMenu);
                break;

            default:
                Logger.log("handle_LevelMenu_controller: default erreicht");
        }
    }

    private void handle_BreakMenu_controller(String code) {
        switch (code) {
            case "b_replay":
                Logger.log("handle_BreakMenu_controller: b_replay erreicht");
                //starte das Level von neuem
                break;

            case "b_main_menu":
                Logger.log("handle_BreakMenu_controller: b_main_menu erreicht");
                //loadMenuFXML(mainMenu);
                break;

            case "b_continue":
                Logger.log("handle_BreakMenu_controller: b_continue erreicht");
                //setze das Level fort
                break;

            default:
                Logger.log("handle_BreakMenu_controller: default erreicht");
        }
    }

    private void handle_Level_controller(String code) {
        //TODO: implement later
    }

    private void handle_MainMenu_controller(String code) {


        switch (code) {
            case "b_play":
                Logger.log("handle_MainMenu_controller: b_play erreicht");
                //lade das Level-Menu
                //loadMenuFXML(levelMenu);
                break;

            case "b_tutorial":
                Logger.log("handle_MainMenu_controller: b_tutorial erreicht");
                //lade das Tutorial-Menu
                //loadMenuFXML(tutorial);
                break;

            case "b_exit":
                Logger.log("handle_MainMenu_controller: b_exit erreicht");
                //kehre zur Haupt-Gui zurück
                //loadMenuFXML(exitToMain);
                break;

            default:
                Logger.log("handle_MainMenu_controller: default erreicht");
        }

    }


    // notifyObserver der Unter-Controller hier auswerten!
    @Override
    public void update(Observable o, Object arg) {

        String code = (String) arg;

        if (o instanceof Level_controller) {
            handle_Level_controller(code);
        } else if (o instanceof BreakMenu_controller) {
            handle_BreakMenu_controller(code);
        } else if (o instanceof LevelMenu_controller) {
            handle_LevelMenu_controller(code);
        } else if (o instanceof MainMenu_controller) {
            handle_MainMenu_controller(code);
        } else if (o instanceof Tutorial_controller) {
            handle_Tutorial_controller(code);
        }
    }

}
