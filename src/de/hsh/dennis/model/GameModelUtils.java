package de.hsh.dennis.model;

import de.hsh.dennis.model.NpcLogic.NpcHandler;
import de.hsh.dennis.model.audio.AudioConfig.DelayBetweenSpawns;
import de.hsh.dennis.model.audio.AudioConfig.MovingSpeeds;
import de.hsh.dennis.model.audio.AudioConfig.Mp3Paths;

final class GameModelUtils {

    private static final String ressourcePath     = "/de/hsh/dennis/resources/audioFiles/";
    private static final String relativeRessource = "../resources/audioFiles/";

    private GameModelUtils() {}

    static void initEasyDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.2d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._easy );
        npcHandler.generateNpcs( Mp3Paths.easy, MovingSpeeds._easy );
        AudioPlayer.MusicPlayer.loadFile( GameModelUtils.class
                                                  .getResource( relativeRessource + Mp3Paths.easy )
                                                  .getPath() );
    }

    static void initMediumDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.1d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._medium );
        npcHandler.generateNpcs( Mp3Paths.medium, MovingSpeeds._medium );
        AudioPlayer.MusicPlayer.loadFile( GameModelUtils.class
                                                  .getResource( relativeRessource + Mp3Paths.medium )
                                                  .getPath() );
    }

    static void initHardDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.05d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._hard );
        npcHandler.generateNpcs( Mp3Paths.hard, MovingSpeeds._hard );
        AudioPlayer.MusicPlayer.loadFile( GameModelUtils.class
                                                  .getResource( relativeRessource + Mp3Paths.hard )
                                                  .getPath() );
    }

    static void initNightmareDifficulty( NpcHandler npcHandler ) {
        npcHandler.getAudioAnalyzer().setSensitivity( 0.0d );
        npcHandler.setDelaysBetweenSpawns( DelayBetweenSpawns._nightmare );
        npcHandler.generateNpcs( Mp3Paths.nightmare, MovingSpeeds._nightmare );
        AudioPlayer.MusicPlayer.loadFile( GameModelUtils.class
                                                  .getResource( relativeRessource + Mp3Paths.nightmare )
                                                  .getPath() );
    }
}
