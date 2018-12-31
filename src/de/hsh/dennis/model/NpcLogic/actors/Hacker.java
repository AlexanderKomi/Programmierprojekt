package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.NPCEnums;

import static de.hsh.dennis.model.NpcLogic.SkinConfig.Hacker.skin_standard_path;

public class Hacker extends Npc {

    public Hacker( NPCEnums.Spawn spawnType, double spawnTime, double speed ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.HACKER, spawnTime, speed );
    }
}
