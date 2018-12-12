package common.engine;

import javafx.application.Application;

public abstract class Container extends Application
        implements GameContainerInterface {

    // JavaFX Properties
    private static boolean fxApplicationLaunched = false;

    //Engine properties
    private final Java2DEngine engine;
    private       boolean      running = false;

    Container() {
        this.engine = new Java2DEngine();
        this.getEngine().setGameContainer( this ); // This must be in every class, which is an FXGameContainer.
    }

    public Java2DEngine getEngine() {return this.engine;}

    //-------------------------------------- GETTER & SETTER --------------------------------------

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
