package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.SkinConfig;
import de.hsh.dennis.model.NpcLogic.NPCEnums;

import java.io.FileNotFoundException;

public class Hacker extends Npc {

    private static final String pictureFileName = "/de/hsh/dennis/resources/actors/hacker/hacker.png";
    private static final int defaultSpeed = 1;


    public Hacker(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER);
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }

    public Hacker(NPCEnums.Spawn spawnType, double spawnTime) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER, spawnTime, defaultSpeed);
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }

    public Hacker(NPCEnums.Spawn spawnType, double spawnTime, double speed) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER, spawnTime, speed);
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }
}
