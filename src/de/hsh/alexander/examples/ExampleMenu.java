package de.hsh.alexander.examples;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

public class ExampleMenu extends Menu {

    ExampleMenu( Observer sceneController, Game[] games ) {
        super( sceneController, games );
    }

    @Override
    protected Pane initScene() {
        BorderPane bp = new BorderPane();
        bp.setPrefSize( 400, 300 );
        Text text = new Text( "Menu" );
        bp.setTop( text );
        Button startGame = new Button( "Start Game" );
        startGame.setOnAction( this::notifyObservers );
        bp.setCenter( startGame );
        return bp;
    }

    @Override
    public void notifyObservers( Object arg ) {
        super.notifyObservers( arg );
    }

    @Override
    public String toString() {
        return "de.hsh.alexander.ExampleMenu.toString()";
    }
}
