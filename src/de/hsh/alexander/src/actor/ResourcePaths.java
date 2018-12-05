package de.hsh.alexander.src.actor;

import common.util.Path;

public final class ResourcePaths {

    public static final String resourceFolderPath = Path.getExecutionLocation() + "de/hsh/alexander/res/";

    public static final class Actor {

        public static final String actorFolderPath = resourceFolderPath + "actor/";

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
            public static final String directory = actorFolderPath + "level_elements/";

            public static final class Wall {
                public static final String directory = LevelElements.directory + "wall/";
            }

            public static final class SMD {
                public static final String   directory = LevelElements.directory + "smd/";
                public static final String[] pictures  = {
                        SMD.directory + "smd_0.png",
                        SMD.directory + "smd_1.png",
                        SMD.directory + "smd_2.png"
                };
            }

            public static final class Backgrounds {
                public static final String directory     = LevelElements.directory + "backgrounds/";
                public static final String leeresFenster = Backgrounds.directory + "leeresFenster.png";
            }

        }

        public static final class Collectables {
            public static final String directory = actorFolderPath + "collectables/";

            public static final class DataCoin {
                public static final String directory = Collectables.directory + "data_coin/";
            }
        }

    }


}
