package de.hsh.Julian;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
import de.hsh.Julian.controller.LKStart;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Observable;
import java.util.Observer;

//
//

public final class LKEntryPoint extends GameEntryPoint {

    private final LKFxmlChanger      changer;
    private       boolean            renderable = false;
    private       GraphicsContext    gc;
    private       Canvas             canvas;
    private       Leertastenklatsche lk;

    public LKEntryPoint( Observer o ) {
        super( o, WindowConfig.julian_title );
        changer = new LKFxmlChanger( this, LKStart.fxml, new LKStart() );
    }

    //Getting the input and realizing when stopping input
    private void addKeyListener() {
        canvas.setOnKeyPressed(
                e -> lk.parseInput( e.getCode().toString() ) );

        canvas.setOnKeyReleased(
                e -> lk.parseInput( e.getCode().toString() ) );
    }

    private void initializeGraphicsContext() {
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 36 );
        gc = canvas.getGraphicsContext2D();
        gc.setFont( theFont );
        gc.setFill( Color.BLACK );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth( 1 );
    }


    public void render( final int fps ) {
        if ( !renderable || lk == null ) {
            return;
        }
        Platform.runLater( () -> {
            gc.clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
        } );
        lk.render( this.canvas );
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( arg instanceof Canvas ) {
            this.canvas = (Canvas) arg;
            initAfterCanvasPass();
        }
        else if ( arg instanceof String ) {
            String message = (String) arg;
            if ( message.equals( "b_backtomenu" ) ) {
                renderable = false;
                lk = null;
                changer.changeScene( LKStart.fxml, new LKStart() );
                exitToMainGUI();
            }
            /*else if(message.equals("b_retry")){
                changer.changeScene(SpielBildschirm_controller.fxml, new SpielBildschirm_controller());
            }*/
            else {
                Logger.log( (String) arg );
                changer.changeFxml( o, (String) arg );
            }
            // Logger.log( this.getClass() + " : update : " + message );
        }
        else {
            changer.changeFxml( o, (String) arg );
        }
    }

    private void initAfterCanvasPass() {
        this.lk = new Leertastenklatsche();
        lk.addObserver( this );
        initializeGraphicsContext();
        addKeyListener();
        renderable = true;
    }

}
