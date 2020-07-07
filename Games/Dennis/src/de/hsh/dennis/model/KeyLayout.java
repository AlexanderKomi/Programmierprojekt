package de.hsh.dennis.model;

import javafx.scene.input.KeyCode;

//fixed key bindings for DDosDefender
public class KeyLayout {

    public static class Movement {

        public static class Custom {
            //UP
            public static KeyCode UP = KeyCode.UP;
            public static KeyCode UP_ALT = KeyCode.W;

            //hit left
            public static KeyCode LEFT = KeyCode.LEFT;
            public static KeyCode LEFT_ALT = KeyCode.A;

            //Crouch
            public static KeyCode DOWN = KeyCode.DOWN;
            public static KeyCode DOWN_ALT = KeyCode.S;

            //hit right
            public static KeyCode RIGHT = KeyCode.RIGHT;
            public static KeyCode RIGHT_ALT = KeyCode.D;
        }

        static class Standard {
            //UP
            static final KeyCode UP = KeyCode.UP;
            static final KeyCode UP_ALT = KeyCode.W;

            //hit left
            static final KeyCode LEFT = KeyCode.LEFT;
            static final KeyCode LEFT_ALT = KeyCode.A;

            //Crouch
            static final KeyCode DOWN = KeyCode.DOWN;
            static final KeyCode DOWN_ALT = KeyCode.S;

            //hit right
            static final KeyCode RIGHT = KeyCode.RIGHT;
            static final KeyCode RIGHT_ALT = KeyCode.D;
        }

        public void movementToDefault() {
            Custom.UP = Standard.UP;
            Custom.UP_ALT = Standard.UP_ALT;
            Custom.LEFT = Standard.LEFT;
            Custom.LEFT_ALT = Standard.LEFT_ALT;
            Custom.DOWN = Standard.DOWN;
            Custom.DOWN_ALT = Standard.DOWN_ALT;
            Custom.RIGHT = Standard.RIGHT;
            Custom.RIGHT_ALT = Standard.RIGHT_ALT;

        }
    }

    //open break menu
    public static class Control {
        public static final KeyCode BREAK = KeyCode.ESCAPE;
        public static final KeyCode BREAK_ALT = KeyCode.P;
    }
}
