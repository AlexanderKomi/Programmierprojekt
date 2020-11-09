package common.engine

import common.updates.Updatable
import common.updates.Updater
import javafx.scene.Scene
import javafx.scene.layout.Pane

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
abstract class FxModul(container: Updater) : Updatable, Updater {
    var scene: Scene = Scene(Pane()) //local scene being worked on.

    init {
        @Suppress("LeakingThis")
        this.addUpdater(container)
    }
}
