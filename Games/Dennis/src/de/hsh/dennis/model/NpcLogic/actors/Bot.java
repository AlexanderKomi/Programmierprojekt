package de.hsh.dennis.model.NpcLogic.actors;


import de.hsh.dennis.model.NpcLogic.NPCEnums;
import de.hsh.dennis.model.NpcLogic.SkinConfig;

public class Bot extends Npc {

    private double bounceMax = 5.0;
    private double bouncePos = getBounceMax() * -1.0;
    private double bounceSpeed = getSpeed() / 2.0;
    private boolean bounceInitialized = false;

    public Bot( NPCEnums.Spawn spawnType, double spawnTime, double speed ) {
        super(SkinConfig.Bot.skin_standard_path, spawnType, NPCEnums.NpcType.BOT, spawnTime, speed);
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
