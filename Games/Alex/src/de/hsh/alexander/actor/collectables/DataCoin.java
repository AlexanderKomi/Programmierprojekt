package de.hsh.alexander.actor.collectables;

import common.actor.Collectable;
import de.hsh.alexander.actor.ResourcePaths;

import java.util.concurrent.ThreadLocalRandom;

public class DataCoin extends Collectable {

    private static final int    default_delay = 10;
    private static final double default_scale = 1.2;

    public DataCoin( double x, double y ) {
        super(x, y,
               default_delay + ThreadLocalRandom.current().nextInt( 0, 10 ),
              default_scale,
              ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths );
    }

    public DataCoin( double x, double y, double scale ) {
        super(x, y,
               default_delay + ThreadLocalRandom.current().nextInt( 0, 10 ),
              scale,
              ResourcePaths.Actor.Collectables.DataCoin.pictureFilePaths );
    }


}
