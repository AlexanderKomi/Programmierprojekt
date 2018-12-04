package de.hsh.kevin.logic.myActor;

import java.util.ArrayList;
import java.util.List;

import de.hsh.kevin.logic.Leben;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;

public class PaketManager {

    private ArrayList<Paket> pakete;
    private double width;
    private double height;
    private Score score;
    private Leben leben;

    public PaketManager(double width, double height, Score score, Leben leben) {
        pakete = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.score = score;
        this.leben = leben;
    }

    public List<Paket> getPakete() {
        return pakete;
    }

    /**
     * Erstellt zu 50:50 ein Good oder BadPaket
     */
    public void createNewPaket() {
        double rand = Math.random();
        createNewPaket(0.5);
    }

    /**
     * Erstellt zu angegebener Chance ein GoodPaket, sonst ein BadPaket
     * 
     * @param chance
     */
    public void createNewPaket(double chance) {
        double rand = Math.random();
        if (rand < chance) {
            createGoodPaket();
        } else {
            createBadPaket();
        }
    }

    public void createBadPaket() {
        Paket p = PaketFactory.getBadPaket(1, 1);
        setOnFreeLocation(p);
        pakete.add(p);
    }

    public void createGoodPaket() {
        Paket p = PaketFactory.getGoodPaket(1, 1);
        setOnFreeLocation(p);
        pakete.add(p);
    }

    public void move() {
        ArrayList<Paket> toRemove = new ArrayList<>();
        
        pakete.stream().parallel().forEach(paket -> {
            if (paket.getPos()[1] > height) {
                if (paket.getPaketTyp() == enmPaketTyp.good) {
                    score.increase();
                } else {
                    leben.decrease();
                }
                toRemove.add(paket);
            } else {
                paket.move();
            }
        });
        
        for(int i = 0; i < toRemove.size(); i++) {
            pakete.remove(toRemove.get(i));
        }
    }

    public void draw(Canvas canvas) {
        for (Paket p : pakete) {
            p.draw(canvas, 0, 0);
        }
    }

    //TODO verursacht flackern
    private void setOnFreeLocation(Paket paket) {
        double randX = -1;
        boolean free = false;
        boolean isCollided = false;

        do {
            // sucht randX sodass Paket ganz im Bild ist
            do {
                randX = Math.random() * width;
            } while (randX + paket.getWidth() > width);

            paket.setPos(randX, 1);

            // check ob Platz belegt ist
            isCollided = false;

            for (Paket p : pakete) {
                if (p.doesCollide(paket)) {
                    isCollided = true;
                    break;
                }
            }

            if (!isCollided) {
                free = true;
            }
        } while (!free);
    }

    public void remove(Paket p) {
        pakete.remove(p);
    }
}
