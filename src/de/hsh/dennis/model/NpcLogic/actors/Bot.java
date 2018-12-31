package de.hsh.dennis.model.NpcLogic.actors;


import de.hsh.dennis.model.NpcLogic.SkinConfig;
import de.hsh.dennis.model.NpcLogic.NPCEnums;

import java.io.FileNotFoundException;

public class Bot extends Npc {

    private static final String picturePath = "/dennis/skins/bot/bot.png";
    private static final int defaultSpeed = 1;

    private double bounceMax = 5.0;
    private double bouncePos = getBounceMax() * -1.0;
    private double bounceSpeed = getSpeed() / 2.0;
    private boolean bounceInitialized = false;


    public Bot(NPCEnums.Spawn spawnType) throws FileNotFoundException {
        super(SkinConfig.Bot.skin_standard_path, spawnType, NPCEnums.NpcType.BOT);
        setCurrentImage(SkinConfig.Bot.skin_standard);
        bounceInit();
    }

    public Bot(NPCEnums.Spawn spawnType, double spawnTime) throws FileNotFoundException {
        super(SkinConfig.Bot.skin_standard_path, spawnType, NPCEnums.NpcType.BOT, spawnTime, defaultSpeed);
        setCurrentImage(SkinConfig.Bot.skin_standard);

        bounceInit();
    }

    public Bot(NPCEnums.Spawn spawnType, double spawnTime, double speed) throws FileNotFoundException {
        super(SkinConfig.Bot.skin_standard_path, spawnType, NPCEnums.NpcType.BOT, spawnTime, speed);
        setCurrentImage(SkinConfig.Bot.skin_standard);

        bounceInit();
    }

    @Override
    public void move() {
        setPosX((getSpawnType() == NPCEnums.Spawn.RIGHT) ? (getPosX() - getSpeed()) : (getPosX() + getSpeed()));
        bounce();

    }

    private void bounceInit() {
        if (!bounceInitialized) {
            setPosY(getPosY() - getBounceMax());
            bounceInitialized = !bounceInitialized;
        }
    }

    private void bounce() {


        if (bounceMax > 0.0) {
            if (bouncePos < getBounceMax()) {
                if ((bouncePos + bounceSpeed) > getBounceMax()) {
                    bouncePos = getBounceMax();
                } else {
                    bouncePos += bounceSpeed;
                }
            } else if (bouncePos >= getBounceMax()) {
                setBounceMax(getBounceMax() * -1.0);
            }
        } else {
            if (bouncePos > getBounceMax()) {
                if ((bouncePos - bounceSpeed) < getBounceMax()) {
                    bouncePos = getBounceMax();
                } else {
                    bouncePos -= bounceSpeed;
                }
            } else if (bouncePos <= getBounceMax()) {
                setBounceMax(getBounceMax() * -1.0);
            }
        }
        setPosY(getPosY() + bouncePos);
    }

    public double getBounceMax() {
        return bounceMax;
    }

    public void setBounceMax(double bounceMax) {
        this.bounceMax = bounceMax;
    }
}
