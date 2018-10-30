package common;

import common.config.WindowConfig;
import de.hsh.alexander.engine.game.Games;
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

    MainMenu( Observer sceneController, Games games ) {
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
        HBox hbox = new HBox();
        hbox.setSpacing( 10 );
        hbox.setPadding( new Insets( 10, 10, 10, 10 ) );

        gameNames.forEach( name -> {
            Button selectGameButton = new Button( name );
            selectGameButton.setOnAction( this::notifyObservers );
            hbox.getChildren().add( selectGameButton );
        } );

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
