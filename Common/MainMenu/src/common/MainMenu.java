package common;

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
    public AnchorPane       ap_desktop;
    public VBox             vbox;
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


    public void initialize(URL location, ResourceBundle resources) {
        this.cb_credits.getItems().setAll(
                WindowConfig.alexander_title + " : " + "Alexander Komischke",
                WindowConfig.dennis_title + " : " + "Dennis Sellemann",
                WindowConfig.julian_title + " : " + "Julian Sender",
                WindowConfig.daniel_title + " : " + "Daniel Diele",
                WindowConfig.kevin_title + " : " + "Kevin Jeske",
                WindowConfig.amir_title + " : " + "Amir-Hossein Ebrahimzadeh");
    }

    public void initGameNames() {
        try {
            bindGamesToButtons(new String[]{
                    WindowConfig.alexander_title,
                    WindowConfig.amir_title,
                    WindowConfig.dennis_title,
                    WindowConfig.daniel_title,
                    WindowConfig.kevin_title,
                    WindowConfig.julian_title
            });

            HBox   shutdownBox    = (HBox) this.vbox.getChildren().get(1);
            Button shutdownButton = (Button) shutdownBox.getChildren().get(0);
            shutdownButton.setOnAction(shutdownEvent -> this.notifyObservers(UpdateCodes.MainMenu.shutdown));
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    private void bindGamesToButtons(final String[] gameNames) {
        final AnchorPane anchorPane = (AnchorPane) this.vbox.getChildren().get(0);
        final GridPane   gridPane   = (GridPane) anchorPane.getChildren().get(0);

        for (int i = 0; i <= 5; i++) {
            final String gameName = gameNames[i];
            final VBox   tempBox  = (VBox) gridPane.getChildren().get(i);

            Text text = ((Text) (tempBox).getChildren().get(1));
            text.setText(gameName); // Set the Text to the game name

            Button button = (Button) tempBox.getChildren().get(0);
            button.setOnAction(buttonEvent -> this.notifyObservers(gameName));
        }
    }

    public String title() {
        return WindowConfig.mainGui_title;
    }

}
