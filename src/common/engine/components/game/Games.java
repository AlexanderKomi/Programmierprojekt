package common.engine.components.game;

import common.events.KeyEventManager;
import common.events.MouseEventManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Games extends ArrayList<Game> {

    public Games() {
    }

    public Games(Game game) {
        this.add(game);
    }

    public Games(Collection<Game> collection) {
        this.addAll(collection);
    }

    public Games(Game... games) {
        this.addAll(games);
    }

    public Game get(String name) {
        for (Game g : this) {
            if (g.getName().equals(name)) {
                return g;
            }
        }
        return null;
    }

    public void addAll(Game... g) {
        Collections.addAll(this, g);
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Game) {
            Game g = (Game) o;
            String gameName = g.getName();
            for (Game game : this) {
                if (game.getName().equals(gameName)) {
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
        for (Game g : this) {
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