package de.hsh.dennis.model.actors;

import common.util.Logger;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NpcHandler {

    private int npcLimit = 100;

    private static Canvas canvas;
    private static final List<Npc> npcList = Collections.synchronizedList(new ArrayList());
    private final List<Npc> npcsToRemove = Collections.synchronizedList(new ArrayList());

    private int scoreChange = 0;
    private int healthChange = 0;

    private int pointValue = 10;


    public NpcHandler(Canvas canvas) {
        this.canvas = canvas;
    }

    public void addNpc(Npc npc) {
        if (npcList.size() <= npcLimit) {
            getNpcList().add(npc);
        }
    }

    private void removeNpcs() {
        synchronized (npcList) {
            for (Npc npc : npcsToRemove) {
                switch (npc.getNpcType()) {
                    case PACKAGE:
                        scoreChange += pointValue;
                        healthChange += pointValue;
                        break;
                    case BOT:
                        healthChange -= pointValue;
                        break;
                    case HACKER:
                        scoreChange += pointValue;
                        break;
                    default:
                        Logger.log(this.getClass() + "Switching in removeNpcs : default.");
                }
                npcList.remove(npc);
            }
            npcsToRemove.clear();
        }

    }

    public List<Npc> getNpcList() {

        return npcList;
    }

    public int getScoreChange() {
        int temp = scoreChange;
        scoreChange = 0;
        return temp;
    }

    public int getHealthChange() {
        int temp = healthChange;
        healthChange = 0;
        return temp;
    }

    public static void drawNpcs() {
        synchronized (npcList) {
            for (Npc npc : npcList) {
                canvas.getGraphicsContext2D().drawImage(npc.getImage(), npc.getPosX(), npc.getPosY());
            }
        }
    }

    public void move() {
        synchronized (npcList) {
            for (Npc npc : npcList) {
                if (npc.getPosX() >= ((canvas.getWidth() / 2.0) - (npc.getImage().getWidth())) && (npc.getPosX() <= (canvas.getWidth() / 2.0))) {
                    npcsToRemove.add(npc);
                } else {
                    npc.move();
                }
            }
            removeNpcs();
        }
    }
}
