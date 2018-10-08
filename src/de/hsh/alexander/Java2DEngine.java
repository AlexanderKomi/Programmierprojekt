package de.hsh.alexander;

import de.hsh.alexander.engine.FXGame;
import de.hsh.alexander.engine.GameInterface;

/**
 * @author Alexander Komischke
 */
public class Java2DEngine implements Runnable {

    private final double        UPDATE_CAP = 1.0 / 60.0;
    private       Thread        gameThread;
    private       GameInterface game;
    private       boolean       running    = false;
    private       int           fps;


    public void init() {
        this.gameThread = new Thread( this, "Java 2D Engine" );
    }

    /**
     * Only starts the game thread.
     */
    public void start() {
        this.gameThread.start();
    }

    /**
     * Joins game thread to current main thread.
     */
    public void stop() {
        this.setRunning( false );
    }

    /**
     * Initializes the game, then starts the game-loop.
     * When game should be stopped, dispose gets called.
     *
     * @see Thread
     * @see Runnable
     */
    @Override
    public void run() {
        game_loop();
        stop();
    }

    /**
     * Updates the frames and determines, when a picture is rendered.
     */
    private void game_loop() {

        //------
        double first_time       = 0;
        double last_time        = System.nanoTime() / 1000000000.0;
        double passed_time      = 0;
        double unprocessed_time = 0;
        //------
        double frame_time = 0;
        int    frames     = 0;


        boolean render = false;
        this.running = true;

        while ( this.running ) {
            render = false;

            first_time = System.nanoTime() / 1000000000.0;
            passed_time = first_time - last_time;
            last_time = first_time;
            unprocessed_time += passed_time; // Needed for CPU idle time and rendering
            frame_time += passed_time; // Needed for FPS calculation

            while ( unprocessed_time >= UPDATE_CAP ) {
                unprocessed_time -= UPDATE_CAP;

                if ( frame_time >= 1 ) {
                    frame_time = 0;
                    this.fps = frames;
                    frames = 0;
                    System.out.println( "FPS : " + fps );
                }

                render = true;
            }

            if ( render ) {
                frames++; // Counts frames rendered

                // START ------------------------ Render game ------------------------

                this.game.render();

                // STOP  ------------------------ Render game ------------------------
            }
            else {
                try {
                    Thread.sleep( 1 ); // CPU Idle
                }
                catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }

        }

    }


    // --------------------- GETTER & SETTER etc...  ---------------------

    public boolean isRunning() {
        return this.running;
    }

    public void setRunning( boolean running ) {
        this.running = running;
    }

    public double getUPDATE_CAP() {
        return UPDATE_CAP;
    }

    public int getFps() {
        return this.fps;
    }

    public void setGame( FXGame fxgame ) {
        this.game = fxgame;
    }

}
