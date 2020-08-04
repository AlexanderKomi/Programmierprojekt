package de.hsh.dennis.model

import javafx.scene.input.KeyCode

//fixed key bindings for DDosDefender
class KeyLayout {
    class Movement {
        object Custom {
            //UP
            var UP = KeyCode.UP
            var UP_ALT = KeyCode.W

            //hit left
            var LEFT = KeyCode.LEFT
            var LEFT_ALT = KeyCode.A

            //Crouch
            var DOWN = KeyCode.DOWN
            var DOWN_ALT = KeyCode.S

            //hit right
            var RIGHT = KeyCode.RIGHT
            var RIGHT_ALT = KeyCode.D
        }

        internal object Standard {
            //UP
            val UP = KeyCode.UP
            val UP_ALT = KeyCode.W

            //hit left
            val LEFT = KeyCode.LEFT
            val LEFT_ALT = KeyCode.A

            //Crouch
            val DOWN = KeyCode.DOWN
            val DOWN_ALT = KeyCode.S

            //hit right
            val RIGHT = KeyCode.RIGHT
            val RIGHT_ALT = KeyCode.D
        }

        fun movementToDefault() {
            Custom.UP = Standard.UP
            Custom.UP_ALT = Standard.UP_ALT
            Custom.LEFT = Standard.LEFT
            Custom.LEFT_ALT = Standard.LEFT_ALT
            Custom.DOWN = Standard.DOWN
            Custom.DOWN_ALT = Standard.DOWN_ALT
            Custom.RIGHT = Standard.RIGHT
            Custom.RIGHT_ALT = Standard.RIGHT_ALT
        }
    }

    //open break menu
    object Control {
        @JvmField
        val BREAK = KeyCode.ESCAPE
        @JvmField
        val BREAK_ALT = KeyCode.P
    }
}
