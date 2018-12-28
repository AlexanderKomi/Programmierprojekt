package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.UpdateCodes;
import common.util.Logger;
import common.util.Path;
import de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.controller.RAM_winScreen_controller;
import de.hsh.daniel.controller.RamGame_controller;
import javafx.application.Platform;

import java.util.Observable;
import java.util.Observer;

public final class RAM extends GameEntryPoint {

    public static final String directory = Path.getExecutionLocation() + "de/hsh/daniel/";
    private RAMFxmlChanger changer;
    private boolean initialized = false;
    private RamGame_controller game;
    private RAM_MainMenu_controller ramMenu;
    private RAM_winScreen_controller winScreen;

    public RAM(Observer o) {
        super(o, WindowConfig.daniel_title);
        changer = new RAMFxmlChanger(this, RAM_MainMenu_controller.fxml, new RAM_MainMenu_controller());
    }

    @Override   //hier melden sich die Controller
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            String message = (String) arg;

            Logger.log(this.getClass() + ": arg = " + message);

            if (message.equals(UpdateCodes.RAM.startGame)) {

                game = new RamGame_controller();
                changer.changeFxml(game, message);
                initialized = true;

            } else if (message.equals(UpdateCodes.RAM.mainMenu)) {

                ramMenu = new RAM_MainMenu_controller();
                initialized = false;
                changer.changeFxml(ramMenu, message);


            } else if (message.equals(UpdateCodes.DefaultCodes.exitToMainGUI) || message.equals(UpdateCodes.RAM.quit)) {
                Logger.log("exit reached");
                initialized = false;
                exitToMainGUI();

            } else if (message.equals(UpdateCodes.RAM.p1Win) || message.equals(UpdateCodes.RAM.p2Win) ||
                    message.equals(UpdateCodes.RAM.tie)) {

                winScreen = new RAM_winScreen_controller();
                initialized = false;
                changer.changeFxml(winScreen, message);


            } else {
                changer.changeFxml(o, (String) arg);
            }
        }
    }

    @Override
    public void render(final int fps) {
        if (initialized) {
            if (game != null) {
                Platform.runLater(() -> {
                    game.render(fps);
                });
            }
        }

    }

}
