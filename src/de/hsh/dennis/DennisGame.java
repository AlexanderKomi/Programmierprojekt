package de.hsh.dennis;

import common.config.WindowConfig;
import common.engine.components.game.Game;
import common.events.KeyEventManager;
import common.events.MouseEventManager;
import de.hsh.dennis.controller.*;

import java.util.Observable;
import java.util.Observer;


public class DennisGame extends Game {

    private DennisFxmlChanger changer;

    private final String gameName = "DDos Defender";

    public DennisGame(Observer o){
        super(o, WindowConfig.dennis_title);
        changer = new DennisFxmlChanger(this, "view/mainMenu.fxml", new MainMenu_controller());

    }


    @Override
    public void update( KeyEventManager keyEventManager, Object arg ) {

    }

    @Override
    public void update( MouseEventManager mouseEventManagerManager, Object arg ) {

    }

    @Override
    public void render() {

    }

    public String getName(){
        return gameName;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Level_controller) {
            changer.handle_Level_controller((String) arg);
        } else if (o instanceof BreakMenu_controller) {
            changer.handle_BreakMenu_controller((String) arg);
        } else if (o instanceof LevelMenu_controller) {
            changer.handle_LevelMenu_controller((String) arg);
        } else if (o instanceof MainMenu_controller) {
            changer.handle_MainMenu_controller((String) arg);
        } else if (o instanceof Tutorial_controller) {
            changer.handle_Tutorial_controller((String) arg);
        }
    }

}
