package de.hsh.amir.logic;

import common.util.PlaySound;
import de.hsh.kevin.logic.Score;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public final class AmirGame {
    private Spielfigur    spielfigur;
    private Score         points;
    private GegnerManager gegnerManager;
    private Canvas        canvas;
    private int           timer;


    public AmirGame( Canvas canvas, Score points ) {
        this.points = points;
        this.canvas = canvas;
    }

    /**
     * Inizialisert die Spielfigur auf in die Mitte der Canvas.
     */
    private void initializePlayer() {
        this.spielfigur = new Spielfigur();
        spielfigur.setPos( canvas.getWidth() / 2, canvas.getHeight() - 100 );
    }

    public void render( final int fps ) {
        deleteOutOfBoundsEnemies( canvas.getWidth(), canvas.getHeight() );
        timer++;
        if ( timer == 120 ) {
            gegnerManager.erstelleGegner( 4 );
            timer = 0;
        }
        collisionGegnerSpieler();
        gegnerManager.move( canvas );
        draw();
    }

    /**
     * Löscht alle Gegner die sich nicht mehr im sichtbaren Bereich befinden.
     */
    private void deleteOutOfBoundsEnemies( final double canvasWidth, final double canvasHeight ) {
        ArrayList<Gegner> temp     = gegnerManager.getGegnerListe();
        ArrayList<Gegner> toRemove = new ArrayList<>();

        for ( Gegner gegner : temp ) {
            if ( !gegner.isInBounds( canvasWidth, canvasHeight + gegner.getHeight() ) ) {
                toRemove.add( gegner );
            }
        }
        temp.removeAll( toRemove );
        gegnerManager.setGegnerListe( temp );
    }

    /**
     * Spielt einen Sound ab.
     */
    private static void playSound() {
        PlaySound.playSound( "/de/hsh/amir/resources/clickSound.mp3", true );
    }


    /**
     * Collisionsabfrage.
     * Punkntestand wird um 1 erhöht, wenn ein Gegner eingesammelt wird.
     */
    private void collisionGegnerSpieler() {
        ArrayList<Gegner> toRemove = new ArrayList<Gegner>();
        for ( Gegner gegner : gegnerManager.getGegnerListe() ) {

            if ( spielfigur.doesCollide( gegner ) ) {
                points.increase();
                playSound();
                toRemove.add( gegner );
            }

        }

        for ( Gegner gegner : toRemove ) {
            gegnerManager.remove( gegner );
        }
    }


    /**
     * setze Spiel zurück und initialisiert es neu
     */
    public void reset() {
        initializePlayer();
        gegnerManager = new GegnerManager();
        points.setScore( 0 );
    }

    public void onKeyPressed( KeyEvent event ) {
        this.spielfigur.move( event );
    }

    /**
     * Zeichnet den Spieler und die Gegner auf die Canvas
     */
    private void draw() {
        clearCanvasForPlayer();
        spielfigur.draw( canvas );
        gegnerManager.draw( canvas );
    }

    /**
     * Radiert die Canvas komplett für den Weg der Spielfigur
     */
    private void clearCanvasForPlayer() {
        canvas.getGraphicsContext2D().clearRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
    }
}
