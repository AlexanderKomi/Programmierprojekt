package de.hsh.daniel.model;


import javafx.scene.canvas.GraphicsContext;

public class Grid {

    public static void drawGrid(GraphicsContext gc, Board board, double gridW, double gridH, double imgSize, int numberOfPairs) {
        int xStart;
        int yStart = 10;
        int spacing ;
        int imgCount = 0;


        if (numberOfPairs == 6) {
            xStart = 300;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    gc.drawImage(board.getCardList().get(imgCount).getImage(), xStart, yStart, imgSize, imgSize);
                }
                yStart += imgSize + 20;
                xStart = 300;
            }

        } else if( numberOfPairs == 8) {
            xStart = 200;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    gc.drawImage(board.getCardList().get(imgCount).getImage(), xStart, yStart, imgSize, imgSize);
                }
                yStart += imgSize + 20;
                xStart = 200;

            }
            } else {
            xStart = 30;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    gc.drawImage(board.getCardList().get(imgCount).getImage(), xStart, yStart, imgSize, imgSize);
                }
                yStart += imgSize + 20;
                xStart = 30;
            }

        }
    }

}
