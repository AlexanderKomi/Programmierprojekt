package de.hsh.dennis.model.actors;

import common.util.Path;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public class Package extends Npc {

    private static final String pictureFileName = Path.getExecutionLocation() + "de/hsh/dennis/resources/actors/Bot/bot_standard.png";

    private Image skin_current;

    public Package(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(pictureFileName, spawnType, NPCEnums.NpcType.PACKAGE);
        skin_current = Config.Package.skin_standard;
    }
}
