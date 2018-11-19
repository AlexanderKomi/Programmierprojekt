package de.hsh.Examples;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import de.hsh.alexander.engine.game.GameMenu;
import de.hsh.alexander.util.Logger;

import java.util.Observable;


/**
 * Ewt überarbeiten
 *
 */
public class ExampleFXMLLoading extends FxmlController {

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
    public ExampleFXMLLoading(Game game, GameMenu menu) {
        super(game, menu);
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



        switch (fxml) {

            //Erstes FXML nochmal laden. Wenn man aus einem anderen Menu zu diesem Menu zurückkehren will!
            case erstesFxml:
                try {
                    FXExampleController controller = new FXExampleController();
                    controller.addObserver(this);
                    changeScene(fxml_1_path, controller);
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
                getGame().exitToMainGUI();       //rufe in deinem Spiel die Verlassen-Methode auf.
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
                getGame().exitToMainGUI();
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
