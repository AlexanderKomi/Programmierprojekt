package de.hsh.dennis.model.NpcLogic.actors;

import common.actor.Actor;
import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import de.hsh.dennis.model.NpcLogic.NPCEnums;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

public abstract class Npc extends Actor implements Comparable {

    private double spawnTime;
    private double speed = SkinConfig.Level.speed;
    private NPCEnums.Spawn spawnType;
    private NPCEnums.NpcType npcType;

    Npc(String picturePath, NPCEnums.Spawn spawnType, NPCEnums.NpcType type) throws FileNotFoundException {
        super(picturePath);
        setSpawnType(spawnType);
        setNpcType(type);

        switch (type) {
            case PACKAGE:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Package.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY);
                break;
            case BOT:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Bot.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY + (SkinConfig.Player.skin_standard.getHeight() * 0.2));
                break;
            case HACKER:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Hacker.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY + SkinConfig.Player.skin_standard.getHeight() - SkinConfig.Hacker.skin_standard.getHeight());
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
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Package.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY);
                break;
            case BOT:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Bot.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY + (SkinConfig.Player.skin_standard.getHeight() * 0.2));
                break;
            case HACKER:
                setPosX((spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width : 0 - SkinConfig.Hacker.skin_standard.getWidth());
                setPosY(SkinConfig.Player.posY + SkinConfig.Player.skin_standard.getHeight() - SkinConfig.Hacker.skin_standard.getHeight());
                break;

            default:
                Logger.log(this.getClass() + "Switching Default!");
        }

    }

    public void move() {
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

    @Override
    public int compareTo(Object o) {
        Npc other = (Npc) o;

        if (this.getSpawnTime() < other.getSpawnTime()) {
            return -1;
        } else if (this.getSpawnTime() > other.getSpawnTime()) {
            return 1;
        }
        return 0;
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
