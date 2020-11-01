package common.engine

interface GameContainerInterface {


    /**
     * Gets called every time a new frame is rendered.
     * Use this to update every frame.
     * @param fps
     */
    fun render(fps: Int)

}
