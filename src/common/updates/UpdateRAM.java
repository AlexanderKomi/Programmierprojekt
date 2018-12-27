package common.updates;

import common.GameContainer;
import common.util.Logger;
import de.hsh.daniel.RAM;

public class UpdateRAM {

    public static void update(RAM game, Object arg, GameContainer gameContainer) {
        Logger.log( UpdateRAM.class + ": " + game, arg );
       if(arg instanceof String) {
           String message = (String) arg;
            switch (message) {
                case UpdateCodes.DefaultCodes.exitToMainGUI:
                    gameContainer.showMainMenu();
                    System.gc();                        //remind the garbage collector. he may trow some unused objects away after the game session should be closed.
                    break;
            }
        }
    }
}