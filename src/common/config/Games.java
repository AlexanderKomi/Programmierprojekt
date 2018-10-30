package common.config;


import de.hsh.Julian.Leertastenklatsche;
import de.hsh.alexander.game.PacManCoop;
import de.hsh.alexander.util.Logger;
import de.hsh.amir.AmirsGame;
import de.hsh.daniel.RAM;
import de.hsh.dennis.DennisGame;
import de.hsh.kevin.KevinGame;

import java.util.HashMap;

/**
 * This class is used for referencing a game, by the author.
 * Easier to handle...
 *
 * @author Alex
 */
public class Games {

    public static HashMap<String, Class> games;

    public static final String[]               names = new String[] {
            "Alex",
            "Kevin",
            "Julian",
            "Daniel",
            "Amir",
            "Dennis"
    };

    public Games() {
        Games.games = fillMap();
    }

    private HashMap fillMap() {
        HashMap<String, Class> temp = new HashMap<>();
        if ( names != null ) {
            temp.put( names[ 0 ], PacManCoop.class );
            temp.put( names[ 1 ], KevinGame.class );
            temp.put( names[ 2 ], Leertastenklatsche.class );
            temp.put( names[ 3 ], RAM.class );
            temp.put( names[ 4 ], AmirsGame.class );
            temp.put( names[ 5 ], DennisGame.class );
        }
        else {
            try {
                throw new Exception( "Names is null" );
            }
            catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        return temp;
    }

    public static void main( String[] args ) {
        Games games = new Games();
        Class c     = Games.games.get( "Daniel" );
        try {
            Object o = c.newInstance();
            Logger.log( o );
        }
        catch ( InstantiationException e ) {
            e.printStackTrace();
        }
        catch ( IllegalAccessException e ) {
            e.printStackTrace();
        }
    }

}
