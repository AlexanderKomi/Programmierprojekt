package de.hsh.dennis

import common.config.WindowConfig
import common.engine.FxModul
import common.engine.FxmlChanger
import common.engine.components.game.GameEntryPoint
import common.updates.UpdateCodes
import common.util.Logger
import de.hsh.dennis.controller.*
import de.hsh.dennis.model.Difficulty
import de.hsh.dennis.model.GameModel
import de.hsh.dennis.model.KeyLayout
import de.hsh.dennis.model.NpcLogic.SkinConfig
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage
import java.io.IOException
import java.util.*

class DennisFxmlChanger(fxModul: FxModul, fxmlPath: String, fxController: Observable) :
        FxmlChanger(fxModul, fxmlPath, fxController) {
    var breakStage: Stage? = null
    private fun handle_Tutorial_controller(code: String?) {
        when (code) {
            "b_left"  -> Logger.log("handle_Tutorial_controller: b_left erreicht")
            "b_right" -> Logger.log("handle_Tutorial_controller:  b_right erreicht")
            "b_exit"  -> {
                Logger.log("handle_Tutorial_controller:  b_exit erreicht")
                changeScene("view/mainMenu.fxml", MainMenu_controller())
            }
            else      -> Logger.log("handle_Tutorial_controller:  default erreicht")
        }
    }

    private fun handle_LevelMenu_controller(code: String?) {
        val levelController = Level_controller()
        when (code) {
            "b_easy"      -> {
                Logger.log("handle_LevelMenu_controller: b_easy erreicht")
                changeScene("view/level.fxml", levelController)
                levelController.passCanvas()
                setChanged()
                notifyObservers(Difficulty.EASY)
            }
            "b_medium"    -> {
                Logger.log("handle_LevelMenu_controller: b_medium erreicht")
                changeScene("view/level.fxml", levelController)
                levelController.passCanvas()
                setChanged()
                notifyObservers(Difficulty.MEDIUM)
            }
            "b_hard"      -> {
                Logger.log("handle_LevelMenu_controller: b_hard erreicht")
                changeScene("view/level.fxml", levelController)
                levelController.passCanvas()
                setChanged()
                notifyObservers(Difficulty.HARD)
            }
            "b_nightmare" -> {
                Logger.log("handle_LevelMenu_controller: b_nightmare erreicht")
                changeScene("view/level.fxml", levelController)
                levelController.passCanvas()
                setChanged()
                notifyObservers(Difficulty.NIGHTMARE)
            }
            "b_back"      -> {
                Logger.log("handle_LevelMenu_controller: b_back erreicht")
                changeScene("view/mainMenu.fxml", MainMenu_controller())
            }
            else          -> Logger.log("handle_LevelMenu_controller: default erreicht")
        }
    }

    private fun handle_BreakMenu_controller(code: String?) {
        when (code) {
            "b_replay"    -> {
                Logger.log("handle_BreakMenu_controller: b_replay erreicht")
                breakStage!!.close()
                setChanged()
                notifyObservers(UpdateCodes.Dennis.replay)
            }
            "b_main_menu" -> {
                Logger.log("handle_BreakMenu_controller: b_main_menu erreicht")
                breakStage!!.close()
                changeScene("view/mainMenu.fxml", MainMenu_controller())
            }
            "b_continue"  -> {
                Logger.log("handle_BreakMenu_controller: b_continue erreicht")
                breakStage!!.close()
                setChanged()
                notifyObservers(UpdateCodes.Dennis.continiue)
            }
            else          -> Logger.log("handle_BreakMenu_controller: default erreicht")
        }
    }

    private fun handle_Level_controller(code: String?) {
        if (code == KeyLayout.Control.BREAK.toString() || code == KeyLayout.Control.BREAK_ALT.toString()) {
            openBreakMenu()
        }
    }

    private fun handle_MainMenu_controller(code: String?) {
        when (code) {
            "b_play"     -> {
                Logger.log("handle_MainMenu_controller: b_play erreicht")
                //lade das Level-Menu
                changeScene("view/levelMenu.fxml", LevelMenu_controller())
            }
            "b_tutorial" -> {
                Logger.log("handle_MainMenu_controller: b_tutorial erreicht")
                //lade das Tutorial-Menu
                changeScene("view/tutorial.fxml", Tutorial_controller())
            }
            "b_exit"     -> {
                Logger.log("handle_MainMenu_controller: b_exit erreicht")
                //kehre zur Haupt-Gui zurück
                (fxModul as GameEntryPoint).exitToMainGUI()
            }
            else         -> Logger.log("handle_MainMenu_controller: default erreicht")
        }
    }

    override fun changeFxml(o: Observable, msg: String) {
        when (o) {
            is Level_controller -> handle_Level_controller(msg)
            is BreakMenu_controller -> handle_BreakMenu_controller(msg)
            is LevelMenu_controller -> handle_LevelMenu_controller(msg)
            is MainMenu_controller -> handle_MainMenu_controller(msg)
            is Tutorial_controller -> handle_Tutorial_controller(msg)
            is GameModel -> handle_GameModel(o, msg)
            is EndScreen_controller -> handle_EndScreen_controller(msg)
        }
    }

    private fun handle_EndScreen_controller(msg: String?) {
        when (msg) {
            "b_replay"    -> {
                setChanged()
                notifyObservers(UpdateCodes.Dennis.replay)
            }
            "b_main_menu" -> changeScene("view/mainMenu.fxml", MainMenu_controller())
        }
    }

    private fun handle_GameModel(gm: GameModel, msg: String?) {
        var endTitle = "Score"
        when (msg) {
            UpdateCodes.Dennis.gameLost -> endTitle = "YOU LOSE!"
            UpdateCodes.Dennis.gameWon  -> endTitle = "YOU Win!"
        }
        val c = EndScreen_controller()
        changeScene("view/endScreen.fxml", c)
        c.setScore(gm.score)
        c.changeToEndScreen(endTitle)
    }

    private fun openBreakMenu() {
        try {
            val fxmlLoader = FXMLLoader(javaClass.getResource("view/breakMenu.fxml"))
            val controller = BreakMenu_controller()
            controller.addObserver(fxModul)
            fxmlLoader.setController(controller)
            val parent = fxmlLoader.load<Parent>()
            breakStage = Stage()
            breakStage!!.title = " - PAUSING - "
            val scene = Scene(parent,
                              BREAKMENU_WIDTH.toDouble(),
                              BREAKMENU_HEIGHT.toDouble())
            breakStage!!.initModality(Modality.APPLICATION_MODAL)
            breakStage!!.scene = scene
            breakStage!!.showAndWait()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {
        private const val BREAKMENU_HEIGHT = WindowConfig.window_height
        private const val BREAKMENU_WIDTH = WindowConfig.window_width
    }
}
