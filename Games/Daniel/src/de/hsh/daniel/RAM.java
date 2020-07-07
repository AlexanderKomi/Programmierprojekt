package de.hsh.daniel;

import common.config.WindowConfig;
import common.engine.components.game.GameEntryPoint;
import common.updates.MenuCodes;
import common.updates.UpdateCodes;
import common.util.Logger;
import de.hsh.daniel.controller.RAM_MainMenu_controller;
import de.hsh.daniel.controller.RAM_winScreen_controller;
import de.hsh.daniel.controller.RamGame_controller;
import javafx.application.Platform;

import java.util.Observable;
import java.util.Observer;

public final class RAM extends GameEntryPoint {

    private boolean initialized = false;
    private final RAMFxmlChanger changer;
    private static RamGame_controller game;
    private static RAM_MainMenu_controller ramMenu;
    private static RAM_winScreen_controller winScreen;

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
                startGame(message);
            } else if (message.equals(UpdateCodes.RAM.mainMenu)) {
                showMainMenu(message);
            } else if (message.equals(MenuCodes.exitToMainGUI) || message.equals(UpdateCodes.RAM.quit)) {
                exit(message);
            } else if (message.equals(UpdateCodes.RAM.p1Win) ||
                    message.equals(UpdateCodes.RAM.p2Win) ||
                    message.equals(UpdateCodes.RAM.tie)) {
                showEndScreen(message);
            } else {
                changer.changeFxml(o, (String) arg);
            }
        }
    }

    private void startGame(String message) {
        if (!initialized) {
            game = new RamGame_controller();
            changer.changeFxml(game, message);
            initialized = true;
        }
    }

    private void showMainMenu(String message) {
        ramMenu = new RAM_MainMenu_controller();
        initialized = false;
        changer.changeFxml(ramMenu, message);
    }

    private void showEndScreen(String message) {
        winScreen = new RAM_winScreen_controller();
        initialized = false;
        changer.changeFxml(winScreen, message);
    }

    private void exit(String message) {
        Logger.log("exit reached");
        initialized = false;
        exitToMainGUI();
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
