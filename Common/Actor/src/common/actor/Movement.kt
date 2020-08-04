/**
 * @author Julian Sender
 */
package common.actor

import java.util.*

/**
 * Checks inputs for movements
 */
class Movement {
    private var keymap: HashMap<String, Direction?>? = null
    private val holdDown = initHoldDown()

    @kotlin.jvm.JvmField
    var velocity = 0.0

    /**
     * returns hashmap of movements
     * @return hashmap of movement-orders
     */
    private fun initHoldDown(): HashMap<Direction, Boolean> {
        val hashMap = HashMap<Direction, Boolean>()
        hashMap[Direction.Up] = java.lang.Boolean.FALSE
        hashMap[Direction.Down] = java.lang.Boolean.FALSE
        hashMap[Direction.Left] = java.lang.Boolean.FALSE
        hashMap[Direction.Right] = java.lang.Boolean.FALSE
        return hashMap
    }

    /**
     * sets Keys
     * @param keyName UP,DOWN,LEFT,RIGHT
     * @return name of the pressed key
     */
    private operator fun contains(keyName: String): Boolean {
        return keymap!!.containsKey(keyName)
    }

    /**
     * Checks if key is held down
     * @param keyName e.g. UP,DOWN,LEFT,RIGHT
     * @return a boolean if key was hold or not
     */
    private fun isHoldDown(keyName: String): Boolean {
        val d = keymap!![keyName]
        return if (d != null) {
            holdDown[d]!!
        } else false
    }

    /**
     *
     * @param direction instance of Direction
     * @return returns a direction
     */
    fun isHoldDown(direction: Direction?): Boolean {
        return holdDown[direction]!!
    }

    /**
     *
     * @param keyName Name of key pressed
     * @param b
     */
    private fun setKeyHoldDownIfPresent(keyName: String,
                                        b: Boolean) {
        val d = keymap!![keyName]
        if (d != null) {
            holdDown[d] = b
        }
    }

    /**
     * Action to take when key is pressed
     * @param keyName Name of Key
     */
    fun onKeyPressed(keyName: String) {
        if (!this.isHoldDown(keyName)) {
            if (this.contains(keyName)) {
                setKeyHoldDownIfPresent(keyName, true)
            }
        }
    }

    /**
     * Action to take when key is released
     * @param keyName Keyname
     */
    fun onKeyReleased(keyName: String) {
        setKeyHoldDownIfPresent(keyName, false)
    }

    /**
     * GETTERS AND SETTERS
     *
     */
    val directions: Set<Direction>
        get() = holdDown.keys

    fun setKeyMap(keymap: HashMap<String, Direction?>?) {
        this.keymap = keymap
    }
}
