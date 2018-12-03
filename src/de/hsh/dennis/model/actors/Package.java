package de.hsh.dennis.model.actors;

import common.util.Path;

import java.io.FileNotFoundException;

public class Package extends Npc {

    private static final String pictureFileName = Path.getExecutionLocation() + "de/hsh/dennis/resources/actors/Bot/bot_standard.png";

    public Package(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE);
        setCurrentImage(Config.Package.skin_standard);
    }

    public Package(NPCEnums.Spawn spawnType, double spawnTime, double speed) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, speed);
        setCurrentImage(Config.Package.skin_standard);
    }
}
