package de.hsh.Julian;

import common.config.WindowConfig;
import de.hsh.dennis.model.NpcLogic.SpawnTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static common.util.Path.getExecutionLocation;

public class Leertastenklatsche {


    private      int               score           = 0;
    private      TheDude           thedude;
    static final String            location        = getExecutionLocation() + "de/hsh/Julian/";
    private      ArrayList<String> input           = new ArrayList<>();
    private      ArrayList<Enemy>  enemyList       = new ArrayList<>();
    private      boolean           actorturnedleft = true;
    private      SpawnTimer        timer           = new SpawnTimer();

    public Leertastenklatsche() {
        try {
            initGame();
        }
        catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    private void initGame() throws FileNotFoundException {
        thedude = new TheDude( WindowConfig.window_width / 2,
                               WindowConfig.window_height * 0.4
        );
        thedude.setSpeed( 0 );
        collisionDetection();
        enemyList.add( Enemy.createEnemy() );
        parseInput( input );
    }


    private void collisionDetection() {
        score += this.enemyList
                .stream()
                .filter( enemy -> thedude.doesCollide( enemy ) )
                .count();
    }


    void render( Canvas gc ) {
        parseInput( getInput() );
        createNewEnemies();
        updateEnemies();
        enemyList.forEach( enemy -> enemy.draw( gc ) );
        thedude.draw( gc );
        renderScore( gc );
    }

    private void createNewEnemies() {
        if ( timer.elabsedTime() > 2.0d ) {
            timer.resetTimer();
            try {

                enemyList.add( Enemy.createEnemy() );
            }
            catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
        }
    }

    private void updateEnemies() {
        for ( Enemy enemy : enemyList ) {
            if ( enemy.getX() > WindowConfig.window_width / 2 ) { enemy.setPos( enemy.getX() - 1, enemy.getY() ); }
            else { enemy.setPos( enemy.getX() + 1, enemy.getY() ); }
        }
    }

    private void renderScore( Canvas canvas ) {
        GraphicsContext gc         = canvas.getGraphicsContext2D();
        String          pointsText = "LEERTASTENKLATSCHE\nGegner abgewehrt: " + (100 * score);
        //Logger.log(getClass()+" Score: "+score);

        gc.fillText( pointsText, 360, 36 );
        gc.strokeText( pointsText, 360, 36 );
    }

    private void parseInput( ArrayList<String> input ) {
        thedude.setSpeed( 0 );
        if ( input.contains( "LEFT" ) ) {
            if ( !actorturnedleft ) {
                try {
                    thedude.setCurrentImage( location + "/thedude.png" );
                }
                catch ( FileNotFoundException e ) {
                    e.printStackTrace();
                }
                actorturnedleft = true;

            }
            //thedude.addVelocity( -v, 0 );
        }
        if ( input.contains( "RIGHT" ) ) {
            if ( actorturnedleft ) {
                try {
                    thedude.setCurrentImage( location + "/thedude_turned.png" );
                }
                catch ( FileNotFoundException e ) {
                    e.printStackTrace();
                }
                actorturnedleft = false;

            }
            //thedude.addVelocity( v, 0 );
        }
        /*if ( input.contains( "UP" ) ) {
            thedude.addVelocity( 0, -v );
        }
        if ( input.contains( "DOWN" ) ) {
            thedude.addVelocity( 0, v );
        }*/
    }

    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        this.score = score;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public void setInput( ArrayList<String> input ) {
        this.input = input;
    }
}
