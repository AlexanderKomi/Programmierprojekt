package common;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.Observer;

/**
 * @author Alexander Komischke
 */
public class MainMenu extends Menu {


    MainMenu( Observer sceneController, Game[] games ) {
        super( sceneController, games );
    }

    @Override
    protected Pane initScene() {
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
    public void notifyObservers( Object arg ) {
        this.setChanged();
        super.notifyObservers( arg );
    }

    private void notifyObservers( ActionEvent actionEvent ) {
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
        return "common.Menu";
    }
}
