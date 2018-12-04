package de.hsh.Julian;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
import de.hsh.Julian.controller.LKStart;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Observable;
import java.util.Observer;

//
//

public class LKEntryPoint extends GameEntryPoint {

    private final LKFxmlChanger      changer;
    private       boolean            renderable = false;
    private       GraphicsContext    gc;
    private       Canvas             canvas;
    private       Leertastenklatsche lk;

    public LKEntryPoint(Observer o) {
        super( o, WindowConfig.julian_title );
        changer = new LKFxmlChanger(this, LKStart.fxml, new LKStart());
    }

    //Getting the input and realizing when stopping input
    private void addKeyListener() {
        canvas.setOnKeyPressed(
                e -> lk.parseInput( e.getCode().toString() ) );

        canvas.setOnKeyReleased(
                e -> lk.parseInput( e.getCode().toString() ) );
    }

    private void initializeGraphicsContext() {
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 36);
        gc = canvas.getGraphicsContext2D();
        gc.setFont(theFont);
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
    }


    public void render(int fps) {
        if(renderable) {
            gc.clearRect(0, 0, WindowConfig.window_width, WindowConfig.window_height);
            lk.render( this.canvas );
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Canvas) {
            this.canvas = (Canvas) arg;
            Logger.log( this.getClass() + ": init after canvas pass" );
            initAfterCanvasPass();
        } else {
            changer.changeFxml(o, (String) arg);
        }
    }

    private void initAfterCanvasPass() {
        this.lk = new Leertastenklatsche();
        initializeGraphicsContext();
        addKeyListener();
        renderable = true;
    }

}
