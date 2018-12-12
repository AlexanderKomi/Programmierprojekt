package de.hsh.alexander.src.actor;

public final class ResourcePaths {

    private static final String resourceFolderPath = "/de/hsh/alexander/res/";

    public static final class Actor {

        static final String actorFolderPath = resourceFolderPath + "actor/";

        public static final class Player {
            public static final String directory = actorFolderPath + "player/";

            public static final class PacMan {
                public static final String   pacman1Directory = Player.directory + "pacman1/";
                public static final String[] pacman1Pictures  = {
                        pacman1Directory + "pacman1_0.png",
                        pacman1Directory + "pacman1_1.png",
                        pacman1Directory + "pacman1_2.png",
                        pacman1Directory + "pacman1_3.png",
                        };
                public static final String   pacman2Directory = Player.directory + "pacman2/";
                public static final String[] pacman2Pictures  = {
                        pacman2Directory + "pacman2_0.png",
                        pacman2Directory + "pacman2_1.png",
                        pacman2Directory + "pacman2_2.png",
                        pacman2Directory + "pacman2_3.png",
                        };
            }
        }

        public static final class LevelElements {
            static final String directory = actorFolderPath + "level_elements/";

            public static final class Wall {
                public static final String directory = LevelElements.directory + "wall/";
            }

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
                public static final String directory     = LevelElements.directory + "backgrounds/";
                public static final String leeresFenster = Backgrounds.directory + "leeresFenster.png";
                public static final String microChip     = Backgrounds.directory + "chip.jpg";
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
                public static final String directory = Collectables.directory + "data_coin/";
            }
        }

    }


}
