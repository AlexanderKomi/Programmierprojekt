package common;

import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.engine.game.Menu;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.example.ExampleGame;
import de.hsh.kevin.KevinGame;
import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class GameContainer extends FXGameContainer {

    @Override
    protected Stage configWindow( Stage primaryStage ) {
        primaryStage.setResizable( false );
        return primaryStage;
    }

    @Override
    public void update( Observable observable, Object arg ) {
        Updater.update( observable, arg, this );
    }

    @Override
    public Games createGames( Observer container ) {
        return new Games(
                new PacManCoop( container ),
                new AmirsGame( container ),
                new RAM( container ),
                new KevinGame( container ),
                new Leertastenklatsche( container ),
                new DennisGame( container ),
                new ExampleGame( container )
        );
    }

    @Override
    protected Menu configMainMenu( Observer container, Games games ) {
        return new common.MainMenu( container, games );
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
