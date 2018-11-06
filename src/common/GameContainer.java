package common;

import common.updates.Updater;
import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.KevinGame;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    @Override
    public void update( Observable observable, Object arg ) {
        Updater.update( observable, arg, this );
    }

    @Override
    public Games createGames( Observer container ) {
        // TODO : Make games only create on request from main menu
        return new Games(
                new PacManCoop(container),
                new AmirsGame( container ),
                new RAM( container ),
                new KevinGame( container ),
                new Leertastenklatsche( container ),
                new DennisGame( container )
        );
    }

    @Override
    protected common.MainMenu configMainMenu(Observer container, ArrayList<String> games) {
        common.MainMenu mainMenu = null;
        try {
            URL location = getClass().getResource("gui/P3_Gui.fxml");
            mainMenu = FXMLLoader.load(location);
        }
        catch ( IOException e ) {
            mainMenu = new common.MainMenu();
            e.printStackTrace();
        }

        mainMenu.setGameNames( games );
        mainMenu.addObserver(container);
        return mainMenu;
    }


    @Override
    public void startContainer( String[] args ) {
        launch( args );
    }

    @Override
    public void startContainer() {
        launch();
    }

    @Override
    public void render() {
        //Logger.log("Rendering");
    }

    @Override
    public String toString() {
        return "GameContainer(" +
               "superclass:" + super.toString() +
               ")";
    }
}
