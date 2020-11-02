package common.actor

import common.util.Logger

/**
 * Actor is a drawable with a few added features.
 * A commonly used feature is collision with other actors or the movement of an actor, when it should be drawn.
 */
abstract class Actor : Drawable {
    val movement = Movement()
    private var collisionActors = mutableListOf<Actor>()

    open var speed: Double
        get() = movement.velocity
        set(speed) { movement.velocity = speed }

    /**
     * Many overloaded constructors following up now...
     * @param pictureFileName Filename of picture for actor
     */
    constructor(pictureFileName: String,
                x: Double = 0.0,
                y: Double = 0.0,
                delay: Int = 0,
                scale: Double = 1.0) : super(pictureFileName, x, y, delay, scale)

    constructor(pictureFilePaths: Array<String>,
                x: Double = 0.0,
                y: Double = 0.0,
                delay: Int = 0,
                scale: Double = 1.0) : super(pictureFilePaths.toList(), x, y, delay, scale)

    constructor(pictureFilePaths: List<String>,
                x: Double = 0.0,
                y: Double = 0.0,
                delay: Int = 0,
                scale: Double = 1.0) : super(pictureFilePaths, x, y, delay, scale)

    /**
     * Returning position based on collisioncheck
     * @param current_pos
     * The current position of the Drawable
     * @param new_pos
     * The next position of the Drawable
     *
     * @return
     */
    override fun beforeDrawing(current_pos: DoubleArray, new_pos: DoubleArray): DoubleArray =
            if (this.doesCollide()) current_pos else new_pos

    /**
     * Checks actor and a list of collisionActors for collision
     * @return returns true, if collided, else false
     */
    private fun doesCollide(): Boolean {
        val list = arrayListOf<Actor>()
        list.addAll(collisionActors)
        return list.any { doesCollide(it) }
    }

    /**
     *
     * @param other instance of actor
     * @return returns if collisioned or not
     */
    fun doesCollide(other: Actor): Boolean =
            if (CollisionCheck.doesCollide(this, other as Drawable) ||
                CollisionCheck.doesCollide(other, this)) {
                collisionModifier(other)
            } else false

    /**
     * returns if two objects are in bounds of each other by checking coordinates
     * @param canvas_width width
     * @param canvas_height height
     * @return returns if
     */
    fun isInBounds(canvas_width: Double, canvas_height: Double): Boolean =
            CollisionCheck.isInBounds(this, canvas_width, canvas_height)

    /**
     * Please override to provide a modifier for if any is applicable.
     * @param other the Actor
     * @return always true
     */
    protected open fun collisionModifier(other: Actor): Boolean = true

    fun removeCollisionActor(collectable: Collectable) {
        val l = collisionActors
        if (l.remove(collectable)) {
            onRemove(collectable)
        } else {
            Logger.log("------>" + this.javaClass + " FATAL ERROR : Can not delete: " + collectable)
        }
        collisionActors.remove(collectable)
    }

    protected open fun onRemove(collectable: Collectable) {}

    /**
     * adds a colliding actor
     * @param a Actor
     */
    fun addCollidingActor(a: Actor) {
        if (!collisionActors.contains(a)) {
            collisionActors.add(a)
        }
    }
}
