package de.hsh.kevin

import common.engine.FxModul
import common.engine.FxmlChanger
import common.engine.components.game.GameEntryPoint
import common.updates.MenuCodes
import common.updates.UpdateCodes
import de.hsh.kevin.controller.TIGameController
import de.hsh.kevin.controller.TIGameOverController
import de.hsh.kevin.controller.TIMenuController
import java.util.*

/**
 * Erstellt den FXMLChanger zum wechseln der Scenes
 */
class TIFxmlChanger(fxModul: FxModul, fxmlPath: String, fxController: Observable)
    : FxmlChanger(fxModul,
                  fxmlPackage + fxmlPath,
                  fxController) {
    /**
     * Ändert die Scene zum Hauptmenü oder Spielmenü
     */
    override fun changeFxml(o: Observable, msg: String) {
        when (o) {
            is TIMenuController         -> {
                when (msg) {
                    MenuCodes.exitToMainGUI -> (fxModul as GameEntryPoint).exitToMainGUI()
                }
            }
            is TIGameOverController -> {
                when (msg) {
                    MenuCodes.exitToMainGUI -> (fxModul as GameEntryPoint).exitToMainGUI()
                    UpdateCodes.TunnelInvader.gameMenu -> changeScene(fxmlPackage + TIMenuController.fxml,
                                                                      TIMenuController())
                }
            }
        }
    }

    /**
     * Ändert die Scene zum SpielScreen
     * @param game
     */
    fun changeGameFxml(game: TIGameController?) =
            changeScene(fxmlPackage + TIGameController.fxml, game!!)

    /**
     * Ändert die Scene zum GameOverScreen
     * @param gameOver
     */
    fun changeGameOverFxml(gameOver: TIGameOverController?) =
            changeScene(fxmlPackage + TIGameOverController.fxml, gameOver!!)

    companion object {
        private const val fxmlPackage = "res/"
    }
}
