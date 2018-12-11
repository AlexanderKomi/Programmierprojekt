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

public class Leertastenklatsche extends Observable implements Observer {


    static final String     location = "/de/hsh/Julian/";
    private      SpawnTimer timer    = new SpawnTimer();

    private int score = 0;
    private int leben=3;
    private boolean gamedone=false;
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
        thedude = new TheDude( WindowConfig.window_width / 2 -75,
                               WindowConfig.window_height * 0.73
        );
        thedude.setSpeed( 0 );
    }

    void render( Canvas gc ) {
        createNewEnemies();
        enemyList.forEach( enemy -> {
            enemy.draw( gc );
        } );
        updateEnemies();
        thedude.draw( gc );
        renderScore( gc );
    }

    private void createNewEnemies() {
        if ( timer.elabsedTime() > 2.0d-score/50.0 ) {
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
            if ( enemy.getX() > WindowConfig.window_width / 2 ) {
                enemy.setPos( enemy.getX() - 1.0 - score / 10.0, enemy.getY() );
                enemy.rotate( 0 );
            }
            else {
                enemy.setPos( enemy.getX() + 1 + score / 10.0, enemy.getY() );
                enemy.rotate( 0 );
            }
        }
    }

    private void renderScore( Canvas canvas ) {
        GraphicsContext gc         = canvas.getGraphicsContext2D();
        String          pointsText = "LEERTASTENKLATSCHE\nGegner abgewehrt: " + (score)+"\nVerbleibende Leben: "+ (leben);
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
        //EASTEREGG ;-))
        else if ( code.equals( "SPACE" ) ) {
            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\cat.wav");
        }
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( o instanceof Enemy &&gamedone==false ) {
            Enemy e = (Enemy) o;
            for ( Enemy enemy : this.enemyList ) {
                //Logger.log( this.getClass() + ": Searched id : " + e.id + " Enemy id : " + enemy.id );
                if ( enemy.id == e.id ) {
                    this.thedude.getCollisionActors().remove( enemy );
                    enemyList.remove( enemy );
                   // Logger.log( this.getClass() + ": Found enemy with same id" );
                    if ( enemy.getPos()[ 0 ] <= thedude.getPos()[ 0 ] ) {
                        if ( thedude.turnedleft ) {
                            score++;
                            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\collision.wav");
                        }
                        else{
                            leben--;
                            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\hit.wav");
                            gameOver();
                        }
                    }
                    else if ( enemy.getPos()[ 0 ] > thedude.getPos()[ 0 ] ) {
                        if ( !thedude.turnedleft ) {
                            score++;
                            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\collision.wav");
                        }
                        else {
                            leben--;
                            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\hit.wav");
                            gameOver();
                        }
                    }
                    return;
                }
            }
        }
    }
    private void gameOver(){

        if(leben<=0 && gamedone ==false){
            gamedone=true;
            PlaySound.playSound("src\\de\\hsh\\Julian\\wav\\noo.wav");
            setChanged();
            notifyObservers("gameover");


        }
    }
    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        this.score = score;
    }
}
