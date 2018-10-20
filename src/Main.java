import common.GameContainer;

import java.net.URL;

public class Main {

    public static void main( String[] args ) {
        GameContainer gameContainer = new GameContainer();
        if ( !GameContainer.isLaunched() ) {
            gameContainer.startContainer( args );
        }
    }

    public static String getMainLocation() {
        return getClassLocationAsURL().getPath();
    }

    public static URL getClassLocationAsURL() {
        return Main.class.getProtectionDomain().getCodeSource().getLocation();
    }

}
