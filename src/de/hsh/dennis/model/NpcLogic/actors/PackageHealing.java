package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.NPCEnums;

import static de.hsh.dennis.model.NpcLogic.SkinConfig.Package.skin_healing_path;

public class PackageHealing extends Npc {

    public PackageHealing( NPCEnums.Spawn spawnType, double spawnTime, double speed ) {
        super( skin_healing_path, spawnType, NPCEnums.NpcType.PACKAGE, spawnTime, speed );
    }
}
