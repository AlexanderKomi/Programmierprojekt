import common.GameContainer;

public class Main {

    public static void main( String[] args ) {
        final GameContainer gameContainer = new GameContainer();
        if ( !GameContainer.isLaunched() ) {
            gameContainer.startContainer( args );
        }
    }
}
