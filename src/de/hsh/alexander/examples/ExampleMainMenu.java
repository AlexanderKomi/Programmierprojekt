package de.hsh.alexander.examples;

import de.hsh.alexander.engine.game.MainMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

public class ExampleMainMenu extends MainMenu {

    public ExampleMainMenu( Observer sceneController ) {
        super( sceneController );
    }

    @Override
    protected Pane initScene() {
        BorderPane bp = new BorderPane();
        bp.setPrefSize( 400, 300 );
        Text text = new Text( "MainMenu" );
        bp.setTop( text );
        Button startGame = new Button( "Start Game" );
        startGame.setOnAction( e -> this.notifyObservers() );
        bp.setCenter( startGame );
        return bp;
    }

    @Override
    public void notifyObservers() {
        this.getSceneController().update( this, "Button clicked" );
        super.notifyObservers();
    }
}
