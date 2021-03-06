package de.hsh.kevin.controller;

import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.TIFxmlChanger;
import de.hsh.kevin.logic.Score;
import javafx.application.Platform;

import java.util.Observable;
import java.util.Observer;

/**
 * Erstellt den Controller für die Menüs und das Spiel
 * 
 * @author Kevin
 *
 */
public class TIController extends GameEntryPoint {

    private TIFxmlChanger changer;
    private TIGameController game;
    private TIGameOverController gameOver;
    private Score score;

    /**
     * Erstellt den Controller für Tunnel Invader
     * 
     * @param o
     */
    public TIController(Observer o) {
        super(o, UpdateCodes.TunnelInvader.gameName);
        changer = new TIFxmlChanger(this, TIMenuController.fxml, new TIMenuController());
    }

    /**
     * Ändert die Scenen je nach UpdateCode der übergeben wurde
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String msg = (String) arg;

            switch (msg) {
            case UpdateCodes.TunnelInvader.playGame:
                score = new Score();
                if (game != null) {
                    game.deleteObservers();
                }
                game = new TIGameController(score);
                changer.changeGameFxml(o, game);
                break;
            case UpdateCodes.TunnelInvader.gameOver:
                if (game != null) {
                    game.deleteObservers();
                    game = null;
                }
                if (gameOver != null) {
                    gameOver.deleteObservers();
                }
                gameOver = new TIGameOverController(score);
                changer.changeGameOverFxml(o, gameOver);
                gameOver.setScore();
                break;
            default:
                changer.changeFxml(o, (String) arg);
                break;
            }
        }
    }

    /**
     * Render: ruft die Render-Methode des SpielControllers auf
     */
    public void render(int fps) {
        if (game != null) {
            Platform.runLater(() -> game.render(fps));
        }
    }

}
