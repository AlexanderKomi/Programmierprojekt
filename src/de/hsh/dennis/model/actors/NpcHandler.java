package de.hsh.dennis.model.actors;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NpcHandler {

    private int npcLimit = 100;

    private static Canvas canvas;
    private static final List<Npc> npcList = Collections.synchronizedList(new ArrayList());
    private List<Npc> npcsToRemove = Collections.synchronizedList(new ArrayList());


    public NpcHandler(Canvas canvas) {
        this.canvas = canvas;
    }

    public void addNpc(Npc npc) {
        if (npcList.size() <= npcLimit) {
            getNpcList().add(npc);
        }
    }

    public void removeNpc(Npc npc) {
        getNpcList().remove(npc);
    }

    public List<Npc> getNpcList() {

        return npcList;
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
            for (Npc npc : npcsToRemove) {
                npcList.remove(npc);
            }
            npcsToRemove.clear();
        }
    }
}
