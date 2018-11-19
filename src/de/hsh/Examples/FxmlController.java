package de.hsh.Examples;

import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public abstract class FxmlController extends Observable implements Observer {

    private Game game;                      //Deine Game Klasse
    private GameMenu gameMenu;              //Deine GameMenu Klasse

    private Stage window;                   //Die Stage, für dynamisches Übergeben.
    private Parent root;                    //root dient als Referenz auf das erste Object des ersten FXMLs und wird einmalig bei der Innitialisierung gesetzt.

    //Paths to .fxmls
    public String fxmlInitLocation;

    /**
     * Konstruktor der aus dem Game.class aufgeruft werden soll und das Game Object überreicht.
     *
     * @param game Das Game Object aus dem der Konsturktor aufgerufen wird.
     */
    public FxmlController(Game game, GameMenu menu) {

        this.game = game;                           //Referenz auf deine Spiele Klasse
        game.addObserver(this);
        this.gameMenu = menu;           //erstmaliges erstellen deiner Menu Klasse
        gameMenu.addObserver(this);             //Diese Klasse dem GameMenue als Observable eintragen.

        if (!init()) {                              //Initialisiere das erste FXML
            game.setGameContentPane(new Pane());    // Falls das FXML nicht gelladen wird, ein leeres neues Pane zurück geben.
        }

    }

    /**
     * Erste Lademethode: Dient als Init-Methode.
     * Einmaliger Aufruf am Anfang!!!
     * Setzt die Referenz auf die Stage und das root Object.
     */
    protected boolean init() {
        FXExampleController controller = new FXExampleController();
        try {
            //muss alles einmalig gesetzt werden nach der Init-Phase.
            root = loadFxml(fxmlInitLocation, controller);
            gameMenu.setMenuPane((HBox) root);
            game.setGameContentPane(gameMenu.getMenuPane());
            return true;
        } catch (IOException e) {
            // FXML konnte nicht geladen werden. Fehlerbehandlung notwendig.
            e.printStackTrace();
        }
        return false;
    }

    public Parent loadFxml(String fxmlLocation, Observable controller) throws IOException {
        controller.addObserver(this);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fxmlLocation));
        fxmlLoader.setController(controller);
        return fxmlLoader.load();                       //setzt root einmalig. Warum auch immer funtioniert temp als Object hier nicht!

    }

    public void changeScene(String fxmlPath, Observable o) throws IOException {
        //setze die Stage einmallig !!!nach!!! dem die FXML Init-Phase abgeschlossen ist. Darf nicht in init() oder im Konstruktor stehen!!!
        if (window == null) {
            window = (Stage) (root.getScene().getWindow());
        }
        window.setScene(new Scene(loadFxml(fxmlPath, o)));
    }

    public Game getGame() {
        return this.game;
    }
}
