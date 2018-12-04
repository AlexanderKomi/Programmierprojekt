package de.hsh.Julian;

import common.config.WindowConfig;
import common.util.Logger;
import de.hsh.dennis.model.NpcLogic.SpawnTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static common.util.Path.getExecutionLocation;

public class Leertastenklatsche implements Observer {


    static final String     location = getExecutionLocation() + "de/hsh/Julian/";
    private      SpawnTimer timer    = new SpawnTimer();

    private int score = 0;

    private      TheDude           thedude;
    private      ArrayList<Enemy>  enemyList       = new ArrayList<>();

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
    }

    void render( Canvas gc ) {
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
                Enemy e = Enemy.createEnemy();
                e.addObserver( this );
                thedude.addCollidingActor( e );
                enemyList.add( e );
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

    void parseInput( String code ) {
        if ( code.equals( "LEFT" ) ) {
            if ( !thedude.turnedleft ) {
                thedude.swapImage();
                thedude.turnedleft = true;
            }
        }
        else if ( code.equals( "RIGHT" ) ) {
            if ( thedude.turnedleft ) {
                thedude.swapImage();
                thedude.turnedleft = false;
            }
        }
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof Enemy ) {
            Enemy e = (Enemy) o;
            for ( Enemy enemy : this.enemyList ) {
                Logger.log( this.getClass() + ": Searched id : " + e.id + " Enemy id : " + enemy.id );
                if ( enemy.id == e.id ) {
                    this.thedude.getCollisionActors().remove( enemy );
                    enemyList.remove( enemy );
                    Logger.log( this.getClass() + ": Found enemy with same id" );
                    if ( enemy.getPos()[ 0 ] <= thedude.getPos()[ 0 ] ) {
                        if ( thedude.turnedleft ) {
                            score++;
                        }
                    }
                    else if ( enemy.getPos()[ 0 ] > thedude.getPos()[ 0 ] ) {
                        if ( !thedude.turnedleft ) {
                            score++;
                        }
                    }
                    return;
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        this.score = score;
    }
}
