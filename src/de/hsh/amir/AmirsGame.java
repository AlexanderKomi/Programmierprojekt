package de.hsh.amir;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import de.hsh.amir.controller.AmirsMainMenuController;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by 424-ml6-u1 on 30.10.18.
 */
public class AmirsGame extends GameEntryPoint {

    private AmirFxmlChanger changer;

    public AmirsGame( Observer o ) {
        super(o, WindowConfig.amir_title);
        changer = new AmirFxmlChanger(this, "view/AmirsMenu.fxml", new AmirsMainMenuController());
    }


    @Override
    public void update(Observable o, Object arg ) {
        changer.changeFxml(o, (String) arg);
    }

    @Override
    public void render(int fps) {

    }
}
