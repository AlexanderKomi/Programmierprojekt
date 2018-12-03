package de.hsh.dennis.model.actors;

import common.actor.Actor;
import common.config.WindowConfig;
import common.util.Logger;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public abstract class Npc extends Actor {

    private double spawnTime;
    private double speed = Config.Level.speed;
    private NPCEnums.Spawn spawnType;
    private NPCEnums.NpcType npcType;

    Npc(String picturePath, NPCEnums.Spawn spawnType, NPCEnums.NpcType type) throws FileNotFoundException {
        super(picturePath);
        setSpawnType(spawnType);
        setNpcType(type);

        switch (type) {
            case PACKAGE:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Package.skin_standard.getWidth());
                setPosY(Config.Player.posY);
                break;
            case BOT:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Bot.skin_standard.getWidth());
                setPosY(Config.Player.posY + (Config.Player.skin_standard.getHeight() * 0.2));
                break;
            case HACKER:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Hacker.skin_standard.getWidth());
                setPosY(Config.Player.posY + Config.Player.skin_standard.getHeight() - Config.Hacker.skin_standard.getHeight());
                break;

            default:
                Logger.log(this.getClass() + "Switching Default!");
        }

    }

    Npc(String picturePath, NPCEnums.Spawn spawnType, NPCEnums.NpcType type, double spawnTime, double speed) throws FileNotFoundException {
        super(picturePath);
        setSpawnType(spawnType);
        setNpcType(type);
        setSpawnTime(spawnTime);
        setSpeed(speed);

        switch (type) {
            case PACKAGE:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Package.skin_standard.getWidth());
                setPosY(Config.Player.posY);
                break;
            case BOT:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Bot.skin_standard.getWidth());
                setPosY(Config.Player.posY + (Config.Player.skin_standard.getHeight() * 0.2));
                break;
            case HACKER:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - Config.Hacker.skin_standard.getWidth());
                setPosY(Config.Player.posY + Config.Player.skin_standard.getHeight() - Config.Hacker.skin_standard.getHeight());
                break;

            default:
                Logger.log(this.getClass() + "Switching Default!");
        }

    }

    void move() {
        setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? (getPosX() - getSpeed()) : (getPosX() + getSpeed()));

    }

    @Override
    public String toString() {
        return "\nClassName:\t" + this.getClass().getName() + "\n" +
                "spawnTime:\t" + spawnTime + "\n" +
                "speed:\t\t" + speed + "\n" +
                "spawnType:\t" + spawnType.toString() + "\n" +
                "npcType:\t" + npcType.toString() + "\n"
                ;
    }

    // --- Getter & Setter ------------------------------------------------------------------------

    public Image getImage() {
        return this.getCurrentImage();
    }

    public double getPosX() {
        return this.getX();
    }

    void setPosX(double positionX) {
        this.setX(positionX);
    }

    public double getPosY() {
        return this.getY();
    }

    void setPosY(double positionY) {
        this.setY(positionY);
    }

    public NPCEnums.Spawn getSpawnType() {
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

    public double getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(double spawnTime) {
        this.spawnTime = spawnTime;
    }
}
