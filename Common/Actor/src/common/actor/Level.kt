package common.actor

import javafx.scene.canvas.Canvas
import java.util.*

/**
 * A simple level structure.
 *
 * @author Alex
 */
abstract class Level(gameCanvas: Canvas) : Observable(),
        Observer,
        ILevel {
    private var backgroundImage = BackgroundImage()
    private var npcs = mutableListOf<Actor>()
    var players = mutableListOf<ControlableActor>()
    private var levelElements = mutableListOf<LevelElement>()
    protected var collectables = mutableListOf<Collectable>()
        private set

    private fun addCollision() {
        levelElements.forEach { levelElement: LevelElement ->
            players.forEach { pacMan: ControlableActor ->
                pacMan.addCollidingActor(levelElement as Actor)
            }
        }
    }

    private fun addCollectables() =
            collectables.forEach { collectable: Collectable ->
                players.forEach { player: ControlableActor ->
                    player.addCollidingActor(collectable as Actor)
                }
            }

    protected fun addLevelElement(gameCanvas: Canvas,
                                  levelElement: LevelElement): Boolean =
            if (CollisionCheck.isInBounds(levelElement, gameCanvas)) {
                addLevelElement(levelElement)
            } else false

    @Synchronized
    override fun deleteObservers() {
        backgroundImage.deleteObservers()
        npcs.forEach { npc -> npc.deleteObservers() }
        players.forEach { player -> player.deleteObservers() }
        levelElements.forEach { levelElement -> levelElement.deleteObservers() }
        collectables.forEach { collectable -> collectable.deleteObservers() }
        super.deleteObservers()
    }

    @Synchronized
    override fun render(canvas: Canvas, fps: Int) {
        backgroundImage.draw(canvas)
        draw(npcs, canvas)
        draw(levelElements, canvas)
        draw(collectables, canvas)
        draw(players, canvas)
    }

    @Synchronized
    private fun <T : Actor> draw(list: List<T>, canvas: Canvas) {
        var size = list.size
        for (i in 0 until size) {
            val t: T? = list[i]
            t?.draw(canvas)
            size = list.size
        }
    }

    protected fun collidesWithLevelElement(a: Actor): Boolean = levelElements.any { it.doesCollide(a) }

    protected fun collidesWithPlayer(d: Actor): Boolean = players.any { it.doesCollide(d) }

    protected fun collidesWithCollectable(a: Actor): Boolean = collectables.any { it.doesCollide(a) }

    protected fun collected(collectable: Collectable): Boolean {
        collectable.deleteObservers()
        val list = Collections.synchronizedList(
                players)
        synchronized(list) {
            for (player in list) {
                player.removeCollisionActor(collectable)
            }
        }
        val result = collectables.remove(collectable)
        collectables.retainAll(collectables)
        return result
    }

    protected open fun addCollectable(c: Collectable): Boolean {
        c.addObserver(this)
        val list = Collections.synchronizedList(players)
        for (player in list) {
            player.addCollidingActor(c)
        }
        players = list
        return collectables.add(c)
    }

    protected fun addPlayer(player: ControlableActor): Boolean = players.add(player)

    protected open fun addLevelElement(levelElement: LevelElement): Boolean = levelElements.add(levelElement)

    private fun reset(gameCanvas: Canvas) {
        backgroundImage = BackgroundImage()
        npcs = mutableListOf()
        players = mutableListOf()
        levelElements = mutableListOf()
        collectables = mutableListOf()
        createLevel(gameCanvas)
        addCollision()
        addCollectables()
    }

    open fun resetAndThan(gameCanvas: Canvas, thanDo: (Canvas) -> Unit) {
        this.reset(gameCanvas)
        thanDo(gameCanvas)
    }

    protected abstract val isGameFinished: Boolean

    protected fun setBackgroundImage(filepath: String,
                                     width: Int,
                                     height: Int) {
        backgroundImage.currentImage = TextureBuffer.loadImage(filepath)
        backgroundImage.width = width.toDouble()
        backgroundImage.height = height.toDouble()
    }

    init {
        reset(gameCanvas)
    }
}
