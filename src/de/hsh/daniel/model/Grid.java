package de.hsh.daniel.model;

import common.util.Logger;

import java.util.ArrayList;

public class Grid {

    private static final int[]                   gridSize        = new int[2];
    private static final ArrayList<Integer>      gridValuesX     = new ArrayList<>();
    private static final ArrayList<Integer>      gridValuesY     = new ArrayList<>();


    public static int[] setupGridSize( int numberOfPairs) {
        int cards = numberOfPairs*2;

        return gridSize;
    }

    public static ArrayList<Integer> getGridValuesX() {
        int x = 10;
        for (int i = 0; i < gridSize[0] ; i++, x+=10) {
            gridValuesX.add(x);
        }
        return gridValuesX;
    }

    public static ArrayList<Integer> getGridValuesY () {
        int y = 20;
        for(int i = 0; i < gridSize[1]; i++, y+=20) {
            gridValuesY.add(y);
        }
        return gridValuesY;
    }


}
