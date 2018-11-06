package de.hsh.alexander.engine.game;

import common.KeyEventManager;

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

    @Override
    public boolean contains( Object o ) {
        if ( o instanceof Game ) {
            Game   g        = (Game) o;
            String gameName = g.getName();
            return this.stream().parallel().anyMatch( game -> game.getName().equals( gameName ) );
        }
        else if ( o instanceof String ) {
            String gameName = (String) o;
            return this.stream().parallel().anyMatch( game -> game.getName().equals( gameName ) );
        }
        return false;
    }

    public Games[] toArray() {
        return (Games[]) super.toArray();
    }

    public void addKeyEventManager( KeyEventManager keyEventManager ) {
        this.forEach( game -> {
            game.addObservable( keyEventManager );
        } );
    }

    public ArrayList<String> getNames() {
        ArrayList<String> temp = new ArrayList<>();
        this.forEach( game -> temp.add( game.getName() ) );
        return temp;
    }
}