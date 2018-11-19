package de.hsh.Examples;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.util.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


/**
 * Ewt überarbeiten
 *
 */
public class ExampleFXMLLoading implements Observer {

    private Game     game;                  //Deine Game Klasse
    private GameMenu gameMenu;              //Deine GameMenu Klasse

    private Stage window;                   //Die Stage, für dynamisches Übergeben.
    private Parent root;                    //root dient als Referenz auf das erste Object des ersten FXMLs und wird einmalig bei der Innitialisierung gesetzt.
    private Parent temp;                    //gleiche Aufgabe wie 'root', allerdings zum mehrfach überschreiben benutzt.


    //Harte Strings zum switchen der FXML-Fälle
    private final String erstesFxml = "erstesFxml";
    private final String zweitesFxml = "zweitesFxml";

    //Paths to .fxmls
    private final String fxmlInitLocation = "Pfad/zum/Init/Fxml.fxml";
    private final String fxml_1_path = ""; //ambesten == fxmlInitLocation
    private final String fxml_2_path = "";

    /**
     *  Konstruktor der aus dem Game.class aufgeruft werden soll und das Game Object überreicht.
     *
     * @param game Das Game Object aus dem der Konsturktor aufgerufen wird.
     * */
    public ExampleFXMLLoading(ExampleGame game) {

        this.game = game;                           //Referenz auf deine Spiele Klasse
        gameMenu = new ExampleGameMenu();           //erstmaliges erstellen deiner Menu Klasse
        gameMenu.addObserver(this);             //Diese Klasse dem GameMenue als Observable eintragen.

        if (!init()) {                              //Initialisiere das erste FXML
            game.setGameContentPane(new Pane());    // Falls das FXML nicht gelladen wird, ein leeres neues Pane zurück geben.
        }

    }

    /**
     *  Erste Lademethode: Dient als Init-Methode.
     *  Einmaliger Aufruf am Anfang!!!
     *  Setzt die Referenz auf die Stage und das root Object.
     * */
    private boolean init() {
        FXExampleController     controller   = new FXExampleController();
        try {
            //muss alles einmalig gesetzt werden nach der Init-Phase.
            root = loadFxml( fxmlInitLocation, controller );
            gameMenu.setMenuPane( (HBox) root );
            gameMenu.addObserver( this );
            game.setGameContentPane( gameMenu.getMenuPane() );
            return true;
        }
        catch ( IOException e ) {
            // FXML konnte nicht geladen werden. Fehlerbehandlung notwendig.
            e.printStackTrace();
        }
        return false;
    }

    private Parent loadFxml( String fxmlLocation, Observable controller ) throws IOException {
        controller.addObserver( this );
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation( getClass().getResource( fxmlLocation ) );
        fxmlLoader.setController( controller );
        return fxmlLoader.load();                       //setzt root einmalig. Warum auch immer funtioniert temp als Object hier nicht!

    }

    /**
     * Läd die FXML dynamisch nach.
     * Um die Observer zu benutzen, müssen diese hier manuel gesetzt werden, nicht im FXML eintragen!!!
     *
     * @param fxml  StringCode welcher FXML Fall eintreten soll
     *
     * @return Boolean-Wert ob das laden funtioniert hat oder nicht.
     * */
    private boolean loadFxmlMenu(String fxml) {

        //setze die Stage einmallig !!!nach!!! dem die FXML Init-Phase abgeschlossen ist. Darf nicht in init() oder im Konstruktor stehen!!!
        if (window == null) {
            window = (Stage) (root.getScene().getWindow());
        }

        switch (fxml) {

            //Erstes FXML nochmal laden. Wenn man aus einem anderen Menu zu diesem Menu zurückkehren will!
            case erstesFxml:
                try {
                    FXExampleController controller = new FXExampleController();
                    controller.addObserver(this);
                    temp = loadFxml(fxml_1_path, controller);   //benutze temp als Zwischenspeicher statt root!
                    window.setScene(new Scene(temp));           //Setze in der stage eine neue Scene mit dem geladenen FXML über die Window-Referenz.

                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            //Zweites FXML für ein anderes Menu laden.
            case zweitesFxml:
                /*
                try {
                    ZweiterController controller = new Zweiter_controller();
                    controller.addObserver(this);
                    temp = loadFxml(fxml_2_path, controller);              //benutze temp als Zwischenspeicher statt root!
                    window.setScene(new Scene(temp));                      //Setze in der stage eine neue Scene mit dem geladenen FXML über die Window-Referenz.

                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                break;

            //verlasse dein Spiel und lade das Haupt-Menu.
            case UpdateCodes.DefaultCodes.exitToMainGUI:
                game.exitToMainGUI();       //rufe in deinem Spiel die Verlassen-Methode auf.
                break;

            default:
                Logger.log("ExampleFXMLLoading Switchcase -> default");
                return false;
        }
        return false;
    }

    /**
     *  Handhabt was bei dem Button drücken passieren soll.
     *  An dieser Stelle und nicht im Sub-Controller um das ObserverShema einzuhalten.
     *  Von diesem Controller bzw. dieser Mehtode aus sollen alle Aufrufe und Weiterleitungen an Model Klassen passieren!
     *
     * @param code  Der ButtonCode der behandelt werden soll.
     * */
    private void handle_FXExample_controller(String code) {

        //gucke welcher UpdateCode behandelt werden soll
        switch (code) {
            case ExampleUpdateCodes.ErsteGui.code1:
                //lade das erste FXML
                loadFxmlMenu(erstesFxml);
                break;

            case ExampleUpdateCodes.ErsteGui.code2:
                //lade das zweite FXML
                loadFxmlMenu(zweitesFxml);
                break;

            case UpdateCodes.DefaultCodes.exitToMainGUI:
                //kehre zur Haupt-Gui zurück
                game.exitToMainGUI();
                break;

            default:
                //default Fall, fals kein 'case' zutrifft
                Logger.log("handle_FXExample_controller: default erreicht");
        }

    }

    /*
    private void handle_Anderen_controller(String code) {

        //gucke welcher Button gedrückt wurde und handel dem entsprechend.
        switch (code) {
            case "button_1_ID":
                //lade das anderes erste FXML
                loadFxmlMenu(erstesFxml);
                break;

            case "button_2_ID":
                //lade das andere zweite FXML
                loadFxmlMenu(zweitesFxml);
                break;

            case "button_exit":
                //kehre zur Haupt-Gui zurück
                loadFxmlMenu(exitToMain);
                break;

            default:
                //default Fall, fals kein 'case' zutrifft
                Logger.log("handle_FXExample_controller: default erreicht");
        }

    }
    */

    /**
     * Update Methode dieses Observers um die notifyObserver der Unter-Controller hier auswerten!
     *
     * @param o Der Controller der notifyObserver aufruft.
     *
     * @param code Der UpdateCode des Spiels, der behandelt werden soll.
     */
    @Override
    public void update(Observable o, Object code) {

        if (o instanceof FXExampleController) {                         //gucke aus welchem Controller das NotifyObserver kommt.
            handle_FXExample_controller((String) code);                 //rufe den entsprechenden Handler auf.
        }
        /*
        else if (o instanceof AndererFXController) {                    //gucke aus welchem Controller das NotifyObserver kommt.
            handle_Anderen_controller((ExampleUpdateCodes) code);       //rufe den entsprechenden Handler auf.
        }
        */
        else {
            Logger.log("default Fall bei update in ExampleFXMLLoading");
        }
    }

}
