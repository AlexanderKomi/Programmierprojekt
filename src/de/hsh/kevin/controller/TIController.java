package de.hsh.kevin.controller;

import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import de.hsh.kevin.TIFxmlChanger;
import de.hsh.kevin.logic.Score;

import java.util.Observable;
import java.util.Observer;

public class TIController extends GameEntryPoint {

    private TIFxmlChanger changer;
    private TIGameController game;
    private Score score;

    public TIController(Observer o) {
        super(o, UpdateCodes.TunnelInvader.gameName);
        changer = new TIFxmlChanger(this, TIMenuController.fxml, new TIMenuController());
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String msg = (String) arg;

            switch (msg) {
            case UpdateCodes.TunnelInvader.playGame:
                score = new Score();
                game = new TIGameController(score);
                changer.changeGameFxml(o, game);
                break;
            case UpdateCodes.TunnelInvader.gameOver:
                TIGameOverController gameOver = new TIGameOverController(score);
                changer.changeGameOverFxml(o, gameOver);
                gameOver.setScore();
                break;
            default:
                changer.changeFxml(o, (String) arg);
                break;
            }
        }
    }

    public void render(int fps) {
        if (game != null) {
            game.render(fps);
        }
    }

}
