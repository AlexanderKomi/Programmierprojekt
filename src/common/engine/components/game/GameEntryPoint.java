package common.engine.components.game;

import common.engine.FxModul;
import common.updates.UpdateCodes;

import java.util.Observer;

/***
 * Neue GameEntryPoint Klasse mit FxModul als Unterbau
 *
 * @author Alexander Komischke
 * @author Dennis Sellemann
 *
 * @version 2
 */
public abstract class GameEntryPoint extends FxModul implements IGame {

    private final String name;


    public GameEntryPoint( Observer container ) {
        super(container);
        this.name = "- Name not set -";
    }

    public GameEntryPoint( Observer container, String name ) {
        super(container);
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if ( obj instanceof GameEntryPoint ) {
            final GameEntryPoint g = (GameEntryPoint) obj;
            return this.name.equals(g.name) &&
                    g.getScene().equals(this.getScene());
        }
        return false;
    }

    @Override
    public void exitToMainGUI() {
        this.setChanged();
        super.notifyObservers(UpdateCodes.DefaultCodes.exitToMainGUI);
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public String getName() {
        return name;
    }

}
