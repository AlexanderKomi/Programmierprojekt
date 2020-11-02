package de.hsh.alexander.actor

object ResourcePaths {
    private const val resources = "/de/hsh/alexander/"
    private const val resourceFolderPath = resources + "res/"
    private const val soundDirectory = resources + "sounds/"

    object Actor {
        const val actorFolderPath = resourceFolderPath + "actor/"

        object Player {
            const val directory = actorFolderPath + "player/"

            object PacMan {
                private const val pacman1Directory = directory + "pacman1/"
                private const val file_prefix = "pacman"
                private const val file_type = ".png"
                const val pacManSound = soundDirectory + "pacman_chomp.wav"
                val pacman1Pictures = arrayOf(
                        pacman1Directory + file_prefix + "1_0" + file_type,
                        pacman1Directory + file_prefix + "1_1" + file_type,
                        pacman1Directory + file_prefix + "1_2" + file_type,
                        pacman1Directory + file_prefix + "1_3" + file_type)
                private const val pacman2Directory = directory + "pacman2/"
                val pacman2Pictures = arrayOf(
                        pacman2Directory + file_prefix + "2_0" + file_type,
                        pacman2Directory + file_prefix + "2_1" + file_type,
                        pacman2Directory + file_prefix + "2_2" + file_type,
                        pacman2Directory + file_prefix + "2_3" + file_type)
            }
        }

        object LevelElements {
            const val directory = actorFolderPath + "level_elements/"

            object SMD {
                private const val directory = LevelElements.directory + "smd/"
                private const val prefix = "smd_"
                private const val fileEnding = ".png"
                val pictures = arrayOf(
                        directory + prefix + "0" + fileEnding,
                        directory + prefix + "1" + fileEnding,
                        directory + prefix + "2" + fileEnding,
                        directory + prefix + "3" + fileEnding
                                      )
            }

            object Backgrounds {
                private const val directory = LevelElements.directory + "backgrounds/"
                const val microChip = directory + "chip.jpg"
            }

            object Condensator {
                private const val directory = LevelElements.directory + "condensator/"
                private const val fileEnding = ".png"
                private const val prefix = "condensator"
                private const val separator = "_"
                const val full_picture = directory + prefix + fileEnding
                val pictures = arrayOf(
                        directory + prefix + separator + "1" + fileEnding,
                        directory + prefix + separator + "2" + fileEnding)
            }

            object Fan {
                private const val directory = LevelElements.directory + "fan/"
                private const val prefix = "fan_"
                private const val fileEnding = ".png"
                val pictures = arrayOf(
                        directory + prefix + "0" + fileEnding,
                        directory + prefix + "1" + fileEnding,
                        directory + prefix + "2" + fileEnding,
                        directory + prefix + "3" + fileEnding,
                        directory + prefix + "4" + fileEnding,
                        directory + prefix + "5" + fileEnding
                                      )
            }
        }

        object Collectables {
            const val directory = actorFolderPath + "collectables/"

            object DataCoin {
                private const val data_coin_dir = directory + "data_coin/"
                private const val file_prefix = "data_coin_"
                private const val file_type = ".png"
                val pictureFilePaths = arrayOf(
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
                                              )
            }

            object Invisible {
                const val invisiblePicture = directory + "Empty.png"
                const val invisibleSound = soundDirectory + "pacman_eatghost.wav"
            }
        }
    }
}
