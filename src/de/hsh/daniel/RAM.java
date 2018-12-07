package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import common.util.Path;
import de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.controller.RamGame_controller;

import java.util.Observable;
import java.util.Observer;

public class RAM extends GameEntryPoint {

    public static final String             directory   = Path.getExecutionLocation() + "de/hsh/daniel/";
    private             RAMFxmlChanger     changer;
    private             boolean            initialized = false;
    private             RamGame_controller game;

    public RAM( Observer o ) {
        super( o, WindowConfig.daniel_title );
        changer = new RAMFxmlChanger( this, RAM_MainMenu_controller.fxml, new RAM_MainMenu_controller() );
    }

    @Override   //hier melden sich die Controller
    public void update( Observable o, Object arg ) {
        if ( arg instanceof String ) {
            String message = (String) arg;
            Logger.log( this.getClass() + ": arg = " + message );
            if ( message.equals( UpdateCodes.RAM.startGame ) ) {
                game = new RamGame_controller();
                changer.changeFxml( game, (String) arg );
                initialized = true;
            }
        }else{
            changer.changeFxml( o, (String) arg );
        }
    }

    @Override
    public void render(int fps) {
        if ( initialized ) {
            game.render( fps );
        }
    }

}
