package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.SkinConfig;
import de.hsh.dennis.model.NpcLogic.NPCEnums;

import java.io.FileNotFoundException;

public class Package extends Npc {

    private static final String pictureFileName = "/de/hsh/dennis/resources/actors/package/package.png";
    private static final int defaultSpeed = 1;

    public Package(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE);
        setCurrentImage(SkinConfig.Package.skin_standard);
    }

    public Package(NPCEnums.Spawn spawnType, double spawnTime) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, defaultSpeed);
        setCurrentImage(SkinConfig.Package.skin_standard);
    }

    public Package(NPCEnums.Spawn spawnType, double spawnTime, double speed) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, speed);
        setCurrentImage(SkinConfig.Package.skin_standard);
    }
}
