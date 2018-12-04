package de.hsh.dennis.model.NpcLogic.actors;

import common.util.Path;
import de.hsh.dennis.model.NpcLogic.Config;
import de.hsh.dennis.model.NpcLogic.NPCEnums;

import java.io.FileNotFoundException;

public class Hacker extends Npc {

    private static final String pictureFileName = Path.getExecutionLocation() + "de/hsh/dennis/resources/actors/Bot/bot_standard.png";


    public Hacker(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER);
        setCurrentImage(Config.Hacker.skin_standard);
    }

    public Hacker(NPCEnums.Spawn spawnType, double spawnTime, double speed) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.HACKER, spawnTime, speed);
        setCurrentImage(Config.Hacker.skin_standard);
    }
}
