package de.hsh.alexander.game.actor;

import java.util.HashMap;

public class PacMan extends Player {


    private static final double start_x = 100;
    private static final double start_y = 100;

    public PacMan( String pictureFileName, HashMap<String, Direction> keyMap ) {
        super( pictureFileName, start_x, start_y );
        this.setKeyMap( keyMap );
    }

    public PacMan( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

}
