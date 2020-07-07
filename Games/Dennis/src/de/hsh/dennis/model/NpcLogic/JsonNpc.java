package de.hsh.dennis.model.NpcLogic;

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

    public Double getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(Double spawnTime) {
        this.spawnTime = spawnTime;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public NPCEnums.Spawn getSpawnType() {
        return spawnType;
    }

    public void setSpawnType(NPCEnums.Spawn spawnType) {
        this.spawnType = spawnType;
    }

    public NPCEnums.NpcType getNpcType() {
        return npcType;
    }

    public void setNpcType(NPCEnums.NpcType npcType) {
        this.npcType = npcType;
    }
}
