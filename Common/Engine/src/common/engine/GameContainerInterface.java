package common.engine;

import java.util.Observable;
import java.util.Observer;

public interface GameContainerInterface extends Observer {

    /**
     * Start the new game. Must be called from the class launching the application!
     *
     * @param args
     *         Program arguments
     */
    void startContainer( String[] args );

    /**
     * Gets called every time a new frame is rendered.
     * Use this to update every frame.
     * @param fps
     */
    void render( final int fps );

    void update( Observable o, Object arg );

    /**
     * Stops the engine container. Kills every process inside the container.
     */
    void stopContainer();

}
