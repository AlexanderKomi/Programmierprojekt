package de.hsh.alexander;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;

public class PacManFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "view/";

    PacManFxmlChanger( FxModul fxModul, String fxmlPath, Observable fxController ) {
        super( fxModul, fxmlPackage + fxmlPath, fxController );
    }

    @Override
    public void changeScene( String fxmlLocation, Observable controller ) {
        super.changeScene( fxmlPackage + fxmlLocation, controller );
    }

    @Override
    public void changeFxml( Observable o, String msg ) {
        switch (msg) {
            case UpdateCodes.PacMan.startGame:
                this.changeScene( PacManGame.fxml, o );
                break;
            default:
                Logger.log( this.getClass() + ": changeFxml in PacManFxmlCanger: default" );
        }
    }
}
