package de.hsh.alexander.engine;

import de.hsh.alexander.Java2DEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public abstract class FXGame
        extends Application
        implements GameInterface {

    private Java2DEngine engine;

    private boolean running = false;

    public FXGame() {
        this.engine = new Java2DEngine();
    }

    @Override
    public void start( Stage primaryStage ) {
        primaryStage.setOnCloseRequest( close -> {
            this.stopGame();
            Platform.exit();
        } );
        primaryStage = configGui( primaryStage );
        this.engine.init();
        this.engine.start();
        primaryStage.show();
    }

    @Override
    public void initGame( String[] args ) {
        this.setRunning( true );
        launch( args );
    }

    @Override
    public void initGame() {
        this.setRunning( true );
        launch();
    }

    @Override
    public void stopGame() {
        this.setRunning( false );
        this.engine.setRunning( false );
    }

    public synchronized boolean isRunning() {
        return running;
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    public abstract Stage configGui( Stage primaryStage );

    public Java2DEngine getEngine() {return this.engine;}

}
