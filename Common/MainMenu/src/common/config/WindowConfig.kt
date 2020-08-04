package common.config

class WindowConfig {
    override fun toString(): String {
        return "WindowConfig(" +
               "window_width = " + window_width + ", " +
               "window_height = " + window_height +
               ")"
    }

    companion object {
        const val mainGui_title = "Awesome Game Collection"
        const val alexander_title = "Pacman Coop"
        const val amir_title = "Virus Collector"
        const val daniel_title = "Memory Leak"
        const val dennis_title = "DDos Defender"
        const val julian_title = "Leertastenklatsche"
        const val kevin_title = "Tunnel Invader"
        const val window_width = 1200
        const val window_height = 800
    }
}
