package common;

import common.updates.UpdateCodes;
import common.updates.Updater;
import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.game.PacManController;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.controller.TIController;
import de.hsh.kevin.controller.TIGameController;
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
        Games games = new Games(
                new PacManController( container ),
                new AmirsGame( container ),
                new RAM( container ),
                new TIController( container ),
                new Leertastenklatsche( container ),
                new DennisGame(container)
        );

        return games;
    }

    @Override
    protected common.MainMenu configMainMenu(Observer container, ArrayList<String> games) {
        common.MainMenu mainMenu = new MainMenu();
        try {
            URL  location = getClass().getResource( "gui/P3_Gui.fxml" );
            VBox vbox     = FXMLLoader.load( location );
            mainMenu.vbox = vbox;
            mainMenu.setPane( mainMenu.vbox );
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
        if ( this.getGames() != null ) {
            ((Leertastenklatsche) this.getGames().get( "Leertastenklatsche" )).render();
            ((PacManController) this.getGames().get( UpdateCodes.PacMan.gameName )).render();
            ((TIController) this.getGames().get( UpdateCodes.TunnelInvader.gameName )).render();
        }
    }

    @Override
    public String toString() {
        return "GameContainer(" +
               "superclass:" + super.toString() +
               ")";
    }
}
