package common.actor

import javafx.scene.canvas.Canvas
import java.util.*
import java.util.function.Consumer

/**
 * A simple level structure.
 *
 * @author Alex
 */
abstract class Level(gameCanvas: Canvas?) : Observable(),
        Observer,
        ILevel {
    private var backgroundImage = BackgroundImage()
    private var npcs = Collections.synchronizedList(
            ArrayList<Actor>())
    private var players = Collections.synchronizedList(
            LinkedList<ControlableActor>())
    private var levelElements = Collections.synchronizedList(
            ArrayList<LevelElement>())
    protected var collectables = Collections.synchronizedList(
            ArrayList<Collectable>())
        private set

    private fun addCollision() {
        levelElements.stream()
                .parallel()
                .forEach { levelElement: LevelElement? ->
                    players.forEach(
                            Consumer { pacMan: ControlableActor ->
                                pacMan.addCollidingActor(
                                        levelElement as Actor)
                            })
                }
    }

    private fun addCollectables() {
        collectables.stream().parallel().forEach { collectable: Collectable? ->
            players.forEach(
                    Consumer { player: ControlableActor ->
                        player.addCollidingActor(
                                collectable as Actor)
                    })
        }
    }

    protected fun addLevelElement(gameCanvas: Canvas,
                                  levelElement: LevelElement): Boolean {
        return if (CollisionCheck.isInBounds(levelElement, gameCanvas)) {
            addLevelElement(levelElement)
        } else false
    }

    @Synchronized
    override fun deleteObservers() {
        backgroundImage.deleteObservers()
        for (npc in npcs) {
            npc.deleteObservers()
        }
        for (player in players) {
            player.deleteObservers()
        }
        for (levelElement in levelElements) {
            levelElement.deleteObservers()
        }
        for (collectable in collectables) {
            collectable.deleteObservers()
        }
        super.deleteObservers()
    }

    @Synchronized
    override fun render(canvas: Canvas?, fps: Int) {
        if (canvas == null) {
            return
        }
        try {
            backgroundImage.draw(canvas)
            draw(npcs, canvas)
            draw(levelElements, canvas)
            draw(collectables, canvas)
            draw(players, canvas)
        } catch (cme: ConcurrentModificationException) {
            cme.printStackTrace()
        }
    }

    private fun <T : Actor> draw(list: List<T>,
                                  canvas: Canvas) {
        val size = list.size
        for (i in 0 until size) {
            list[i].draw(canvas)
        }
    }

    protected fun collidesWithLevelElement(a: Actor?): Boolean {
        for (levelElement in getLevelElements()) {
            if (levelElement.doesCollide(a)) {
                return true
            }
        }
        return false
    }

    protected fun collidesWithPlayer(d: Actor?): Boolean {
        for (controlableActor in getPlayers()) {
            if (controlableActor.doesCollide(d)) {
                return true
            }
        }
        return false
    }

    protected fun collidesWithCollectable(a: Actor?): Boolean {
        for (coll in collectables) {
            if (coll != null) {
                if (coll.doesCollide(a)) {
                    return true
                }
            }
        }
        return false
    }

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
        val list = Collections.synchronizedList(
                players)
        for (player in list) {
            player.addCollidingActor(c)
        }
        players = list
        return collectables.add(c)
    }

    protected fun addPlayer(player: ControlableActor): Boolean {
        return players.add(player)
    }

    protected open fun addLevelElement(levelElement: LevelElement): Boolean {
        return levelElements.add(levelElement)
    }

    open fun reset(gameCanvas: Canvas?) {
        backgroundImage = BackgroundImage()
        npcs = ArrayList()
        players = LinkedList()
        levelElements = ArrayList()
        collectables = ArrayList()
        createLevel(gameCanvas)
        addCollision()
        addCollectables()
    }

    protected abstract val isGameFinished: Boolean
    protected fun getPlayers(): List<ControlableActor> {
        return players
    }

    protected fun getLevelElements(): List<LevelElement> {
        return levelElements
    }

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
