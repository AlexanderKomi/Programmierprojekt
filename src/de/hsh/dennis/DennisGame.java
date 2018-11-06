package de.hsh.dennis;

import de.hsh.alexander.engine.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class DennisGame extends Game implements Initializable {

    private DennisMenu gameMenu;

    public DennisGame( Observer o ) {
        super(o, "DDos Defender");
        if (!loadMenuFXML()) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane(new Pane());
        }
    }

    private boolean loadMenuFXML() {
        try {
            HBox node = FXMLLoader.load(getClass().getResource("view/mainMenu.fxml"));
            this.gameMenu = new DennisMenu();
            this.gameMenu.setMenuPane(node);
            this.gameMenu.addObserver(this);
            this.setGameContentPane(this.gameMenu.getMenuPane());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg ) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
