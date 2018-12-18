package de.hsh.daniel.model;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Grid {

    public static void drawGrid(Canvas canvas, Board board, double gridW, double gridH, double imgSize, int numberOfPairs) {
        int xStart;
        int yStart = 10;
        int spacing ;
        int imgCount = 0;


        if (numberOfPairs == 6) {
            xStart = 300;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    Card i = board.getCardList().get(imgCount);
                    i.setPos(xStart, yStart);
                    i.draw(canvas);
                }
                yStart += imgSize + 20;
                xStart = 300;
            }

        } else if( numberOfPairs == 8) {
            xStart = 200;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    Card i = board.getCardList().get(imgCount);
                    //gc.drawImage(i, xStart, yStart, imgSize, imgSize);
                    i.setPos(xStart, yStart);
                    i.draw(canvas);
                }
                yStart += imgSize + 20;
                xStart = 200;

            }
            } else {
            xStart = 30;
            spacing = 40;
            for (int j = 0; j < gridH; j++) {
                for (int k = 0; k < gridW; k++, xStart += (imgSize + spacing), imgCount++) {
                    Card i = board.getCardList().get(imgCount);
                    //gc.drawImage(i, xStart, yStart, imgSize, imgSize);
                    i.setPos(xStart, yStart);
                    i.draw(canvas);
                }
                yStart += imgSize + 20;
                xStart = 30;
            }

        }
    }

}
