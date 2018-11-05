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
import javafx.scene.layout.VBox;

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
                new PacManCoop( container ),
                new AmirsGame( container ),
                new RAM( container ),
                new KevinGame( container ),
                new Leertastenklatsche( container ),
                new DennisGame( container )
        );
    }

    @Override
    protected MainMenu configMainMenu( Observer container, ArrayList<String> games ) {
        common.MainMenu mainMenu = new MainMenu();
        try {
            URL        location = getClass().getResource( "gui/P3_Gui.fxml" );
            FXMLLoader fl       = new FXMLLoader();
            VBox       v        = FXMLLoader.load( location );
            fl.setController( mainMenu );
            mainMenu.vbox = v;
            System.out.println( fl.getController().toString() );
        }
        catch ( IOException e ) {
            mainMenu = new common.MainMenu();
            e.printStackTrace();
        }

        mainMenu.setGameNames( games );
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
