package de.hsh.dennis

import common.config.WindowConfig
import common.engine.components.game.GameEntryPoint
import common.updates.UpdateCodes
import common.util.Logger
import de.hsh.dennis.controller.LevelMenu_controller
import de.hsh.dennis.controller.MainMenu_controller
import de.hsh.dennis.model.Difficulty
import de.hsh.dennis.model.GameModel
import de.hsh.dennis.model.KeyLayout
import de.hsh.dennis.model.Spawn.NpcHandler
import javafx.application.Platform
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import java.lang.IllegalStateException
import java.util.*

class DennisGameEntryPoint(o: Observer) : GameEntryPoint(o, WindowConfig.dennis_title) {
    private val changer: DennisFxmlChanger = DennisFxmlChanger(this, "view/mainMenu.fxml", MainMenu_controller())
    private val gm: GameModel = GameModel()
    private var rendering = false
    private var lastGameMode: Difficulty? = null

    init {
        gm.addObserver(this)
    }

    override fun render(fps: Int) {
        if (!gm.isActing) gm.printLoading()
        if (gm.fps != fps) gm.fps = fps
        if (rendering) Platform.runLater { gm.act() }
    }

    override fun update(o: Observable, arg: Any) {
        if (arg is Difficulty) {
            lastGameMode = arg
            gm.difficulty = arg
        }
        if (arg is String) {
            if (o is GameModel) when (arg) {
                UpdateCodes.Dennis.gameLost -> {
                    Logger.log("!!! YOU LOSE !!!")
                    rendering = false
                    changer.changeFxml(o, arg)
                    gm.reset()
                }
                UpdateCodes.Dennis.gameWon  -> {
                    Logger.log("!!! YOU WIN !!!")
                    rendering = false
                    changer.changeFxml(o, arg)
                    gm.reset()
                }
            }
        } else if (arg is Canvas) {
            gm.canvas = arg
            gm.npcHandler = NpcHandler(arg)
        } else if (arg is KeyCode) {
            if (arg === KeyLayout.Control.BREAK || arg === KeyLayout.Control.BREAK_ALT) {
                gm.triggerBreak()
                rendering = false
                changer.changeFxml(o, arg.toString()) //no game pausing
            } else {
                gm.userInput(arg)
            }
        } else if (arg is String) {
            when (arg) {
                UpdateCodes.Dennis.gameReady -> rendering = true
                UpdateCodes.Dennis.replay    -> {
                    gm.reset()
                    when (lastGameMode) {
                        Difficulty.EASY      -> changer.changeFxml(LevelMenu_controller(), "b_easy")
                        Difficulty.MEDIUM    -> changer.changeFxml(LevelMenu_controller(), "b_medium")
                        Difficulty.HARD      -> changer.changeFxml(LevelMenu_controller(), "b_hard")
                        Difficulty.NIGHTMARE -> changer.changeFxml(LevelMenu_controller(), "b_nightmare")
                        Difficulty.CUSTOM -> throw IllegalStateException("Custom difficulty is not implemented")
                        null                 -> throw IllegalStateException("lastGameMode is null")
                    }
                }
                UpdateCodes.Dennis.continiue -> {
                    gm.unTriggerBreak()
                    rendering = true
                }
                "b_main_menu"                -> {
                    gm.reset()
                    changer.changeFxml(o, arg)
                }
                else                         -> changer.changeFxml(o, arg)
            }
        }
    }
}
