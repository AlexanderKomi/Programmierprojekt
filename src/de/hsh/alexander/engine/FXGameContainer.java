package de.hsh.alexander.engine;

import de.hsh.alexander.Java2DEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public abstract class FXGameContainer
        extends Application
        implements GameInterface {


    private Canvas       canvas;
    private Java2DEngine engine;
    private boolean      running = false;

    public FXGameContainer() {
        this.setEngine( new Java2DEngine() );
        this.getEngine().setGame( this ); // This must be in every class, which is an FXGameContainer.
    }


    @Override
    public void start( Stage primaryStage ) {
        this.setRunning( true );
        this.getEngine().init();
        this.getEngine().start();

        primaryStage = configGui( primaryStage );
        primaryStage.setOnCloseRequest( close -> {
            this.stopGame();
            Platform.exit();
        } );
        primaryStage.show();
    }


    @Override
    public void stopGame() {
        this.setRunning( false );
        this.getEngine().setRunning( false );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    public abstract Stage configGui( Stage primaryStage );

    public Java2DEngine getEngine() {return this.engine;}

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas( Canvas canvas ) {
        this.canvas = canvas;
    }
}
