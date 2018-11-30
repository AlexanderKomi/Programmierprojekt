package common.engine;

import java.util.Observable;

public interface GameContainerInterface {

    /**
     * Start the new game. Must be called from the class launching the application!
     *
     * @param args
     *         Program arguments
     */
    void startContainer( String[] args );

    /**
     * Start the new game. Must be called from the class launching the application!
     */
    void startContainer();

    /**
     * Gets called every time a new frame is rendered.
     * Use this to update every frame.
     */
    void render();

    void update( Observable o, Object arg );

    /**
     * Stops the engine container. Kills every process inside the container.
     */
    void stopContainer();

    //-------------------------------------- GETTER & SETTER --------------------------------------

    boolean isRunning();

    void setRunning( boolean value );

}
