package de.hsh.Examples;

import de.hsh.alexander.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

import java.util.Observable;

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
        String id = getId(event);
        switch (id){
            case "button_1_ID":
                setChanged();
                notifyObservers(id);
                break;

            case "button_2_ID":
                setChanged();
                notifyObservers(id);
                break;

            case "button_exit":
                setChanged();
                notifyObservers(id);
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
