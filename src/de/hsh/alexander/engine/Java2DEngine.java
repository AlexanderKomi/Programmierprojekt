package de.hsh.alexander.engine;

import de.hsh.alexander.examples.ExampleFXGameContainer;

import java.util.Observable;

/**
 * A simple Java 2 engine.<br>
 * To use the engine, implement an GameContainerInterface.<br>
 * <p>
 * For a few examples, take a look at the classes in this package : de.hsh.alexander.engine.examples .
 *
 * @author Alexander Komischke
 * @see GameContainerInterface
 * <p>
 * Example classes:
 * @see ExampleFXGameContainer
 */
public class Java2DEngine extends Observable implements Runnable {


    private static final double                 UPDATE_CAP = 1.0 / 60.0;
    private              Thread                 gameThread;
    private              GameContainerInterface gameContainer;
    private              boolean                running    = false;
    private              int                    fps;

    /**
     * Must be called before start().
     * <p>
     * Initializes the AmirsGame Thread, with an instance of this class.
     */
    void init() {
        this.gameThread = new Thread( this, "Java 2D Engine" );
    }

    /**
     * Use configMainMenu once before start.
     * Only starts the gameContainer thread.
     */
    void start() {
        if ( !this.isRunning() ) {
            this.setRunning( true );
            if ( !this.gameThread.isInterrupted() ) {
                this.gameThread.start();
            }
        }
    }

    private boolean isRunning() {
        return this.running;
    }

    void setRunning( boolean running ) {
        this.running = running;
    }

    /**
     * Initializes the gameContainer, then starts the gameContainer-loop.
     * When gameContainer should be stopped, dispose gets called.
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
        double first_time;
        double last_time        = System.nanoTime() / 1000000000.0;
        double passed_time;
        double unprocessed_time = 0;
        //------
        double frame_time = 0;
        int    frames     = 0;


        boolean shouldRender;
        this.running = true;

        while ( this.running ) {
            shouldRender = false;

            // Needed for CPU idle time and rendering
            first_time = System.nanoTime() / 1000000000.0;
            passed_time = first_time - last_time;
            last_time = first_time;
            unprocessed_time += passed_time;

            frame_time += passed_time; // Needed for FPS calculation

            while ( unprocessed_time >= UPDATE_CAP ) {
                unprocessed_time -= UPDATE_CAP;

                if ( frame_time >= 1 ) {
                    frame_time = 0;
                    this.fps = frames;
                    frames = 0;
                }

                shouldRender = true;
            }
            frames += this.render( shouldRender );
        }

    }

    /**
     * Interrupts the engine thread.
     */
    private void stop() {
        if ( this.isRunning() ) {
            this.setRunning( false );
            this.gameThread.interrupt();
        }
    }

    /**
     * Calls CPU Idle and starts render process of the current gameContainer.<br>
     *
     * @param shouldRender
     *         Determines if a the update and render process of the GameContainer is used.<br>
     *
     * @return Returns 1 if a new frame is rendered.<br>
     * Returns 0 when cpu idles.
     */
    private int render( boolean shouldRender ) {
        if ( shouldRender ) {

            // START ------------------------ Render gameContainer ------------------------
            this.gameContainer.render();
            // STOP  ------------------------ Render gameContainer ------------------------
            return 1;
        }
        else {
            if ( !this.gameThread.isInterrupted() ) {
                try {
                    Thread.sleep( 1 ); // CPU Idle
                }
                catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    int getFps() {
        return this.fps;
    }

    void setGameContainer( FXGameContainer fxgame ) {
        this.gameContainer = fxgame;
    }

}
