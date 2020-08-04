package de.hsh.kevin.controller

import common.engine.components.game.GameEntryPoint
import common.updates.UpdateCodes
import de.hsh.kevin.TIFxmlChanger
import de.hsh.kevin.logic.Score
import javafx.application.Platform
import java.util.*

/**
 * Erstellt den Controller für die Menüs und das Spiel
 *
 * @author Kevin
 */
class TIController(o: Observer?) : GameEntryPoint(o, UpdateCodes.TunnelInvader.gameName) {
    private val changer: TIFxmlChanger = TIFxmlChanger(this, TIMenuController.fxml, TIMenuController())
    private var game: TIGameController? = null
    private var gameOver: TIGameOverController? = null
    private var score: Score? = null

    /**
     * Ändert die Scenen je nach UpdateCode der übergeben wurde
     */
    override fun update(o: Observable, arg: Any) {
        if (arg is String) {
            when (arg) {
                UpdateCodes.TunnelInvader.playGame -> {
                    score = Score()
                    if (game != null) {
                        game!!.deleteObservers()
                    }
                    game = TIGameController(score)
                    changer.changeGameFxml(o, game)
                }
                UpdateCodes.TunnelInvader.gameOver -> {
                    if (game != null) {
                        game!!.deleteObservers()
                        game = null
                    }
                    if (gameOver != null) {
                        gameOver!!.deleteObservers()
                    }
                    gameOver = TIGameOverController(score)
                    changer.changeGameOverFxml(o, gameOver)
                    gameOver!!.setScore()
                }
                else                               -> changer.changeFxml(o, arg)
            }
        }
    }

    /**
     * Render: ruft die Render-Methode des SpielControllers auf
     */
    override fun render(fps: Int) {
        Platform.runLater {
            if(game != null) {
                game!!.render(fps)
            }
        }
    }

}
