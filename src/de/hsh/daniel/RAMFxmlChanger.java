package de.hsh.daniel;

import common.engine.FxModul;
import common.engine.FxmlChanger;

import java.util.Observable;

/**
 * Created by yy9-mys-u1 on 23.11.18.
 */
public class RAMFxmlChanger extends FxmlChanger {

    public RAMFxmlChanger(FxModul fxModul, String fxmlPath, Observable fxController) {
        super(fxModul, fxmlPath, fxController);
    }

    @Override
    public void changeFxml(Observable o, String msg) {

    }
}
