package common.engine.components.game;

import common.events.KeyEventManager;
import common.events.MouseEventManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Games extends ArrayList<GameEntryPoint> {

    public Games() {
    }

    public Games( GameEntryPoint gameEntryPoint ) {
        this.add( gameEntryPoint );
    }

    public Games( Collection<GameEntryPoint> collection ) {
        this.addAll(collection);
    }

    public Games( GameEntryPoint... gameEntryPoints ) {
        this.addAll( gameEntryPoints );
    }

    public void addAll( GameEntryPoint... g ) {
        Collections.addAll( this, g );
    }

    public GameEntryPoint get( String name ) {
        for ( GameEntryPoint g : this ) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    @Override
    public boolean contains(Object o) {
        if ( o instanceof GameEntryPoint ) {
            GameEntryPoint g        = (GameEntryPoint) o;
            String         gameName = g.getName();
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

    public boolean contains(String gameName) {
        for ( GameEntryPoint g : this ) {
            if (g.getName().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    public Games[] toArray() {
        return (Games[]) super.toArray();
    }

    public void addKeyEventManager(KeyEventManager keyEventManager) {
        this.forEach(game -> {
            game.addObservable(keyEventManager);
        });
    }

    public void addMouseEventManager(MouseEventManager mouseEventManager) {
        this.forEach(game -> {
            game.addObservable(mouseEventManager);
        });
    }

    public ArrayList<String> getNames() {
        ArrayList<String> temp = new ArrayList<>();
        this.forEach(game -> temp.add(game.getName()));
        return temp;
    }
}