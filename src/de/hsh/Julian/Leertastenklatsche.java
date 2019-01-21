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


    static final         String     location      = "/de/hsh/Julian/";
    private static final String     soundLocation = "/de/hsh/Julian/wav/";
    private static final SpawnTimer timer         = new SpawnTimer();

    private int              score     = 0;
    private int              leben     = 3;
    private boolean          gamedone  = false;
    boolean horrorWasActivated = false;
    private TheDude          thedude;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private double spawntimer;

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
        if(score >= 70)
            spawntimer= 0.2;
        else
            spawntimer = 2.0d - score / 50;

        if ( timer.elabsedTime() > spawntimer) {
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
        String          pointsText;
        if(score<70) {
            pointsText    ="LEERTASTENKLATSCHE\nGegner abgewehrt: " + (score) + "\nVerbleibende Leben: " + (leben);
            //Logger.log(getClass()+" Score: "+score);

            gc.fillText(pointsText, 360, 36);
            gc.strokeText(pointsText, 360, 36);
        }
        else{
            if(!horrorWasActivated){
                //PlaySound.playSound( "src\\de\\hsh\\Julian\\wav\\horror.wav" );
                PlaySound.playSound( soundLocation + "Kalinka.mp3" );
                leben = 70;
            }

            horrorWasActivated=true;
            pointsText = "HORRORMODUS SCORE: " + (score) + "\nKLATSCH KLATSCH KLATSCH Leben: " + (leben);

            //Logger.log(getClass()+" Score: "+score);

            gc.fillText( pointsText, 360, 36);
            gc.strokeText( pointsText, 360, 36 );

        }
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
                if ( !horrorWasActivated ) { PlaySound.playSound( soundLocation + "cat.wav" ); }
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

                    this.thedude.removeCollisionActor( enemy );
                    enemyList.remove( enemy );

                    //Logger.log( this.getClass() + ": Found enemy with same id" );
                    if ( enemy.getPos()[ 0 ] <= thedude.getPos()[ 0 ] ) {
                        if ( thedude.turnedleft ) {
                            score++;
                            if ( !horrorWasActivated ) { PlaySound.playAndResetSound( soundLocation + "collision.wav" ); }
                        }
                        else {
                            leben--;
                            if ( !horrorWasActivated ) { PlaySound.playAndResetSound( soundLocation + "hit.wav" ); }
                            gameOver();
                        }
                    }
                    else if ( enemy.getPos()[ 0 ] > thedude.getPos()[ 0 ] ) {
                        if ( !thedude.turnedleft ) {
                            score++;
                            if ( !horrorWasActivated ) { PlaySound.playAndResetSound( soundLocation + "collision.wav" ); }
                        }
                        else {
                            leben--;
                            if ( !horrorWasActivated ) { PlaySound.playAndResetSound( soundLocation + "hit.wav" ); }
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
            PlaySound.playSound( soundLocation + "noo.wav" );
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
