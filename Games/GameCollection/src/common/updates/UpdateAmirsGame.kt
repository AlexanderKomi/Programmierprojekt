package common.updates

import common.GameContainer
import common.util.Logger
import de.hsh.amir.AmirEntryPoint

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
object UpdateAmirsGame {
    fun update(arg: String,
               gameContainer: GameContainer) {
        Updater.updateElse(arg, gameContainer)
    }
}
