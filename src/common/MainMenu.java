package common;

import de.hsh.alexander.engine.game.Game;
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
        BorderPane bp = new BorderPane();
        bp.setPrefSize( GameContainer.window_width, GameContainer.window_height );
        bp.setTop( new Text( "Spielesammlung" ) );
        HBox hbox = new HBox();

        return bp;
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers( "Spiele Sammlung event" );
    }
}
