package de.hsh.dennis.model.NpcLogic;

import com.google.gson.Gson;
import common.util.Logger;
import de.hsh.dennis.model.NpcLogic.actors.Bot;
import de.hsh.dennis.model.NpcLogic.actors.Hacker;
import de.hsh.dennis.model.NpcLogic.actors.Npc;
import de.hsh.dennis.model.NpcLogic.actors.Package;

import java.io.*;

public class NpcIO {

    /**
     * Gets the execution location of the main program.
     *
     * @return a String representation.
     *
     * @author Alexander Komischke
     */
    public static String getExecutionLocation() {
        return NpcIO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

    public Npc[] loadLevel(SkinConfig.Level.Difficulty dif) {

        //JsonNpc[] tempArray = new JsonNpc[]{new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE)};
        //gsonWrite(tempArray, "temp");

        String fileName;
        switch (dif) {
            case MEDIUM:
                fileName = "medium";
                break;
            case HARD:
                fileName = "hard";
                break;
            case NIGHTMARE:
                fileName = "nightmare";
                break;
            case CUSTOM:
                fileName = "temp";
                break;
            default:
                fileName = "easy";
                break;
        }

        return gsonRead(fileName);
    }

    private Npc[] gsonRead(String fileName) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(getExecutionLocation() + "de/hsh/dennis/resources/levelFiles/" + fileName + ".json"));
            return readNPC(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        return new Npc[]{};
    }

    private Npc[] readNPC(BufferedReader bufferedReader) {
        Gson gson = new Gson();
        JsonNpc[] parsingArray = gson.fromJson(bufferedReader, JsonNpc[].class);
        Npc[] returnArray = new Npc[parsingArray.length];
        for (int i = 0; i < parsingArray.length; i++) {

            if (parsingArray[i].getNpcType() != null) {
                switch (parsingArray[i].getNpcType()) {
                    case PACKAGE:
                        returnArray[i] = new Package(parsingArray[i].getSpawnType(), parsingArray[i].getSpawnTime(), parsingArray[i].getSpeed());
                        break;
                    case BOT:
                        returnArray[i] = new Bot(parsingArray[i].getSpawnType(), parsingArray[i].getSpawnTime(), parsingArray[i].getSpeed());
                        break;

                    case HACKER:
                        returnArray[i] = new Hacker(parsingArray[i].getSpawnType(), parsingArray[i].getSpawnTime(), parsingArray[i].getSpeed());
                        break;
                    default:
                        Logger.log(this.getClass().getName() + "failed to create matching NpcLogic");
                }
            } else returnArray[i] = null;
        }

        return returnArray;
    }
}
