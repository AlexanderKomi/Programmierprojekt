import common.GameContainer;
import de.hsh.alexander.util.Path;

public class Main {

    public static void main( String[] args ) {
        GameContainer gameContainer = new GameContainer();
        if ( !GameContainer.isLaunched() ) {
            Path.test();
            gameContainer.startContainer( args );
        }
    }
}
