package de.hsh.dennis.model.NpcLogic.actors;

import common.actor.Actor;
import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.SkinConfig;
import javafx.scene.image.Image;

public abstract class Npc extends Actor implements Comparable {

    private final double           spawnTime;
    private       double           speed = SkinConfig.Level.speed;
    private final NPCEnums.Spawn   spawnType;
    private final NPCEnums.NpcType npcType;

    Npc( final String picturePath, NPCEnums.Spawn spawnType, NPCEnums.NpcType type ) {
        super( picturePath );
        this.spawnType = spawnType;
        this.npcType = type;
        spawnTime = 0;

        switch ( type ) {
            case PACKAGE:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Package.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY );
                break;
            case BOT:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Bot.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY + (SkinConfig.Player.skin_standard.getHeight() * 0.2) );
                break;
            case HACKER:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Hacker.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY + SkinConfig.Player.skin_standard.getHeight() -
                         SkinConfig.Hacker.skin_standard.getHeight() );
                break;

            default:
                Logger.log( this.getClass() + "Switching Default!" );
        }

    }

    Npc( final String picturePath, NPCEnums.Spawn spawnType, NPCEnums.NpcType type, double spawnTime, double speed ) {
        super( picturePath );
        this.spawnTime = spawnTime;
        this.npcType = type;
        this.spawnType = spawnType;
        setSpeed( speed );

        switch ( type ) {
            case PACKAGE:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Package.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY );
                break;
            case BOT:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Bot.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY + (SkinConfig.Player.skin_standard.getHeight() * 0.2) );
                break;
            case HACKER:
                setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? WindowConfig.window_width :
                         0 - SkinConfig.Hacker.skin_standard.getWidth() );
                setPosY( SkinConfig.Player.posY + SkinConfig.Player.skin_standard.getHeight() -
                         SkinConfig.Hacker.skin_standard.getHeight() );
                break;

            default:
                Logger.log( this.getClass() + "Switching Default!" );
        }

    }

    public void move() {
        setPosX( (spawnType == NPCEnums.Spawn.RIGHT) ? (getPosX() - getSpeed()) : (getPosX() + getSpeed()) );

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
    public int compareTo( Object o ) {
        Npc other = (Npc) o;

        if ( this.getSpawnTime() < other.getSpawnTime() ) {
            return -1;
        }
        else if ( this.getSpawnTime() > other.getSpawnTime() ) {
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

    void setPosX( double positionX ) {
        this.setX( positionX );
    }

    public double getPosY() {
        return this.getY();
    }

    void setPosY( double positionY ) {
        this.setY( positionY );
    }

    public NPCEnums.Spawn getSpawnType() {
        return spawnType;
    }

    public NPCEnums.NpcType getNpcType() {
        return npcType;
    }

    public double getSpawnTime() {
        return spawnTime;
    }
}
