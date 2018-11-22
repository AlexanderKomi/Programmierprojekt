package common.engine.components.game;

import common.engine.FxModul;
import common.updates.UpdateCodes;

import java.util.Observable;
import java.util.Observer;

/***
 * Neue Game Klasse mit FxModul als Unterbau
 *
 * @author Alexander Komischke
 * @author Dennis Sellemann
 *
 * @version 2
 */
public abstract class Game extends FxModul implements IGame {

    private final String name;


    public Game(Observer container) {
        super(container);
        this.name = "- Name not set -";
    }

    public Game(Observer container, String name) {
        super(container);
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Game) {
            Game g = (Game) obj;
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

    public void addObservable(Observable observable) {
        observable.addObserver(this);
    }

    public abstract void render();

    // ----------------------------------- GETTER & SETTER  -----------------------------------

    public String getName() {
        return name;
    }

}
