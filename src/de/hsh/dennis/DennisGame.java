package de.hsh.dennis;

import common.config.WindowConfig;
import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import de.hsh.dennis.controller.BigBrother;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

//extends Game == Observer!!!
public class DennisGame extends Game implements Initializable {

    private BigBrother bb;
    private final String gameName = "DDos Defender";

    public DennisGame(Observer o){
        super(o, WindowConfig.dennis_title);
        bb = new BigBrother(this);

    }

    public void exitToMainGui(){
        setChanged();
        notifyObservers(UpdateCodes.Dennis.exitToMainGui);
    }

    public String getName(){
        return gameName;
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
