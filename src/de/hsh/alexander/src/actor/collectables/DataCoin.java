package de.hsh.alexander.src.actor.collectables;

import common.actor.Collectable;

import java.util.concurrent.ThreadLocalRandom;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths;

public class DataCoin extends Collectable {

    private static final int    default_delay = 10;
    private static final double default_scale = 1.2;

    public DataCoin( double x, double y ) {
        super( x, y,
               default_delay + ThreadLocalRandom.current().nextInt( 0, 10 ),
               default_scale,
               pictureFilePaths );
    }
}
