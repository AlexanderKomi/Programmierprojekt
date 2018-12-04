package de.hsh.Julian;

import common.engine.FxModul;
import common.engine.FxmlChanger;
import common.updates.UpdateCodes;
import common.util.Logger;

import java.util.Observable;

public class LKFxmlChanger extends FxmlChanger {

    private static final String fxmlPackage = "view/";

    LKFxmlChanger( FxModul fxModul, String fxmlPath, Observable fxController ) {
        super( fxModul, fxmlPackage + fxmlPath, fxController );
    }

    @Override
    public void changeScene( String fxmlLocation, Observable controller ) {
        super.changeScene( fxmlPackage + fxmlLocation, controller );
    }

    @Override
    public void changeFxml( Observable o, String msg ) {
        switch (msg) {
            case UpdateCodes.LK.gameMenu:
                this.changeScene( LKStart.fxml, o );
                break;
            case UpdateCodes.LK.gameOver:
                this.changeScene( LKEnd.fxml, o );
                break;
            default:
                Logger.log( this.getClass() + ": changeFxml in PacManFxmlCanger: default" );
        }
    }
}
