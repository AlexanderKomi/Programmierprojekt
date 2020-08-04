package common.engine.components.game

import java.util.*

class GameEntryPoints : ArrayList<GameEntryPoint> {

    var activeGame: String? = null
    val names: List<String>
        get() = this.map{ game: GameEntryPoint -> game.name }

    constructor()
    constructor(vararg gameEntryPoints: GameEntryPoint) {
        Collections.addAll(this, *gameEntryPoints)
    }

    operator fun get(name: String): GameEntryPoint {
        return find { it.name == name} ?: throw IllegalArgumentException("GameEntryPoint not found")
    }

    override operator fun contains(element: GameEntryPoint): Boolean = contains(element.name)
             operator fun contains(gameName: String): Boolean = this.find { it.name == gameName } != null

    fun render(fps: Int) {
        if (activeGame != null) {
            this[activeGame!!].render(fps)
        } else {
            this.parallelStream().forEach {
                game -> game.render(fps)
            }
        }
    }

}
