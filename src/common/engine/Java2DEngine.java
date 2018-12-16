package common.engine;

import java.util.Observable;

/**
 * A simple Java 2 engine.<br>
 * To use the engine, implement an GameContainerInterface.<br>
 * <p>
 * For a few examples, take a look at the classes in this package : common.engine.examples .
 *
 * @author Alexander Komischke
 * @see GameContainerInterface
 */
public final class Java2DEngine extends Observable implements Runnable {


    private static final double                 UPDATE_CAP = 1.0 / 60.0;
    private static       Thread                 gameThread;
    private final        GameContainerInterface gameContainer;
    private static       boolean                running    = false;
    static               int                    fps;

    /**
     * Must be called before start().
     * <p>
     * Initializes the Game Thread, with an instance of this class.
     */
    public Java2DEngine( GameContainerInterface container ) {
        this.gameContainer = container;
        gameThread = new Thread( this, "Java 2D Engine" );
    }

    /**
     * Use configMainMenu once before start.
     * Only starts the gameContainer thread.
     */
    final void start() {
        if ( !this.isRunning() ) {
            running = true;
            if ( !gameThread.isInterrupted() ) {
                gameThread.start();
            }
        }
    }

    private boolean isRunning() {
        return running;
    }

    void shutdown() {
        running = false;
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
        running = true;

        while ( running ) {
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
                    fps = frames;
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
        if ( running ) {
            running = (false);
            gameThread.interrupt();
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
    private byte render( final boolean shouldRender ) {
        if ( shouldRender ) {

            // START ------------------------ Render gameContainer ------------------------
            this.gameContainer.render( fps );
            // STOP  ------------------------ Render gameContainer ------------------------
            return 1;
        }
        else {
            if ( !gameThread.isInterrupted() ) {
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

}
