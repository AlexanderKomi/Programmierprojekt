package common.config;


import de.hsh.alexander.game.PacManCoop;

import java.util.HashMap;

/**
 * This class is used for referencing a game, by the author.
 * Easier to handle...
 *
 * @author Alex
 */
public class Games {

    public static final HashMap<String, Class> games = new HashMap<>();
    public static final String[]               names = new String[] {
            "Alex",
            "Kevin",
            "Julian",
            "Daniel",
            "Amir",
            "Dennis"
    };

    public Games() {
        fillMap();
    }

    private void fillMap() {
        games.put( names[ 0 ], PacManCoop.class );
    }

}
