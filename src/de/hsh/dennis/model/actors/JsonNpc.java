package de.hsh.dennis.model.actors;

public class JsonNpc {
    private Double spawnTime;
    private Double speed = Config.Level.speed;
    private String spawnType;
    private String npcType;

    public JsonNpc(Double spawnTime, Double speed, String spawnType, String npcType) {
        this.spawnTime = spawnTime;
        this.speed = speed;
        this.spawnType = spawnType;
        this.npcType = npcType;
    }
}
