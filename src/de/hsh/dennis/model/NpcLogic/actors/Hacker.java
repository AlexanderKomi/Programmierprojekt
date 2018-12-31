package de.hsh.dennis.model.NpcLogic.actors;

import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.SkinConfig;

import static de.hsh.dennis.model.NpcLogic.SkinConfig.Hacker.skin_standard_path;

public class Hacker extends Npc {

    private static final int defaultSpeed = 1;

    public Hacker( NPCEnums.Spawn spawnType ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.HACKER );
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }

    public Hacker( NPCEnums.Spawn spawnType, double spawnTime ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.HACKER, spawnTime, defaultSpeed );
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }

    public Hacker( NPCEnums.Spawn spawnType, double spawnTime, double speed ) {
        super( skin_standard_path, spawnType, NPCEnums.NpcType.HACKER, spawnTime, speed );
        setCurrentImage(SkinConfig.Hacker.skin_standard);
    }
}
