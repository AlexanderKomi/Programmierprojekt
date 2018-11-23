package common.config;

public class WindowConfig {

    public static final String mainGui_title   = "Awesome Game Collection";
    public static final String alexander_title = "Pacman Coop";
    public static final String amir_title      = "Amirs Game";
    public static final String daniel_title    = "RAM";
    public static final String dennis_title    = "DDos Defender";
    public static final String julian_title    = "Leertastenklatsche";
    public static final String kevin_title     = "Tunnel Invader";

    public static final int window_width  = 1200;
    public static final int window_height = 800;


    @Override
    public String toString() {
        return "WindowConfig(" +
               "window_width = " + window_width + ", " +
               "window_height = " + window_height +
               ")";
    }
}
