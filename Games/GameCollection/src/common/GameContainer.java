package common;

import common.engine.FXGameContainer;
import common.engine.components.game.GameEntryPoints;
import common.updates.Updater;
import de.hsh.Julian.LKEntryPoint;
import de.hsh.alexander.src.PacManController;
import de.hsh.amir.AmirEntryPoint;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGameEntryPoint;
import de.hsh.dennis.model.AudioPlayer;
import de.hsh.kevin.controller.TIController;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    private static final String mainMenuFXMLPath = "gui/P3_Gui.fxml";

    @Override
    public void update( Observable observable, Object arg ) {
        Updater.update( observable, arg, this );
    }


    @Override
    public GameEntryPoints createGames( Observer container ) {
        // TODO : Make gameEntryPoints only create on request from main menu
        return new GameEntryPoints(
                new PacManController( container ),
                new AmirEntryPoint( container ),
                new RAM( container ),
                new TIController( container ),
                new DennisGameEntryPoint(container),
                new LKEntryPoint( container )
        );
    }

    @Override
    protected common.MainMenu configMainMenu(ArrayList<String> games) {
        common.MainMenu mainMenu = new MainMenu();
        try {
            URL location = getClass().getResource( mainMenuFXMLPath );
            mainMenu.vbox = FXMLLoader.load( location );
            mainMenu.setMenuPane(mainMenu.vbox);
        }
        catch ( IOException e ) {
            mainMenu = new common.MainMenu();
            e.printStackTrace();
        }

        mainMenu.addObserver(this);
        return mainMenu;
    }


    @Override
    public void startContainer( final String[] args ) {
        launch( args );
    }


    @Override
    protected void beforeStoppingContainer() {
        AudioPlayer.MusicPlayer.shutdown();
    }


    @Override
    public String toString() {
        return "GameContainer(" +
               "superclass:" + super.toString() +
               ")";
    }
}
