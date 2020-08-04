package common.engine

import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.layout.Pane
import java.util.*

/**
 * A module with a scene that can be included in a higher place if needed.
 *
 *
 * Despite incorporation elsewhere, the encapsulated editing of the scene should be ensured.
 *
 *
 * Either use an FXML document via an attached FxmlChanger (object via FxmlChanger.changeFxml ())
 * or attach your Fx code in this class directly via using setScene(Scene scene).
 *
 */
abstract class FxModul(container: Observer) : Observable(),
        Observer {
    // ----------------------------------- GETTER & SETTER  -----------------------------------
    var scene //local scene being worked on.
            : Scene = Scene(Pane())

    fun setRoot(root: Parent?) {
        scene.root = root
    }

    /***
     * Constructor with a passed observer object.
     * @param container belonging observer
     */
    init {
        addObserver(container)
    }
}
