package de.hsh.dennis.model.audio

class AudioConfig {
    object Mp3Names {
        const val easy = "sound1.mp3"
        const val medium = "Tantrum_Desire__Reach_VIP.mp3"
        const val hard = "Megalovania__Holder_Remix.mp3"
        const val nightmare = "Through_the_Fire_and_Flames__Dragonforce.mp3"
    }

    object DelayBetweenSpawns {
        const val _default = 1.0
        const val _easy = 1.0
        const val _medium = 0.2
        const val _hard = 0.15
        const val _nightmare = 0.05
    }

    object MovingSpeeds {
        const val _default = 1.0
        const val _easy = 1.0
        const val _medium = 1.5
        const val _hard = 2.0
        const val _nightmare = 2.0
    }
}
