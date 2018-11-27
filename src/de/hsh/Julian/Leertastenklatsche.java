package de.hsh.Julian;

import common.events.KeyEventManager;
import common.engine.components.game.GameEntryPoint;
import common.util.Logger;
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

public class Leertastenklatsche extends GameEntryPoint {

    private GraphicsContext   gc;
    private Pane              root         = new Pane();
    private Canvas            canvas       = new Canvas( 512, 512 );
    private int               score        = 0;
    private Sprite            briefcase    = new Sprite();
    private String            location     = getLocation();
    private ArrayList<String> input        = new ArrayList<String>();
    private ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();

    public Leertastenklatsche( Observer o ) {
        super( o, "Leertastenklatsche" );

        this.setGameContentPane( this.initGameContentWindow( o ) );
        /*

        this.getGameContentPane().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains( code ) ) {
                        input.add( code );
                    }
                    Logger.log( "Key pressed : " + code );
                } );
         */
    }

    private void setGameContentPane(Pane initGameContentWindow) {
        this.root=initGameContentWindow;    }

    public Pane initGameContentWindow( Observer observer ) {

     //   Logger.log( "Dir : " + getExecutionLocation(),  Path.getAllFileNames(getExecutionLocation()) );
        addKeyListener();
        root.getChildren().add( canvas );
        initializeGraphicsContext();

        briefcase.setImage( location + "/briefcase.png" );
        briefcase.setPosition(200, 0);
        collisionDetection();
        createEnemies();
        parseInput( input );
        render();
        return root;
    }

    private void addKeyListener() {
        canvas.setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains( code ) ) {
                        input.add( code );
                    }
                    Logger.log( "Key pressed : " + code );
                } );

        root.setOnKeyPressed( e -> {
            String code = e.getCode().toString();
            if ( !input.contains( code ) ) {
                input.add( code );
            }
            Logger.log( "Key pressed : " + code );
        } );
        root.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                    Logger.log( "Key pressed : " + code );
                } );

    }

    private void initializeGraphicsContext() {
        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc = canvas.getGraphicsContext2D();
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth( 1 );
    }

    private void collisionDetection() {
        for ( Sprite moneybag : moneybagList ) {
            if ( briefcase.intersects( moneybag ) ) {
                score++;
            }
        }
    }

    private void createEnemies() {
        for ( int i = 0 ; i < 15 ; i++ ) {
            Sprite moneybag = new Sprite();
            moneybag.setImage( location + "/moneybag.png" );
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition( px, py );
            moneybagList.add( moneybag );
        }
    }

    private void parseInput( ArrayList<String> input ) {
        int v = 1;
        briefcase.setVelocity( 0, 0 );
        if ( input.contains( "LEFT" ) ) {
            briefcase.addVelocity( -v, 0 );
        }
        if ( input.contains( "RIGHT" ) ) {
            briefcase.addVelocity( v, 0 );
        }
        if ( input.contains( "UP" ) ) {
            briefcase.addVelocity( 0, -v );
        }
        if ( input.contains( "DOWN" ) ) {
            briefcase.addVelocity( 0, v );
        }
    }

    public void render() {
        parseInput( input );
        gc.clearRect( 0, 0, 512, 512 );

        for ( Sprite moneybag : moneybagList ) { moneybag.render( gc ); }

        String pointsText = "Cash: $" + (100 * score);
        gc.fillText( pointsText, 360, 36 );
        gc.strokeText( pointsText, 360, 36 );

        briefcase.update( 10 );
        briefcase.render( gc );
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof KeyEventManager ) {
            if ( arg instanceof KeyEvent ) {
                KeyEvent keyEvent = (KeyEvent) arg;
                String   type     = keyEvent.getEventType().getName();
                if ( type.equals( "KEY_PRESSED" ) ) {
                    String code = keyEvent.getCode().toString();
                    if ( !input.contains( code ) ) {
                        input.add( code );
                    }
                }
                else if ( type.equals( "KEY_RELEASED" ) ) {
                    String code = keyEvent.getCode().toString();
                    input.remove( code );
                }
            }
        }
    }

    private String getLocation() {
        return getExecutionLocation() + "de/hsh/Julian";
    }

}
