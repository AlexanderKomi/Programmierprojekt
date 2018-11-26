package de.hsh.dennis.model;

import javafx.scene.input.KeyCode;

//fixed key bindings for DDosDefender
public class KeyLayout {

    public static class Movement {

        //Jump
        public static final KeyCode UP = KeyCode.UP;
        public static final KeyCode W = KeyCode.W;

        //hit left
        public static final KeyCode LEFT = KeyCode.LEFT;
        public static final KeyCode A = KeyCode.A;

        //Crouch
        public static final KeyCode DOWN = KeyCode.DOWN;
        public static final KeyCode S = KeyCode.S;

        //hit right
        public static final KeyCode RIGHT = KeyCode.RIGHT;
        public static final KeyCode D = KeyCode.D;
    }

    //open break menu
    public static class Control {
        public static final KeyCode ESC = KeyCode.ESCAPE;
    }
}
