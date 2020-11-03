package common.updates

import common.engine.GameContainer

interface GameUpdater {

    /**
     * @param arg An argument passed as a update
     * @param gameContainer Container of all games
     * @return Returns true, when the argument can be handled. Otherwise returns false.
     */
    fun update(arg: String, gameContainer: GameContainer): Boolean
}
