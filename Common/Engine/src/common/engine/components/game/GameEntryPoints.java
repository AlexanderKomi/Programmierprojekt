package common.engine.components.game;

import java.util.ArrayList;
import java.util.Collections;

public final class GameEntryPoints extends ArrayList<GameEntryPoint> {

    public GameEntryPoints() {
    }

    public GameEntryPoints( GameEntryPoint... gameEntryPoints ) {
        this.addAll( gameEntryPoints );
    }

    private void addAll( GameEntryPoint... g ) {
        Collections.addAll( this, g );
    }

    public GameEntryPoint get( final String name ) {
        for ( GameEntryPoint g : this ) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public boolean contains( final Object o ) {
        if ( o instanceof GameEntryPoint ) {
            final GameEntryPoint g        = (GameEntryPoint) o;
            final String         gameName = g.getName();
            for ( GameEntryPoint gameEntryPoint : this ) {
                if ( gameEntryPoint.getName().equals( gameName ) ) {
                    return true;
                }
            }
            return false;
        } else if (o instanceof String) {
            return this.contains((String) o);
        }
        return false;
    }

    public boolean contains( final String gameName ) {
        for ( GameEntryPoint g : this ) {
            if (g.getName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    public synchronized void render( final int fps ) {
        this.forEach( game -> game.render( fps ) );
    }

    public GameEntryPoints[] toArray() {
        return (GameEntryPoints[]) super.toArray();
    }

    public ArrayList<String> getNames() {
        final ArrayList<String> temp = new ArrayList<>();
        this.forEach(game -> temp.add(game.getName()));
        return temp;
    }
}