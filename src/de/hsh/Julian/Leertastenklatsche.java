package de.hsh.Julian;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.events.KeyEventManager;
import common.util.Logger;
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

public class Leertastenklatsche extends GameEntryPoint {

    private GraphicsContext   gc;
    private Pane              root         = new Pane();
    private Canvas            canvas       = new Canvas( WindowConfig.window_width, WindowConfig.window_height );
    private int               score        = 0;
    private Sprite            thedude = new Sprite();
    private String            location     = getLocation();
    private ArrayList<String> input        = new ArrayList<String>();
    private ArrayList<Sprite> enemyList = new ArrayList<Sprite>();

    public Leertastenklatsche( Observer o ) {
        super( o, "Leertastenklatsche" );
        this.canvas.setFocusTraversable( true );// DO NOT DELETE!!!! -> Otherwise does not fire events!
        this.setGameContentPane( this.initGameContentWindow( o ) );
        this.getGameContentPane().setOnKeyPressed(
                e -> {
                    String code = e.getCode().toString();
                    if ( !input.contains( code ) ) {
                        input.add( code );
                    }

                } );
        this.getScene().setRoot( (Parent) this.getGameContentPane() );
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

        thedude.setImage( location + "/thedude.png" );
        thedude.setPosition(WindowConfig.window_width/2, WindowConfig.window_height*0.6);
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
                    Logger.log(this.getClass()+ " Key pressed : " + code );
                } );

        root.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
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

    private void collisionDetection() {
        for ( Sprite enemy : enemyList ) {
            if ( thedude.intersects( enemy ) ) {
                score++;
            }
        }
    }

    private void createEnemies() {
        for ( int i = 0 ; i < 15 ; i++ ) {
            Sprite enemyvirus = new Sprite();
            enemyvirus.setImage( location + "/enemyvirus.png" );
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            enemyvirus.setPosition( px, py );
            enemyList.add( enemyvirus );
        }
    }

    private void parseInput( ArrayList<String> input ) {
        double v = 0.5;
        thedude.setVelocity( 0, 0 );
        if ( input.contains( "LEFT" ) ) {
            thedude.addVelocity( -v, 0 );
        }
        if ( input.contains( "RIGHT" ) ) {
            thedude.addVelocity( v, 0 );
        }
        /*if ( input.contains( "UP" ) ) {
            thedude.addVelocity( 0, -v );
        }
        if ( input.contains( "DOWN" ) ) {
            thedude.addVelocity( 0, v );
        }*/
    }

    public void render() {
        parseInput( input );
        gc.clearRect( 0, 0, WindowConfig.window_width, WindowConfig.window_height );

        for ( Sprite enemy : enemyList ) { enemy.render( gc ); }

        String pointsText = "LEERTASTENKLATSCHE\nGegner abgewehrt: " + (100 * score);
        Logger.log(getClass()+" Score: "+score);
        gc.fillText( pointsText, 360, 36 );
        gc.strokeText( pointsText, 360, 36 );

        thedude.update( 10 );
        thedude.render( gc );
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
