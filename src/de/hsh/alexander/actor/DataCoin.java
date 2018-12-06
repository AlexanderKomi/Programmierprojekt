package de.hsh.alexander.actor;

import common.actor.Collectable;

import java.io.FileNotFoundException;

public class DataCoin extends Collectable {

    public static final  String data_coin_dir = ResourcePaths.Actor.Collectables.DataCoin.directory;
    private static final int    default_delay = 10;

    private static final String[] pictureFilePaths = new String[] {
            data_coin_dir + "data_coin_00.png",
            data_coin_dir + "data_coin_01.png",
            data_coin_dir + "data_coin_02.png",
            data_coin_dir + "data_coin_03.png",
            data_coin_dir + "data_coin_04.png",
            data_coin_dir + "data_coin_05.png",
            data_coin_dir + "data_coin_06.png",
            data_coin_dir + "data_coin_07.png",
            data_coin_dir + "data_coin_08.png",
            data_coin_dir + "data_coin_09.png",
            data_coin_dir + "data_coin_10.png",
            data_coin_dir + "data_coin_11.png"
    };

    public DataCoin( double x, double y ) throws FileNotFoundException {
        super( x, y, default_delay, pictureFilePaths );
    }
}
