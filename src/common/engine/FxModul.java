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
     * Default Constructor with no effects.
     */
    public FxModul() {

    }

    /**
     * Constructor with the option to not load a default scene.
     *
     * @param b true -> load a default scene, false -> default scene = null;
     */
    public FxModul(boolean b) {
        if (b) {
            initScene();
        }
    }

    /***
     * Constructor with a passed observer object.
     * @param container belonging observer
     */
    public FxModul(Observer container) {
        initScene();
        this.addObserver(Objects.requireNonNull(container));
    }

    /***
     * Constructor with a passed observer object and the option to not load a default scene.
     * @param container belonging observer
     * @param b true -> load a default scene, false -> default scene = null;
     */
    public FxModul(Observer container, boolean b) {
        if (b) {
            initScene();
        }
        this.addObserver(Objects.requireNonNull(container));
    }

    /***
     * Constructor with a passed scene but no observer.
     * @param scene
     */
    public FxModul(Scene scene) {
        this.setScene(Objects.requireNonNull(scene));
    }

    /***
     * Constructor with a passed scene and a suiting observer.
     * @param scene
     * @param controller
     */
    public FxModul(Scene scene, Observer controller) {
        this.setScene(Objects.requireNonNull(scene));
        this.addObserver(Objects.requireNonNull(controller));
    }

    /***
     * Method to load a default scene.
     */
    private void initScene() {
        this.setScene(new Scene(new Pane()));
    }

    @Override
    public void update(Observable o, Object arg) {
    }
    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public Scene getScene() {
        return this.loadedScene;
    }

    public void setScene(Scene scene) {
        this.loadedScene = scene;
    }

    public Parent getRoot() {
        return getScene().getRoot();
    }

    public void setRoot(Parent root) {
        getScene().setRoot(root);
    }
}
