package de.hsh.dennis.model.NpcLogic

import com.google.gson.Gson
import common.util.Logger.log
import de.hsh.dennis.model.NpcLogic.NPCEnums.NpcType
import de.hsh.dennis.model.NpcLogic.SkinConfig.Level.Difficulty
import de.hsh.dennis.model.NpcLogic.actors.Bot
import de.hsh.dennis.model.NpcLogic.actors.Hacker
import de.hsh.dennis.model.NpcLogic.actors.Npc
import de.hsh.dennis.model.NpcLogic.actors.Package
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException

class NpcIO {
    fun loadLevel(dif: Difficulty?): Array<Npc?> {

        //JsonNpc[] tempArray = new JsonNpc[]{new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE), new JsonNpc(1.0d, 1.0d, NPCEnums.Spawn.RIGHT, NPCEnums.NpcType.PACKAGE)};
        //gsonWrite(tempArray, "temp");
        val fileName: String
        fileName = when (dif) {
            Difficulty.MEDIUM    -> "medium"
            Difficulty.HARD      -> "hard"
            Difficulty.NIGHTMARE -> "nightmare"
            Difficulty.CUSTOM    -> "temp"
            else                 -> "easy"
        }
        return gsonRead(fileName)
    }

    private fun gsonRead(fileName: String): Array<Npc?> {
        var bufferedReader: BufferedReader? = null
        try {
            bufferedReader = BufferedReader(FileReader(executionLocation + "de/hsh/dennis/resources/levelFiles/" + fileName + ".json"))
            return readNPC(bufferedReader)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (ioException: IOException) {
                    ioException.printStackTrace()
                }
            }
        }
        return arrayOf()
    }

    private fun readNPC(bufferedReader: BufferedReader): Array<Npc?> {
        val gson = Gson()
        val parsingArray = gson.fromJson(bufferedReader,
                                         Array<JsonNpc>::class.java)
        val returnArray = arrayOfNulls<Npc>(parsingArray.size)
        for (i in parsingArray.indices) {
            when (parsingArray[i].npcType) {
                NpcType.PACKAGE -> returnArray[i] = Package(parsingArray[i].spawnType,
                                                            parsingArray[i].spawnTime,
                                                            parsingArray[i].speed)
                NpcType.BOT     -> returnArray[i] = Bot(parsingArray[i]
                                                                .spawnType,
                                                        parsingArray[i]
                                                                .spawnTime,
                                                        parsingArray[i]
                                                                .speed)
                NpcType.HACKER  -> returnArray[i] = Hacker(parsingArray[i].spawnType,
                                                           parsingArray[i].spawnTime,
                                                           parsingArray[i].speed)
            }
        }
        return returnArray
    }

    companion object {
        /**
         * Gets the execution location of the main program.
         *
         * @return a String representation.
         *
         * @author Alexander Komischke
         */
        val executionLocation: String
            get() = NpcIO::class.java.protectionDomain.codeSource.location.path
    }
}
