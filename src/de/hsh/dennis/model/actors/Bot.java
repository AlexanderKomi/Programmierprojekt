package de.hsh.dennis.model.actors;


import javafx.scene.image.Image;

public class Bot extends Npc {

    private static final String pictureFileName = "bot_standard.png";
    private static final String dir = "de/hsh/dennis/resources/actors/Bot/";

    private double bounceMax = 5.0;
    private double bouncePos = getBounceMax() * -1.0;
    private double bounceSpeed = getSpeed() / 2.0;
    private boolean bounceInitialized = false;

    private Image skin_current;

    public Bot(NPCEnums.Spawn spawnType) {
        super(pictureFileName, dir, spawnType, NPCEnums.NpcType.BOT);
        skin_current = Config.Bot.skin_standard;

        bounceInit();
    }

    @Override
    public void move() {
        setPositionX((getSpawnType() == NPCEnums.Spawn.RIGHT) ? (getPosX() - getSpeed()) : (getPosX() + getSpeed()));
        bounce();

    }

    private void bounceInit() {
        if (!bounceInitialized) {
            setPositionY(getPosY() - getBounceMax());
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
        setPositionY(getPosY() + bouncePos);
    }

    public double getBounceMax() {
        return bounceMax;
    }

    public void setBounceMax(double bounceMax) {
        this.bounceMax = bounceMax;
    }
}
