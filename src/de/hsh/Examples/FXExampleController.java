package de.hsh.Examples;

import common.updates.UpdateCodes;
import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Observable;
import java.util.Observer;

public class FXExampleController extends Observable {

    /*
    * @FXML ...
    *
    * { ... FX_IDs ... }
    *
    * @FXML ...
    *
    * */

    @FXML
    void button_clicked(ActionEvent event) {

        switch (getId(event)){
            case "button_1_ID":
                setChanged();
                notifyObservers(ExampleUpdateCodes.ErsteGui.code1);
                break;

            case "button_2_ID":
                setChanged();
                notifyObservers(ExampleUpdateCodes.ErsteGui.code2);
                break;

            case "button_exit":
                setChanged();
                notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
                break;

            default:
                Logger.log("ERROR : button_clicked Aufruf mit default Ergebniss!");

        }
    }

    /*
    *
    * */
    private String getId(ActionEvent event){
        return ((Node) event.getSource()).getId();
    }
}
