package common;

import common.config.Authors;
import common.config.WindowConfig;
import common.updates.UpdateCodes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Alexander Komischke
 */
public final class MainMenu extends common.engine.components.menu.MainMenu implements Initializable {

    @FXML
    public AnchorPane ap_desktop;
    public VBox vbox;
    @FXML
    public Button b_game_1;
    @FXML
    public Text txt_game_1 = new Text();
    @FXML
    public Button b_game_2;
    @FXML
    public Text txt_game_2 = new Text();
    @FXML
    public Button b_game_3;
    @FXML
    public Text txt_game_3 = new Text();
    @FXML
    public Button b_game_4;
    @FXML
    public Text txt_game_4 = new Text();
    @FXML
    public Button b_game_5;
    @FXML
    public Text txt_game_5 = new Text();
    @FXML
    public Button b_game_6;
    @FXML
    public Text txt_game_6 = new Text();
    @FXML
    public Button b_shutdown;
    @FXML
    public ComboBox<String> cb_credits = new ComboBox<>();

    @Override
    public String toString() {
        return "common.MainMenu";
    }


    public void initialize(URL location, ResourceBundle resources) {
        this.cb_credits.getItems().setAll(Authors.authorNamesWithTitles);

    }

    public void initGameNames() {
        try {
            bindGamesToButtons( new String[] {
                    WindowConfig.alexander_title,
                    WindowConfig.amir_title,
                    WindowConfig.dennis_title,
                    WindowConfig.daniel_title,
                    WindowConfig.kevin_title,
                    WindowConfig.julian_title
            } );

            HBox   shutdownBox    = (HBox) this.vbox.getChildren().get( 1 );
            Button shutdownButton = (Button) shutdownBox.getChildren().get( 0 );
            shutdownButton.setOnAction( shutdownEvent -> this.notifyObservers( UpdateCodes.MainMenu.shutdown ) );
        }
        catch ( NullPointerException npe ) {
            npe.printStackTrace();
        }

    }

    private void bindGamesToButtons( String[] gameNames ) {
        AnchorPane p = (AnchorPane) this.vbox.getChildren().get( 0 );
        GridPane   g = (GridPane) p.getChildren().get( 0 );

        for ( int i = 0 ; i <= 5 ; i++ ) {
            String gameName = gameNames[ i ];

            VBox tempBox = (VBox) g.getChildren().get( i );
            ((Text) (tempBox).getChildren().get( 1 )).setText( gameName ); // Set the Text to the game name
            Button b = (Button) tempBox.getChildren().get( 0 );
            b.setOnAction( buttonEvent -> this.notifyObservers( gameName ) );
        }
    }

}
