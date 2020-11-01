package common.engine

interface GameContainer {
    /**
     * Start the new game. Must be called from the class launching the application!
     *
     * @param args
     * Program arguments
     */
    fun startContainer(args: Array<String>)
    /**
     * Stops the engine container. Kills every process inside the container.
     */
    fun stopContainer( func : () -> Unit = {})
}
