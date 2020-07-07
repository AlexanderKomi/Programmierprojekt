package common.engine;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * A module with a scene that can be included in a higher place if needed.
 * <p>
 * Despite incorporation elsewhere, the encapsulated editing of the scene should be ensured.
 * <p>
 * Either use an FXML document via an attached FxmlChanger (object via FxmlChanger.changeFxml ())
 * or attach your Fx code in this class directly via using setScene(Scene scene).
 * </p>
 */
public abstract class FxModul extends Observable implements Observer {

    private Scene loadedScene;      //local scene being worked on.

    /***
     * Constructor with a passed observer object.
     * @param container belonging observer
     */
    public FxModul(Observer container) {
        this( container, true );
    }

    /***
     * Constructor with a passed observer object and the option to not load a default scene.
     * @param container belonging observer
     * @param b true -> load a default scene, false -> default scene = null;
     */
    public FxModul(Observer container, boolean b) {
        if (b) {
            this.loadedScene = new Scene( new Pane() );
        }
        this.addObserver(Objects.requireNonNull(container));
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public Scene getScene() {
        return this.loadedScene;
    }

    public void setScene(Scene scene) {
        this.loadedScene = scene;
    }

    public void setRoot(Parent root) {
        getScene().setRoot(root);
    }
}
