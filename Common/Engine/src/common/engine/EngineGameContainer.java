package common.engine;

import common.engine.components.game.GameEntryPoints;
import javafx.application.Application;

import java.util.Observable;
import java.util.Observer;

public abstract class EngineGameContainer extends Application
        implements GameContainerInterface {

    protected static GameEntryPoints gameEntryPoints = new GameEntryPoints(); // Tracks all the gameEntryPoints
    private boolean fxApplicationLaunched = false;
    protected final Java2DEngine engine;

    EngineGameContainer() {
        this.engine = new Java2DEngine( this );// This must be in every class, which is an FXGameContainer.
    }

    @Override
    public void render( final int fps ) {
        gameEntryPoints.render( fps );
    }

    public abstract GameEntryPoints createGames(Observer o );
    public abstract void update(Observable observable, Object arg );
    protected abstract void beforeStoppingContainer();

    public boolean containsGame( String gameName ) {
        return gameEntryPoints.contains( gameName );
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public boolean isLaunched() {
        return this.fxApplicationLaunched;
    }
    protected void setLaunched() {
        this.fxApplicationLaunched = true;
    }
}
