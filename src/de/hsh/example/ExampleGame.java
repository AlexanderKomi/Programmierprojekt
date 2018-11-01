package de.hsh.example;

import de.hsh.alexander.engine.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ExampleGame extends Game {

    public ExampleGame( Observer o ) {
        super( o, "Example" );
    }

    @Override
    public Pane initGameContentWindow( Observer observer ) {


        Parent root = null;
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/exampleRes/ExampleMainMenu.fxml"));
            root = fxmlloader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);


        return null;
    }

    @Override
    public void update( Observable o, Object arg ) {

    }
}