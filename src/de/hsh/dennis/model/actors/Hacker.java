package de.hsh.dennis.model.actors;

import javafx.scene.image.Image;

public class Hacker extends Npc {

    private static final String pictureFileName = "bot_standard.png";
    private static final String dir = "de/hsh/dennis/resources/actors/Bot/";

    private Image skin_current;

    public Hacker(NPCEnums.Spawn spawnType) {
        super(pictureFileName, dir, spawnType, NPCEnums.NpcType.HACKER);
        skin_current = Config.Hacker.skin_standard;
    }
}
