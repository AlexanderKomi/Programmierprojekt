package de.hsh.daniel;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.MenuCodes;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.controller.RAM_winScreen_controller;
import de.hsh.daniel.controller.RamGame_controller;

import java.util.Observable;


public class RAMFxmlChanger extends FxmlChanger {

    RAMFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {
        if (msg.equals(UpdateCodes.RAM.startGame)) {
            changeScene(RamGame_controller.fxml, o);

        } else if (msg.equals(UpdateCodes.RAM.mainMenu) || msg.equals(UpdateCodes.RAM.quit)) {
            changeScene(RAM_MainMenu_controller.fxml, o);

        } else if (msg.equals(MenuCodes.exitToMainGUI)) {
            changeScene("common/gui/P3_Gui.fxml", o);

        } else if (msg.equals(UpdateCodes.RAM.p1Win)) {
            changeScene(RAM_winScreen_controller.fxmlP1Win, o);

        } else if (msg.equals(UpdateCodes.RAM.p2Win)) {
            changeScene(RAM_winScreen_controller.fxmlP2Win, o);

        } else if (msg.equals(UpdateCodes.RAM.tie)) {
            changeScene(RAM_winScreen_controller.fxmlTie, o);
        } else {
            Logger.log(this.getClass() + ": fxml not found");
        }

    }

}
