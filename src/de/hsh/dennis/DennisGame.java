package de.hsh.dennis;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.util.Logger;
import de.hsh.dennis.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

//extends Game == Observer!!!
public class DennisGame extends Game implements Initializable {

    private final String breakMenu = "breakMenu";
    private final String level = "level";
    private final String levelMenu = "levelMenu";
    private final String mainMenu = "mainMenu";
    private final String tutorial = "tutorial";


    private DennisMenu gameMenu;
    private Parent root;


    public DennisGame(Observer o) {
        super(o, "DDos Defender");

        gameMenu = new DennisMenu();
        gameMenu.addObserver(this);

        if (!loadMenuFXML()) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane(new Pane());
        }


    }

    private boolean loadMenuFXML() {
        try {
            MainMenu_controller controller = new MainMenu_controller();
            controller.addObserver(this);
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("view/mainMenu.fxml"));
            fxmlLoader.setController(controller);
            root = fxmlLoader.load();

            this.gameMenu.setMenuPane((HBox) root);
            this.gameMenu.addObserver(this);
            this.setGameContentPane(this.gameMenu.getMenuPane());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean loadMenuFXML(String fxml) {

        //TODO: switch fxml
        switch (fxml) {
            //TODO: currently working
            case levelMenu:
                try {
                    LevelMenu_controller controller = new LevelMenu_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("view/levelMenu.fxml"));
                    fxmlLoader.setController(controller);
                    Pane temp = fxmlLoader.load();

                    gameMenu.setMenuPane(temp);
                    this.setGameContentPane(temp);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case mainMenu:
                try {
                    MainMenu_controller controller = new MainMenu_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("view/mainMenu.fxml"));
                    fxmlLoader.setController(controller);


                    this.gameMenu.setMenuPane((HBox) root);
                    this.gameMenu.addObserver(this);
                    this.setGameContentPane(this.gameMenu.getMenuPane());
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case level:
                try {
                    Level_controller controller = new Level_controller();
                    controller.addObserver(this);
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("view/level.fxml"));
                    fxmlLoader.setController(controller);
                    root = fxmlLoader.load();

                    this.gameMenu.setMenuPane((HBox) root);
                    this.gameMenu.addObserver(this);
                    this.setGameContentPane(this.gameMenu.getMenuPane());
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
                    fxmlLoader.setLocation(getClass().getResource("view/breakMenu.fxml"));
                    fxmlLoader.setController(controller);
                    root = fxmlLoader.load();

                    this.gameMenu.setMenuPane((HBox) root);
                    this.gameMenu.addObserver(this);
                    this.setGameContentPane(this.gameMenu.getMenuPane());
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
                    fxmlLoader.setLocation(getClass().getResource("view/tutorial.fxml"));
                    fxmlLoader.setController(controller);
                    root = fxmlLoader.load();

                    this.gameMenu.setMenuPane((HBox) root);
                    this.gameMenu.addObserver(this);
                    this.setGameContentPane(this.gameMenu.getMenuPane());
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                Logger.log("FXMLLoading Switchcase -> default");
                return false;
        }
        return false;
    }


    // notifyObserver der Controller hier auswerten!
    @Override
    public void update(Observable o, Object arg) {
        Logger.log("arg: " + arg);


        String code = (String) arg;

        if (o instanceof Level_controller) {

        } else if (o instanceof BreakMenu_controller) {

        } else if (o instanceof LevelMenu_controller) {

        } else if (o instanceof MainMenu_controller) {

            handleMainMenu_controller(code);

        } else if (o instanceof Tutorial_controller) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void handleMainMenu_controller(String code) {

        switch (code) {
            case "play":
                Logger.log("handle play erreicht");
                loadMenuFXML(levelMenu);                //lade neues FXML
                this.setChanged();
                this.notifyObservers("play");
                break;

            case tutorial:
                Logger.log("handle tutorial erreicht");
                loadMenuFXML(tutorial);
                this.setChanged();
                this.notifyObservers("tutorial");
                break;

            case "exitToGameCollection":
                Logger.log("handle exitToGameCollection erreicht");

                //TODO implement

                this.setChanged();
                this.notifyObservers("exitToGameCollection");
                break;

            default:
                this.setChanged();
                this.notifyObservers("error");


        }

    }
}
