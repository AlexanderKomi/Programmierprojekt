package de.hsh.alexander.src.actor;

public final class ResourcePaths {

    private static final String resourceFolderPath = "/de/hsh/alexander/res/";
    private static final String soundDirectory     = "/de/hsh/alexander/sounds/";

    public static final class Actor {

        static final String actorFolderPath = resourceFolderPath + "actor/";

        public static final class Player {
            static final String directory = actorFolderPath + "player/";

            public static final class PacMan {
                static final        String pacman1Directory = Player.directory + "pacman1/";
                static final        String file_prefix      = "pacman";
                static final        String file_type        = ".png";
                public static final String pacManSound      = soundDirectory + "pacman_chomp.wav";

                public static final String[] pacman1Pictures  = {
                        pacman1Directory + file_prefix + "1_0" + file_type,
                        pacman1Directory + file_prefix + "1_1" + file_type,
                        pacman1Directory + file_prefix + "1_2" + file_type,
                        pacman1Directory + file_prefix + "1_3" + file_type,
                        };

                static final        String   pacman2Directory = Player.directory + "pacman2/";
                public static final String[] pacman2Pictures  = {
                        pacman2Directory + file_prefix + "2_0" + file_type,
                        pacman2Directory + file_prefix + "2_1" + file_type,
                        pacman2Directory + file_prefix + "2_2" + file_type,
                        pacman2Directory + file_prefix + "2_3" + file_type,
                        };
            }
        }

        public static final class LevelElements {
            static final String directory = actorFolderPath + "level_elements/";

            public static final class SMD {
                static final        String   directory  = LevelElements.directory + "smd/";
                static final        String   prefix     = "smd_";
                static final        String   fileEnding = ".png";
                public static final String[] pictures   = {
                        SMD.directory + prefix + "0" + fileEnding,
                        SMD.directory + prefix + "1" + fileEnding,
                        SMD.directory + prefix + "2" + fileEnding,
                        SMD.directory + prefix + "3" + fileEnding
                };
            }

            public static final class Backgrounds {
                static final        String directory     = LevelElements.directory + "backgrounds/";
                public static final String leeresFenster = Backgrounds.directory + "leeresFenster.png";
                public static final String microChip     = Backgrounds.directory + "chip.jpg";
            }

            public static final class Condensator {
                static final String directory  = LevelElements.directory + "condensator/";
                static final String fileEnding = ".png";
                static final String prefix     = "condensator";
                static final String seperator  = "_";

                public static final String   full_picture = directory + prefix + fileEnding;
                public static final String[] pictures     = {
                        directory + prefix + seperator + "1" + fileEnding,
                        directory + prefix + seperator + "2" + fileEnding,
                        };
            }

            public static final class Fan {
                static final        String   directory  = LevelElements.directory + "fan/";
                static final        String   prefix     = "fan_";
                static final        String   fileEnding = ".png";
                public static final String[] pictures   = {
                        directory + prefix + "0" + fileEnding,
                        directory + prefix + "1" + fileEnding,
                        directory + prefix + "2" + fileEnding,
                        directory + prefix + "3" + fileEnding,
                        directory + prefix + "4" + fileEnding,
                        directory + prefix + "5" + fileEnding
                };
            }
        }

        public static final class Collectables {
            static final String directory = actorFolderPath + "collectables/";

            public static final class DataCoin {

                static final        String   data_coin_dir    = Collectables.directory + "data_coin/";
                static final        String   file_prefix      = "data_coin_";
                static final        String   file_type        = ".png";
                public static final String[] pictureFilePaths = new String[] {
                        data_coin_dir + file_prefix + "00" + file_type,
                        data_coin_dir + file_prefix + "01" + file_type,
                        data_coin_dir + file_prefix + "02" + file_type,
                        data_coin_dir + file_prefix + "03" + file_type,
                        data_coin_dir + file_prefix + "04" + file_type,
                        data_coin_dir + file_prefix + "05" + file_type,
                        data_coin_dir + file_prefix + "06" + file_type,
                        data_coin_dir + file_prefix + "07" + file_type,
                        data_coin_dir + file_prefix + "08" + file_type,
                        data_coin_dir + file_prefix + "09" + file_type,
                        data_coin_dir + file_prefix + "10" + file_type,
                        data_coin_dir + file_prefix + "11" + file_type
                };
            }

            public static final class Invisible {

                public static final String invisiblePicture = directory + "Empty.png";
                public static final String invisibleSound   = soundDirectory + "pacman_eatghost.wav";
            }
        }

    }


}
