package de.hsh.alexander.engine;

public interface GameContainerInterface {

    /**
     * Start the new game.
     * @param args Problem arguments
     */
    public void startContainer( String[] args );

    public void startContainer();

    /**
     * Render the next picture and update.
     */
    public void render();

    /**
     * Stops the engine container.
     */
    public void stopContainer();

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public boolean isRunning();

    public void setRunning( boolean value );

}
