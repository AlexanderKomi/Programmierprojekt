package de.hsh.example;

import de.hsh.alexander.engine.FXGameContainer;
import de.hsh.alexander.engine.game.Games;
import de.hsh.alexander.engine.game.MainMenu;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ExampleGameContainer extends FXGameContainer {
    @Override
    public Games createGames( Observer container ) {

        return null;
    }

    @Override
    protected MainMenu configMainMenu( Observer container, ArrayList<String> games ) {
        ExampleGameMenu exampleGameMenu;
        try {
            exampleGameMenu = FXMLLoader.load( ExampleGameMenu.class.getResource( "GameMenu.fxml" ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
            exampleGameMenu = new ExampleGameMenu();
        }
        return exampleGameMenu;
    }

    @Override
    public void update( Observable observable, Object arg ) {

    }

    @Override
    public void startContainer( String[] args ) {

    }

    @Override
    public void startContainer() {

    }

    @Override
    public void render() {

    }
}
