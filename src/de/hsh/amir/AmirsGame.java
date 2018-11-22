package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
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
        if (!loadMenuFXML()) { // In case FXML could not be loaded, a default pane is set
            this.setGameContentPane(new Pane());
        }
    }

    private boolean loadMenuFXML() {
        try {
            HBox node = FXMLLoader.load(getClass().getResource("view/AmirsMenu.fxml"));
            this.gameMenu = new AmirsMenu();
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
