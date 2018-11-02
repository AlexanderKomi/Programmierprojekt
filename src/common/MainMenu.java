package common;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
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
    public Text             txt_game_1;
    @FXML
    public Button           b_game_2;
    @FXML
    public Text             txt_game_2;
    @FXML
    public Button           b_game_3;
    @FXML
    public Text             txt_game_3;
    @FXML
    public Button           b_game_4;
    @FXML
    public Text             txt_game_4;
    @FXML
    public Button           b_game_5;
    @FXML
    public Text             txt_game_5;
    @FXML
    public Button           b_game_6;
    @FXML
    public Text             txt_game_6;
    @FXML
    public Button           b_shutdown;
    @FXML
    public ComboBox<String> cb_credits;

    @Override
    public String toString() {
        return "common.MainMenu";
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        this.cb_credits.getItems().setAll(
                "Alexander Komischke",
                "Dennis Sellemann",
                "Julian Sender",
                "Daniel Diele",
                "Kevin",
                "Amir-Hossein Ebrahimzadeh" );
        addButtonListener();
    }

    private void addButtonListener() {

    }


    public void setGameContainer( GameContainer gameContainer ) {
        this.gameContainer = gameContainer;
    }

    public void setGameNames( ArrayList<String> gameNames ) {
        this.gameNames = gameNames;
    }

}
