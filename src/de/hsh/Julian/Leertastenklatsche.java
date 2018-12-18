package de.hsh.Julian;

import common.config.WindowConfig;
import common.util.Logger;
import common.util.PlaySound;
import de.hsh.dennis.model.NpcLogic.SpawnTimer;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Leertastenklatsche extends Observable implements Observer {


    static final         String     location = "/de/hsh/Julian/";
    private static final SpawnTimer timer    = new SpawnTimer();

    private int              score     = 0;
    private int              leben     = 3;
    private boolean          gamedone  = false;
    private TheDude          thedude;
    private ArrayList<Enemy> enemyList = new ArrayList<>();

    public Leertastenklatsche() {
        initGame();
    }

    private void initGame() {
        thedude = new TheDude( WindowConfig.window_width / 2 - 75,
                               WindowConfig.window_height * 0.73
        );
        thedude.setSpeed( 0 );
    }

    void render( Canvas gc ) {
        if ( gamedone ) {
            return;
        }
        createNewEnemies();
        updateEnemies();
        Platform.runLater( () -> {
            renderScore( gc );
            enemyList.forEach( enemy -> {
                enemy.draw( gc );
            } );
            thedude.draw( gc );
        } );
    }

    private void createNewEnemies() {
        if ( timer.elabsedTime() > 2.0d - score / 50 ) {
            timer.resetTimer();
            final Enemy e = Enemy.createEnemy();
            e.addObserver( this );
            thedude.addCollidingActor( e );
            enemyList.add( e );
        }
    }

    private void updateEnemies() {
        for ( Enemy enemy : enemyList ) {
            if ( enemy.getX() > WindowConfig.window_width / 2 ) {
                enemy.setPos( enemy.getX() - 1.0 - score / Math.sqrt( score + 1 ), enemy.getY() );
            }
            else {
                enemy.setPos( enemy.getX() + 1 + score / Math.sqrt( score + 1 ), enemy.getY() );
            }
        }
    }

    private void renderScore( Canvas canvas ) {
        GraphicsContext gc         = canvas.getGraphicsContext2D();
        String          pointsText = "LEERTASTENKLATSCHE\nGegner abgewehrt: " + (score) + "\nVerbleibende Leben: " + (leben);
        //Logger.log(getClass()+" Score: "+score);

        gc.fillText( pointsText, 360, 36 );
        gc.strokeText( pointsText, 360, 36 );
    }

    void parseInput( final String code ) {
        switch ( code ) {
            case "LEFT":
                if ( !thedude.turnedleft ) {
                    thedude.swapImage();
                    thedude.turnedleft = true;
                }
                break;
            case "RIGHT":
                if ( thedude.turnedleft ) {
                    thedude.swapImage();
                    thedude.turnedleft = false;
                }
                break;
            //EASTEREGG ;-))
            case "SPACE":
                PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\cat.wav" );
                break;
        }
    }

    @Override
    public void update( Observable o, Object arg ) {
        if ( gamedone ) {
            Logger.log( this.getClass() + ": Game should be done, but event is still fired. : " + o + ", " + arg );
            return;
        }
        if ( o instanceof Enemy ) {
            Enemy e = (Enemy) o;
            for ( Enemy enemy : this.enemyList ) {
                if ( enemy.id == e.id ) {
                    //Logger.log( this.getClass() + ": Searched id : " + e.id + " Enemy id : " + enemy.id );

                    e.deleteObservers();
                    enemy.deleteObservers();

                    this.thedude.getCollisionActors().remove( enemy );
                    enemyList.remove( enemy );

                    //Logger.log( this.getClass() + ": Found enemy with same id" );
                    if ( enemy.getPos()[ 0 ] <= thedude.getPos()[ 0 ] ) {
                        if ( thedude.turnedleft ) {
                            score++;
                            PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\collision.wav" );
                        }
                        else {
                            leben--;
                            PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\hit.wav" );
                            gameOver();
                        }
                    }
                    else if ( enemy.getPos()[ 0 ] > thedude.getPos()[ 0 ] ) {
                        if ( !thedude.turnedleft ) {
                            score++;
                            PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\collision.wav" );
                        }
                        else {
                            leben--;
                            PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\hit.wav" );
                            gameOver();
                        }
                    }
                    enemy = null;
                    e = null;

                    return;
                }
            }
        }


    }

    private void gameOver() {

        if ( leben <= 0 && !gamedone ) {
            gamedone = true;
            PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\noo.wav" );
            for ( Enemy e : this.enemyList ) {
                e.deleteObservers();
            }
            this.enemyList.removeAll( this.enemyList );
            setChanged();
            notifyObservers( "gameover" );
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore( int score ) {
        this.score = score;
    }
}
