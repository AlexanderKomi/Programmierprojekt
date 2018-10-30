package de.hsh.alexander.engine.game;

import java.util.ArrayList;
import java.util.Collection;

public class Games extends ArrayList<Game> {

    public Games() {}

    public Games( Game game ) {
        this.add( game );
    }

    public Games( Collection<Game> collection ) {
        this.addAll( collection );
    }

    public Games( Game... games ) {
        this.addAll( games );
    }

    public Game get( String name ) {
        return this.stream()
                   .parallel()
                   .filter( game -> game.getName().equals( name ) )
                   .findFirst()
                   .get();
    }

    public void addAll( Game... g ) {
        for ( Game game : g ) {
            this.add( game );
        }
    }

}