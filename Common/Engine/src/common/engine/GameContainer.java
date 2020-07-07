package common.engine;

import javafx.application.Application;

public abstract class GameContainer extends Application
        implements GameContainerInterface {

    // JavaFX Properties
    private boolean fxApplicationLaunched = false;

    //Engine properties
    private final Java2DEngine engine;
    private       boolean      running = false;

    GameContainer() {
        this.engine = new Java2DEngine( this );// This must be in every class, which is an FXGameContainer.
    }

    public Java2DEngine getEngine() {return this.engine;}

    //-------------------------------------- GETTER & SETTER --------------------------------------

    final void startEngine() {
        this.setRunning( true );
        this.getEngine().start();
    }

    public boolean isLaunched() {
        return this.fxApplicationLaunched;
    }

    void setLaunched() {
        this.fxApplicationLaunched = true;
    }

    public synchronized boolean isRunning() {
        return running;
    }

    public synchronized void setRunning( boolean running ) {
        this.running = running;
    }
}
