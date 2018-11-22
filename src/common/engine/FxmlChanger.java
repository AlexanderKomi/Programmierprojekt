package common.engine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;
import java.util.Observable;


/***
 * Subclass for a FxModule that takes care of reloading Fxml documents and their controllers.
 * <p>
 *     By default, loads a fxml on the Build object.
 *     Works directly on the scene of his FxModul.
 * </p>
 */
public abstract class FxmlChanger extends Observable {

    private FxModul fxModul;

    /**
     * @param fxModul      The FxModul in which this constructor is called or rather in which the scene to be edited is located.
     * @param fxmlPath     Path to Fxml to be loaded when creating.
     * @param fxController Matching controller for the .fxml
     */
    public FxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {

        if (fxModul != null && fxmlPath != null && !fxmlPath.equals("") && fxController != null) {
            setFxModul(fxModul);
            this.addObserver(fxModul);
            init(fxmlPath, fxController);
        } else throw new NullPointerException("\t\tConstructor: FxmlChanger invalid parameters!");
    }

    /**
     * Init method to load a .fxml als starting-Scene.
     *
     * @param fxmlpath   Path to the fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    private void init(String fxmlpath, Observable controller) {
        controller.addObserver(getFxModul());
        getFxModul().setScene(new Scene(Objects.requireNonNull(loadFxml(fxmlpath, controller))));
    }

    /***
     * Load a Parent Object
     *
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     * @return A loaded Parent
     */
    private Parent loadFxml(String fxmlLocation, Observable controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(fxmlLocation));
            fxmlLoader.setController(controller);
            return fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Method to change the root element of the current scene of the FxModule
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    protected void changeScene(String fxmlLocation, Observable controller) {
        controller.addObserver(getFxModul());
        getFxModul().setRoot(Objects.requireNonNull(loadFxml(fxmlLocation, controller)));
    }

    /**
     * Insert your changeScene(String fxmlLocation, Observable controller) calls here as you please.
     */
    public abstract void changeFxml(String msg);

    // --- Getter & Setter ----------------------------------------------------------------

    private FxModul getFxModul() {
        return this.fxModul;
    }

    private void setFxModul(FxModul fxModul) {
        this.fxModul = fxModul;
    }
}
