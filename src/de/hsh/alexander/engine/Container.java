package de.hsh.alexander.engine;

import javafx.application.Application;

public abstract class Container extends Application
        implements GameContainerInterface {

    // JavaFX Properties
    private static boolean fxApplicationLaunched = false;

    //Engine properties
    private Java2DEngine engine;
    private boolean      running = false;

    Container() {
        this.setEngine( new Java2DEngine() );
        this.getEngine().setGameContainer( this ); // This must be in every class, which is an FXGameContainer.
    }

    public Java2DEngine getEngine() {return this.engine;}

    //-------------------------------------- GETTER & SETTER --------------------------------------

    protected void setEngine( Java2DEngine java2DEngine ) {
        this.engine = java2DEngine;
    }

    void startEngine() {
        this.setRunning( true );
        this.getEngine().init();
        this.getEngine().start();
    }

    public static boolean isLaunched() {
        return Container.fxApplicationLaunched;
    }

    static void setLaunched( boolean value ) {
        Container.fxApplicationLaunched = value;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }

    protected int getFPS() {
        return this.engine.getFps();
    }
}
