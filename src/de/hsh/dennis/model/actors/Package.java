package de.hsh.dennis.model.actors;

import javafx.scene.image.Image;

public class Package extends Npc {

    private static final String pictureFileName = "bot_standard.png";
    private static final String dir = "de/hsh/dennis/resources/actors/Bot/";

    private Image skin_current;

    public Package(NPCEnums.Spawn spawnType) {
        super(pictureFileName, dir, spawnType, NPCEnums.NpcType.PACKAGE);
        skin_current = Config.Package.skin_standard;
    }
}
