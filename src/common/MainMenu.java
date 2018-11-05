package common;

import common.config.Authors;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Alexander Komischke
 */
public class MainMenu extends de.hsh.alexander.engine.game.MainMenu {

    private GameContainer     gameContainer;
    private ArrayList<String> gameNames = new ArrayList<>();

    @FXML
    public AnchorPane ap_desktop;
    public VBox       vbox;

    @FXML
    public Button           b_game_1;
    @FXML
    public Text             txt_game_1 = new Text();
    @FXML
    public Button           b_game_2;
    @FXML
    public Text             txt_game_2 = new Text();
    @FXML
    public Button           b_game_3;
    @FXML
    public Text             txt_game_3 = new Text();
    @FXML
    public Button           b_game_4;
    @FXML
    public Text             txt_game_4 = new Text();
    @FXML
    public Button           b_game_5;
    @FXML
    public Text             txt_game_5 = new Text();
    @FXML
    public Button           b_game_6;
    @FXML
    public Text             txt_game_6 = new Text();
    @FXML
    public Button           b_shutdown;
    @FXML
    public ComboBox<String> cb_credits = new ComboBox<>();

    @Override
    public String toString() {
        return "common.MainMenu";
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.cb_credits.getItems().setAll( Authors.authorNames );
        super.initialize( location, resources );
    }

    public void initGameNames() {
            try {
                // TODO : Implement correct mapping...
                printGamesNames( gameNames );

                AnchorPane      p    = (AnchorPane) this.vbox.getChildren().get( 0 );
                for ( int i = 0 ; i < this.gameNames.size() ; i++ ) {
                    try {
                        String gameName = this.gameNames.get( i );
                        VBox   box      = (VBox) p.getChildren().get( i ); // Get a VBox from FXML
                        ((Text) (box).getChildren().get( 1 )) // Get the Text in the VBox
                                                              .setText( gameName ); // Set the Text to the game name
                        Button b = (Button) box.getChildren().get( 0 );

                        b.setOnAction( buttonEvent -> this.notifyObservers( gameName ) );
                    }
                    catch ( IndexOutOfBoundsException ioobe ) {
                        ioobe.printStackTrace();
                    }
                }
                HBox   shutdownBox    = (HBox) this.vbox.getChildren().get( 1 ); // HBox with buttons
                Button shutdownButton = (Button) shutdownBox.getChildren().get( 0 );
                shutdownButton.setOnAction( shutdownEvent -> this.notifyObservers( UpdateCodes.MainMenu.shutdown ) );
            }
            catch ( NullPointerException npe ) {
                npe.printStackTrace();
            }

    }

    private void printGamesNames( ArrayList<String> gameNames ) {

    }


    public void setGameContainer( GameContainer gameContainer ) {
        this.gameContainer = gameContainer;
    }

    public void setGameNames( ArrayList<String> gameNames ) {
        this.gameNames = gameNames;
    }

}
