package de.hsh.dennis.model.actors;

import common.util.Path;

import java.io.FileNotFoundException;

public class Hacker extends Npc {

    private static final String pictureFileName = Path.getExecutionLocation() + "de/hsh/dennis/resources/actors/Bot/bot_standard.png";


    public Hacker(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER);
        setCurrentImage(Config.Hacker.skin_standard);
    }

    public Hacker(NPCEnums.Spawn spawnType, double spawnTime) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER, spawnTime);
        setCurrentImage(Config.Hacker.skin_standard);
    }
}
