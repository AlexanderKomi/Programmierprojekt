package de.hsh.daniel.model;

import de.hsh.daniel.model.Board;

public class BoardFactory {

    private static Board board = new Board();

    public static Board initBoard(int numberOfPairs) {
        board.setNumberOfPairs(numberOfPairs);
        board.imgToList();
        board.initCards(numberOfPairs);

        return board;
    }

    public static void setBoardPairs(int numberOfPairs) {
        board.setNumberOfPairs(numberOfPairs);
    }
    public static int getBoardPairs () { return board.getNumberOfPairs();}



}
