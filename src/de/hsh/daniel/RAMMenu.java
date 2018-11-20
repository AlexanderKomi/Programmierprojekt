package de.hsh.daniel;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

public class RAMMenu extends GameMenu {

    public RAMMenu(){

    }

            @FXML
            public FlowPane pane;
            public static Observer observer;
            @FXML
            public Button b_back;
            @FXML
            public Button   b_play;

            public RAMMenu(Observer o) { observer = o; }


    @Override
    protected Pane initMenuPane() {
        return new Pane();
    }

    public void initialize( URL location, ResourceBundle resources ) {
        this.addObserver(observer);
    }

    public void backButtonPressed(ActionEvent actionEvent) {
        Logger.log("RAM Menu: Back button pressed");
        this.setChanged();
        this.notifyObservers(UpdateCodes.RAM.mainMenu);
    }

    public void playButtonPressed(ActionEvent actionEvent) {
                Memory.main();
                Logger.log("RAM Menu: Play button pressed");
                this.setChanged();
                this.notifyObservers(UpdateCodes.RAM.startGame);
    }

    public void levelMenuButton(ActionEvent actionEvent) {
                Logger.log("RAM Menu: Level menu Button clicked");
                this.setChanged();
                this.notifyObservers(UpdateCodes.RAM.fieldSize);
    }
}
