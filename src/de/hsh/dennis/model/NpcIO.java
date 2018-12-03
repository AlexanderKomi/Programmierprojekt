package de.hsh.dennis.model;

import com.google.gson.Gson;
import common.util.Path;
import de.hsh.dennis.model.actors.Config;
import de.hsh.dennis.model.actors.JsonNpc;
import de.hsh.dennis.model.actors.NPCEnums;

import java.io.*;

public class NpcIO {

    public JsonNpc[] loadLevel(Config.Level.Difficulty dif) {
        //TODO: Loading Json files correctly

        JsonNpc[] tempArray = new JsonNpc[]{new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE)};

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

    private JsonNpc[] gsonRead() {
        Gson gson = new Gson();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Path.getExecutionLocation() + "de/hsh/dennis/resources/levelFiles/temp.json"));
            return gson.fromJson(bufferedReader, JsonNpc[].class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new JsonNpc[]{};
    }
}
