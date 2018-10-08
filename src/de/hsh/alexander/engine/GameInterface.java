package de.hsh.alexander.engine;

public interface GameInterface {

    /**
     * Start the new game.
     */
    public void initGame( String[] args );

    public void initGame();

    /**
     * Render the next picture and update.
     */
    public void render();

    /**
     * Stop the new game.
     */
    public void stopGame();

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public boolean isRunning();

    public void setRunning( boolean value );

}
