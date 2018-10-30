package de.hsh.alexander.engine.game;

import java.util.ArrayList;
import java.util.Collections;

public class Games extends ArrayList<Game> {

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