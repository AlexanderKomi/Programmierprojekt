import common.GameContainer;

public class Main {

    public static void main( String[] args ) {
        GameContainer gameContainer = new GameContainer();
        if ( !GameContainer.isLaunched() ) {
            //Path.test();
            gameContainer.startContainer( args );
        }
    }
}
