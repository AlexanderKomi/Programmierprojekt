package de.hsh.dennis.model;

import com.google.gson.Gson;
import common.util.Logger;
import common.util.Path;
import de.hsh.dennis.model.actors.Package;
import de.hsh.dennis.model.actors.*;

import java.io.*;

public class NpcIO {

    public Npc[] loadLevel(Config.Level.Difficulty dif) {
        //TODO: Loading Json files correctly

        //JsonNpc[] tempArray = new JsonNpc[]{new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE)};

        //gsonWrite(tempArray);


        return gsonRead();

    }

    private void gsonWrite(JsonNpc[] array) {
        Gson gson = new Gson();
        String json = gson.toJson(array);
        try {
            FileWriter writer = new FileWriter(Path.getExecutionLocation() + "de/hsh/dennis/resources/levelFiles/temp.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Npc[] gsonRead() {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Path.getExecutionLocation() + "de/hsh/dennis/resources/levelFiles/easy.json"));
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
                            Logger.log(this.getClass().getName() + "failed to create matching Npc");
                    }
                } else returnArray[i] = null;
            }

            return returnArray;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Npc[]{};
    }
}
