package common;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.util.Logger;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

public class MainMenu extends de.hsh.alexander.engine.game.MainMenu {

    MainMenu( Observer sceneController, Game[] games ) {
        super( sceneController, games );
    }

    @Override
    protected Pane initScene( Game[] games ) {
        Button pacmanButton = new Button( "Pacman" );
        pacmanButton.setOnAction( this::notifyObservers );

        HBox hbox = new HBox();
        hbox.getChildren().addAll( pacmanButton );

        BorderPane bp = new BorderPane();
        bp.setPrefSize( GameContainer.window_width, GameContainer.window_height );
        bp.setTop( new Text( "Spielesammlung" ) );
        bp.setCenter( hbox );
        return bp;
    }

    @Override
    public void notifyObservers() {
        String message = "MainMenu : Something changed -> Notifying observers";
        Logger.log( message );
        this.getSceneController().update( this, message );
        super.notifyObservers();
    }

    @Override
    public void notifyObservers( Object arg ) {
        Logger.log( arg.toString() );
        this.getSceneController().update( this, "Button clicked" );
        super.notifyObservers( arg );
    }
}
