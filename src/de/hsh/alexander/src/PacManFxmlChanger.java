package de.hsh.alexander.src;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;
import javafx.application.Platform;

import java.util.Observable;

public final class PacManFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "view/";

    PacManFxmlChanger( FxModul fxModul, final String fxmlPath, Observable fxController ) {
        super( fxModul, fxmlPackage + fxmlPath, fxController );
    }

    @Override
    public void changeScene( final String fxmlLocation, Observable controller ) {
        Platform.runLater( () -> {
            super.changeScene( fxmlPackage + fxmlLocation, controller );
        } );
    }

    @Override
    public void changeFxml( Observable o, final String msg ) {
        switch (msg) {
            case UpdateCodes.PacMan.startGame:
                this.changeScene( PacManGame.fxml, o );
                break;
            case UpdateCodes.PacMan.mainMenu:
                this.changeScene( PacManMenu.fxml, o );
                break;
            case UpdateCodes.PacMan.showEndScreen:
                this.changeScene( PacManEndScreen.fxml, o );
                break;
            default:
                Logger.log( this.getClass() + ": changeFxml in PacManFxmlCanger: default" );
        }
    }
}
