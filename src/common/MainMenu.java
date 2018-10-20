package common;

import de.hsh.alexander.engine.game.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Observer;

public class MainMenu extends de.hsh.alexander.engine.game.MainMenu {

    private static final HashMap<String, Game> gameMap = new HashMap<>();

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

    public static Game selectGameFromArgument( String gameName ) throws IllegalArgumentException {
        if ( gameMap.containsKey( gameName ) ) {
            return gameMap.get( gameName );
        }
        else {
            throw new IllegalArgumentException( "Game is not registered in MainMenu." );
        }
    }

    @Override
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    void notifyObservers( ActionEvent actionEvent ) {
        if ( actionEvent.getSource() instanceof Button ) {
            Button button = (Button) actionEvent.getSource();
            this.setChanged();
            super.notifyObservers( button );
        }
        else {
            this.setChanged();
            super.notifyObservers( actionEvent );
        }
    }

    @Override
    public String toString() {
        return "common.MainMenu";
    }
}
