package de.hsh.dennis.model.actors;

public class JsonNpc {
    private Double spawnTime;
    private Double speed;
    private NPCEnums.Spawn spawnType;
    private NPCEnums.NpcType npcType;

    public JsonNpc(Double spawnTime, Double speed, NPCEnums.Spawn spawnType, NPCEnums.NpcType npcType) {
        this.spawnTime = spawnTime;
        this.speed = speed;
        this.spawnType = spawnType;
        this.npcType = npcType;
    }

    @Override
    public String toString() {
        return "\n" +
                "spawnTime:\t" + spawnTime.toString() + "\n" +
                "speed:\t\t" + speed.toString() + "\n" +
                "spawnType:\t" + spawnType.toString() + "\n" +
                "npcType:\t" + npcType.toString() + "\n"
                ;
    }
}
