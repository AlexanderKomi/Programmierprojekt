package common.engine

/**
 * A simple Java 2 engine.<br></br>
 * To use the engine, implement an GameContainerInterface.<br></br>
 *
 *
 * For a few examples, take a look at the classes in this package : common.engine.examples .
 *
 * @author Alexander Komischke
 * @see AbleToBeRendered
 */
class Java2DEngine(private val gameContainer: AbleToBeRendered) : Runnable {

    private var gameThread: Thread = Thread(this, "Java 2D Engine")
    var isRunning = false
    var fps = 0

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
        if (isRunning) {
            isRunning = false
            gameThread.interrupt()
        }
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
     * Calls CPU Idle and starts render process of the current gameContainer.<br></br>
     *
     * @param shouldRender
     * Determines if a the update and render process of the GameContainer is used.<br></br>
     *
     * @return Returns 1 if a new frame is rendered.<br></br>
     * Returns 0 when cpu idles.
     */
    private fun render(shouldRender: Boolean): Byte {
        return if (shouldRender) {
            gameContainer.render(fps)
            1
        } else {
            if (!gameThread.isInterrupted) {
                Thread.sleep(1) // CPU Idle
            }
            0
        }
    }

    companion object {
        private const val UPDATE_CAP = 1.0f / 60.0f
    }

}
