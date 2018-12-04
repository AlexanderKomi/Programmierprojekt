package de.hsh.alexander.src.actor;

import common.util.Path;

public final class ResourcePaths {

    public static final String resourceFolderPath = Path.getExecutionLocation() + "de/hsh/alexander/res/";

    public static final class Actor {

        public static final String actorFolderPath = resourceFolderPath + "actor/";

        public static final class Player {
            public static final String directory = actorFolderPath + "player/";

            public static final class PacMan {
                public static final String pacman1Directory = Player.directory + "pacman1/";
                public static final String pacman2Directory = Player.directory + "pacman2/";
            }
        }

        public static final class LevelElements {
            public static final String directory = actorFolderPath + "level_elements/";

            public static final class Wall {
                public static final String directory = LevelElements.directory + "wall/";
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
