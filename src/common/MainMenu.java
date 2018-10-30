package common;

import common.config.WindowConfig;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.Menu;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
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

        BorderPane bp = new BorderPane();
        bp.setPrefSize( WindowConfig.window_width, WindowConfig.window_height );
        bp.setTop( new Text( "Spielesammlung" ) );
        bp.setCenter( createButtons() );
        return bp;
    }

    private HBox createButtons() {
        Button pacmanButton = new Button( "Pacman" );
        pacmanButton.setOnAction( this::notifyObservers );

        Button amirButton = new Button( "Amir" );
        amirButton.setOnAction( this::notifyObservers );

        Button kevinButton = new Button( "Kevin" );
        kevinButton.setOnAction( this::notifyObservers );

        Button danielButton = new Button( "Daniel" );
        danielButton.setOnAction( this::notifyObservers );

        Button julianButton = new Button( "Julian" );
        julianButton.setOnAction( this::notifyObservers );

        Button dennisButton = new Button( "Dennis" );
        dennisButton.setOnAction( this::notifyObservers );

        HBox hbox = new HBox();
        hbox.setSpacing( 10 );
        hbox.setPadding( new Insets( 10, 10, 10, 10 ) );
        hbox.getChildren().addAll(
                pacmanButton,
                amirButton,
                kevinButton,
                danielButton,
                julianButton,
                dennisButton
                                 );

        return hbox;
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
