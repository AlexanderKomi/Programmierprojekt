package de.hsh.alexander.game.actor;

public class PacMan extends Actor {


    private static final double start_x = 100;
    private static final double start_y = 100;

    public PacMan( String pictureFileName ) {
        super( pictureFileName, start_x, start_y );
    }

    public PacMan( String pictureFileName, double x, double y ) {
        super( pictureFileName, x, y );
    }

}
