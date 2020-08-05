package common.actor

/**
 * Checks inputs for movements
 */
class Movement {
    lateinit var keymap: Map<String, Direction>
    val holdDown = mutableMapOf(
            Direction.Up to false,
            Direction.Down to false,
            Direction.Left to false,
            Direction.Right to false)
    var velocity = 0.0

    /**
     * Checks if key is held down
     * @param keyName e.g. UP,DOWN,LEFT,RIGHT
     * @return a boolean if key was hold or not
     */
    private fun isHoldDown(keyName: String): Boolean {
        val d = keymap[keyName]
        return if (d != null) {
            holdDown[d]!!
        } else false
    }

    /**
     *
     * @param keyName Name of key pressed
     * @param b
     */
    private fun setKeyHoldDownIfPresent(keyName: String, b: Boolean) {
        val d = keymap[keyName]
        if (d != null) {
            holdDown[d] = b
        }
    }

    /**
     * Action to take when key is pressed
     * @param keyName Name of Key
     */
    fun onKeyPressed(keyName: String) {
        if (!this.isHoldDown(keyName) && keymap.containsKey(keyName)) {
            setKeyHoldDownIfPresent(keyName, true)
        }
    }

    /**
     * Action to take when key is released
     * @param keyName Keyname
     */
    fun onKeyReleased(keyName: String) = setKeyHoldDownIfPresent(keyName, false)


}
