package de.hsh.dennis.model;

import de.hsh.dennis.model.NpcLogic.NpcHandler;
import de.hsh.dennis.model.audio.AudioConfig.DelayBetweenSpawns;
import de.hsh.dennis.model.audio.AudioConfig.MovingSpeeds;
import de.hsh.dennis.model.audio.AudioConfig.Mp3Names;

final class GameModelUtils {

    private GameModelUtils() {}

    static void initEasyDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.2d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._easy );
        npcHandler.generateNpcs( Mp3Names.easy, MovingSpeeds._easy );
        AudioPlayer.MusicPlayer.loadFile( Mp3Names.easy );
    }

    static void initMediumDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.1d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._medium );
        npcHandler.generateNpcs( Mp3Names.medium, MovingSpeeds._medium );
        AudioPlayer.MusicPlayer.loadFile( Mp3Names.medium );
    }

    static void initHardDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.05d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._hard );
        npcHandler.generateNpcs( Mp3Names.hard, MovingSpeeds._hard );
        AudioPlayer.MusicPlayer.loadFile( Mp3Names.hard );
    }

    static void initNightmareDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.0d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._nightmare );
        npcHandler.generateNpcs( Mp3Names.nightmare, MovingSpeeds._nightmare );
        AudioPlayer.MusicPlayer.loadFile( Mp3Names.nightmare );
    }
}
