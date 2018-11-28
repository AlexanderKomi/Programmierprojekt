package de.hsh.dennis.model.actors;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;

public class Spawner {

    private Canvas canvas;
    private ArrayList<Npc> npcs = new ArrayList();
    private ArrayList<Npc> npcsToRemove = new ArrayList();

    public Spawner(Canvas canvas) {
        this.canvas = canvas;
    }

    public void addNpc(Npc npc) {
        if (npcs.size() <= 100) {  //TODO: Durch Zeitliche Begrenzung ablösen! oder TODO2! (sinnvoller)
            getNpcs().add(npc);
        }
    }

    public void removeNpc(Npc npc) {
        getNpcs().remove(npc);
    }

    public ArrayList<Npc> getNpcs() {

        return npcs;
    }

    public void move() {
        for (Npc npc : npcs) {
            if (npc.getSpawnType() == NPCEnums.Spawn.RIGHT && npc.getPosX() <= (canvas.getWidth() / 2.0)) {
                npcsToRemove.add(npc);
            } else if (npc.getSpawnType() == NPCEnums.Spawn.LEFT && npc.getPosX() >= (canvas.getWidth() / 2.0) - npc.getImage().getWidth()) {
                npcsToRemove.add(npc);
            } else {
                npc.move();
            }
        }
        for (Npc npc : npcsToRemove) {
            removeNpc(npc);                 //TODO: TODO2: Der Render Thread und das Iterieren dieser Methode können sich in die Quere kommen: "Exception in thread "Java 2D Engine" java.util.ConcurrentModificationException"
        }
        npcsToRemove.clear();
    }
}
