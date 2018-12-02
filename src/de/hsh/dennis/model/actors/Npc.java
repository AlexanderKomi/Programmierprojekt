package de.hsh.dennis.model.actors;

import common.actor.Actor;
import common.config.WindowConfig;
import common.util.Logger;
import javafx.scene.image.Image;

public abstract class Npc extends Actor {

    private double speed = Config.Level.speed;
    private double positionX;
    private double positionY;
    private NPCEnums.Spawn spawnType;
    private NPCEnums.NpcType npcType;

    Npc(String pictureFileName, String dir, NPCEnums.Spawn spawnType, NPCEnums.NpcType type) {
        super(pictureFileName, dir);
        setSpawnType(spawnType);
        setNpcType(type);

        switch (type) {
            case PACKAGE:
                setPositionX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Package.skin_standard.getWidth());
                setPositionY(Config.Player.posY);
                break;
            case BOT:
                setPositionX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Bot.skin_standard.getWidth());
                setPositionY(Config.Player.posY + (Config.Player.skin_standard.getHeight() * 0.2));
                break;
            case HACKER:
                setPositionX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Hacker.skin_standard.getWidth());
                setPositionY(Config.Player.posY + Config.Player.skin_standard.getHeight() - Config.Hacker.skin_standard.getHeight());
                break;

            default:
                Logger.log(this.getClass() + "Switching Default!");
        }

    }

    void move() {
        setPositionX((spawnType == NPCEnums.Spawn.RIGHT) ? (getPosX() - getSpeed()) : (getPosX() + getSpeed()));

    }


    // --- Getter & Setter ------------------------------------------------------------------------

    public Image getImage() {
        return this.getPicture();
    }

    public double getPosX() {
        return positionX;
    }

    void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPosY() {
        return positionY;
    }

    void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    NPCEnums.Spawn getSpawnType() {
        return spawnType;
    }

    private void setSpawnType(NPCEnums.Spawn spawnType) {
        this.spawnType = spawnType;
    }

    private void setSpeed(double speed) {
        this.speed = speed;
    }

    double getSpeed() {
        return speed;
    }

    public NPCEnums.NpcType getNpcType() {
        return npcType;
    }

    public void setNpcType(NPCEnums.NpcType npcType) {
        this.npcType = npcType;
    }
}
