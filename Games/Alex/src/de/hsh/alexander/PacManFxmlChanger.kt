package de.hsh.alexander

import common.engine.FxModul
import common.engine.FxmlChanger
import common.updates.UpdateCodes
import common.logger.Logger
import javafx.application.Platform
import java.util.*

class PacManFxmlChanger(fxModul: FxModul,
                        fxmlPath: String,
                        fxController: Observable) :
        FxmlChanger(fxModul,
                    fxmlPackage + fxmlPath,
                    fxController) {
    override fun changeScene(fxmlLocation: String,
                             controller: Observable) = Platform.runLater {
        super.changeScene(fxmlPackage + fxmlLocation, controller)
    }

    override fun changeFxml(o: Observable?,
                            msg: String?) = when (msg) {
        UpdateCodes.PacMan.startGame -> changeScene(PacManGame.fxml, o!!)
        UpdateCodes.PacMan.mainMenu -> changeScene(PacManMenu.fxml, o!!)
        UpdateCodes.PacMan.showEndScreen -> changeScene(PacManEndScreen.fxml, o!!)
        else                             -> Logger.log(this.javaClass.toString() + ": changeFxml in PacManFxmlCanger: default")
    }

    companion object {
        private const val fxmlPackage = "view/"
    }
}
