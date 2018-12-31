package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.SkinConfig;

import static de.hsh.dennis.model.NpcLogic.SkinConfig.Package.skin_standard_path;

public class Package extends Npc {

    private static final int defaultSpeed = 1;

    public Package( NPCEnums.Spawn spawnType ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.PACKAGE );
        setCurrentImage(SkinConfig.Package.skin_standard);
    }

    public Package( NPCEnums.Spawn spawnType, double spawnTime ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, defaultSpeed );
        setCurrentImage(SkinConfig.Package.skin_standard);
    }

    public Package( NPCEnums.Spawn spawnType, double spawnTime, double speed ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, speed );
        setCurrentImage(SkinConfig.Package.skin_standard);
    }
}
