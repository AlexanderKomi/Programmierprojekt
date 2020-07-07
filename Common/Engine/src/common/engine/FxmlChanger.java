package common.engine;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Optional;


/***
 * Subclass for a FxModule that takes care of reloading Fxml documents and their controllers.
 * <p>
 *     By default, loads a fxml on the Build object.
 *     Works directly on the scene of his FxModul.
 * </p>
 */
public abstract class FxmlChanger extends Observable {

    private final FxModul fxModul;

    /**
     * @param fxModul      The FxModul in which this constructor is called or rather in which the scene to be edited is located.
     * @param fxmlPath     Path to Fxml to be loaded when creating.
     * @param fxController Matching controller for the .fxml
     */
    public FxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        if (fxModul == null || fxmlPath == null || fxmlPath.isEmpty() || fxController == null) {
            throw new NullPointerException("\t\tConstructor: FxmlChanger invalid parameters!");
        }
        this.fxModul = fxModul;
        this.addObserver(fxModul);
        fxController.addObserver( fxModul );
        Optional<Parent> p = loadFxml( fxmlPath, fxController );
        if (p.isPresent()) {
            this.fxModul.setScene( new Scene( p.get()) );
        } else {
            throw new NullPointerException("FXML kann nicht geladen werden: " + fxmlPath);
        }
    }

    /***
     * Load a Parent Object
     *
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     * @return A loaded Parent
     */
    private Optional<Parent> loadFxml(String fxmlLocation, Observable controller) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(fxmlLocation));
            fxmlLoader.setController(controller);
            return Optional.of(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /***
     * Method to change the root element of the current scene of the FxModule
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    public void changeScene( String fxmlLocation, Observable controller ) {
        controller.addObserver(getFxModul());
        loadFxml(fxmlLocation, controller)
                .ifPresent(parent -> getFxModul().setRoot(parent));
    }

    /**
     * Insert your changeScene(String fxmlLocation, Observable controller) calls here as you please.
     */
    public abstract void changeFxml(Observable o, String msg);

    // --- Getter & Setter ----------------------------------------------------------------

    protected FxModul getFxModul() {
        return this.fxModul;
    }
}
