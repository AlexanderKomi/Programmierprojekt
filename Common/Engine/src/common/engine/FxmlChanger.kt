package common.engine

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import java.io.IOException
import java.util.*

/***
 * Subclass for a FxModule that takes care of reloading Fxml documents and their controllers.
 *
 *
 * By default, loads a fxml on the Build object.
 * Works directly on the scene of his FxModul.
 *
 */
abstract class FxmlChanger(fxModul: FxModul?,
                           fxmlPath: String?,
                           fxController: Observable?) :
        Observable() {
    // --- Getter & Setter ----------------------------------------------------------------
    @JvmField
    protected val fxModul: FxModul

    /***
     * Load a Parent Object
     *
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     * @return A loaded Parent
     */
    private fun loadFxml(fxmlLocation: String,
                         controller: Observable): Optional<Parent> {
        try {
            val fxmlLoader = FXMLLoader()
            fxmlLoader.location = javaClass.getResource(fxmlLocation)
            fxmlLoader.setController(controller)
            return Optional.of(fxmlLoader.load())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Optional.empty()
    }

    /***
     * Method to change the root element of the current scene of the FxModule
     * @param fxmlLocation Path to the .fxml to be loaded
     * @param controller The controller suitable for the .fxml
     */
    open fun changeScene(fxmlLocation: String,
                         controller: Observable) {
        controller.addObserver(fxModul)
        loadFxml(fxmlLocation, controller)
                .ifPresent { parent: Parent? ->
                    fxModul.setRoot(parent)
                }
    }

    /**
     * Insert your changeScene(String fxmlLocation, Observable controller) calls here as you please.
     */
    abstract fun changeFxml(o: Observable?,
                            msg: String?)

    /**
     * @param fxModul      The FxModul in which this constructor is called or rather in which the scene to be edited is located.
     * @param fxmlPath     Path to Fxml to be loaded when creating.
     * @param fxController Matching controller for the .fxml
     */
    init {
        if (fxModul == null || fxmlPath == null || fxmlPath.isEmpty() || fxController == null) {
            throw NullPointerException("\t\tConstructor: FxmlChanger invalid parameters!")
        }
        this.fxModul = fxModul
        addObserver(fxModul)
        fxController.addObserver(fxModul)
        val p = loadFxml(fxmlPath, fxController)
        if (p.isPresent) {
            this.fxModul.scene = Scene(p.get())
        } else {
            throw NullPointerException("FXML kann nicht geladen werden: $fxmlPath")
        }
    }
}
