package de.hsh.Julian;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.util.Logger;
import de.hsh.alexander.src.PacManFxmlChanger;
import de.hsh.alexander.src.PacManGame;
import de.hsh.alexander.src.PacManMenu;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static common.util.Path.getExecutionLocation;

//
//

public class LKEntryPoint extends GameEntryPoint {

    private final LKFxmlChanger changer;
    //private final PacManGame game;
    private GraphicsContext   gc;
    private Pane              root         = new Pane();
    private Canvas            canvas       = new Canvas( WindowConfig.window_width, WindowConfig.window_height );
    private Leertastenklatsche lk          = new Leertastenklatsche();
    //private Sprite            enem = new Sprite();

    public LKEntryPoint(Observer o ) {
        super( o, "LKEntryPoint" );
        this.canvas.setFocusTraversable( true );// DO NOT DELETE!!!! -> Otherwise does not fire events!
        this.setGameContentPane( this.initGameContentWindow( o ) );
        this.getGameContentPane().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !lk.getInput().contains( code ) ) {
                        lk.getInput().add( code );
                    }

                } );
        this.getScene().setRoot( (Parent) this.getGameContentPane() );


        //game = new PacManGame();
        changer = new LKFxmlChanger( this, LKStart.fxml, new LKStart() );
    }

    private Node getGameContentPane() {
        return this.root;
    }

    private void setGameContentPane(Pane initGameContentWindow) {
        this.root=initGameContentWindow;    }

    public Pane initGameContentWindow( Observer observer ) {

     //   Logger.log( "Dir : " + getExecutionLocation(),  Path.getAllFileNames(getExecutionLocation()) );
        addKeyListener();
        root.getChildren().add( canvas );
        initializeGraphicsContext();


        return root;
    }
    //Getting the input and realizing when stopping input
    private void addKeyListener() {
        canvas.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !lk.getInput().contains( code ) ) {
                        lk.getInput().add( code );
                    }
                    Logger.log(this.getClass()+ " Key pressed : " + code );
                } );

        root.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    lk.getInput().remove( code );
                    Logger.log(this.getClass()+ " Key pressed : " + code );
                } );

    }

    private void initializeGraphicsContext() {
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 36 );
        gc = canvas.getGraphicsContext2D();
        gc.setFont( theFont );
        gc.setFill( Color.BLACK );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth( 1 );
    }






    public void render(int fps) {
        gc.clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );
        lk.render(this.gc);
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof KeyEventManager ) {
            if ( arg instanceof KeyEvent ) {
                KeyEvent keyEvent = (KeyEvent) arg;
                String   type     = keyEvent.getEventType().getName();
                if ( type.equals( "KEY_PRESSED" ) ) {
                    String code = keyEvent.getCode().toString();
                    if ( !lk.getInput().contains( code ) ) {
                        lk.getInput().add( code );
                    }
                }
                else if ( type.equals( "KEY_RELEASED" ) ) {
                    String code = keyEvent.getCode().toString();
                    lk.getInput().remove( code );
                }
            }
        }
    }


}
