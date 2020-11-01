package common.engine

import java.util.*
import kotlin.coroutines.CoroutineContext

/**
 * A simple Java 2 engine.<br></br>
 * To use the engine, implement an GameContainerInterface.<br></br>
 *
 *
 * For a few examples, take a look at the classes in this package : common.engine.examples .
 *
 * @author Alexander Komischke
 * @see GameContainerInterface
 */
class Java2DEngine(private val gameContainer: GameContainerInterface) :
        Observable(),
        Runnable {

    /**
     * Use configMainMenu once before start.
     * Only starts the gameContainer thread.
     */
    fun start() {
        if (!isRunning) {
            isRunning = true
            if (!gameThread.isInterrupted) {
                gameThread.start()
            }
        }
    }

    fun shutdown() {
        isRunning = false
    }

    /**
     * Initializes the gameContainer, then starts the gameContainer-loop.
     * When gameContainer should be stopped, dispose gets called.
     *
     * @see Thread
     *
     * @see Runnable
     */
    override fun run() {
        gameLoop()
        stop()
    }

    /**
     * Updates the frames and determines, when a full_picture is rendered.
     */
    private fun gameLoop() {

        //------
        var firstTime: Float
        var lastTime = System.nanoTime() / 1000000000.0f
        var passedTime: Float
        var unprocessedTime = 0f
        //------
        var frameTime = 0f
        var frames = 0
        var shouldRender: Boolean
        isRunning = true
        while (isRunning) {
            shouldRender = false

            // Needed for CPU idle time and rendering
            firstTime = System.nanoTime() / 1000000000.0f
            passedTime = firstTime - lastTime
            lastTime = firstTime
            unprocessedTime += passedTime
            frameTime += passedTime // Needed for FPS calculation
            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP
                if (frameTime >= 1) {
                    frameTime = 0f
                    fps = frames
                    frames = 0
                }
                shouldRender = true
            }
            frames += render(shouldRender).toInt()
        }
    }

    /**
     * Interrupts the engine thread.
     */
    private fun stop() {
        if (isRunning) {
            isRunning = false
            gameThread.interrupt()
        }
    }

    /**
     * Calls CPU Idle and starts render process of the current gameContainer.<br></br>
     *
     * @param shouldRender
     * Determines if a the update and render process of the GameContainer is used.<br></br>
     *
     * @return Returns 1 if a new frame is rendered.<br></br>
     * Returns 0 when cpu idles.
     */
    private fun render(shouldRender: Boolean): Byte {
        if (shouldRender) {
            // START ------------------------ Render gameContainer ------------------------
            gameContainer.render(fps)
            // STOP  ------------------------ Render gameContainer ------------------------
            return 1
        } else {
            if (!gameThread.isInterrupted) {
                try {
                    Thread.sleep(1) // CPU Idle
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        return 0
    }

    companion object {
        private const val UPDATE_CAP = 1.0f / 60.0f
        private lateinit var gameThread: Thread
        private var isRunning = false
        var fps = 0
    }

    /**
     * Must be called before start().
     *
     *
     * Initializes the Game Thread, with an instance of this class.
     */
    init {
        gameThread = Thread(this, "Java 2D Engine")
    }
}
