package de.hsh.alexander.engine.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    public final void addAll( Game... g ) {
        Collections.addAll( this, g );
    }

    @Override
    public Game get( int index ) {
        return super.get( index );
    }
}