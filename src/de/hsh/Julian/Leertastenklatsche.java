package de.hsh.Julian;

import common.config.WindowConfig;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

import static common.util.Path.getExecutionLocation;

public class Leertastenklatsche {


    private int               score        = 0;
    private Sprite            thedude      = new Sprite();
    private String            location     = getLocation();
    private ArrayList<String> input        = new ArrayList<>();
    private ArrayList<Sprite> enemyList    = new ArrayList<>();

    public Leertastenklatsche(){

        thedude.setImage( location + "/thedude.png" );
        thedude.setPosition(WindowConfig.window_width/2, WindowConfig.window_height*0.6);
        collisionDetection();
        createEnemies();
        parseInput( input );
    }



    private void collisionDetection() {
        for ( Sprite enemy : enemyList ) {
            if ( thedude.intersects( enemy ) ) {
                score++;
            }
        }
    }

    // Gegner erstellen und zufällig links oder rechts starten lassen
    private void createEnemies() {
        for ( int i = 0 ; i < 15 ; i++ ) {
            Sprite enemyvirus = new Sprite();
            enemyvirus.setImage( location + "/enemyvirus.png" );
            /*double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;*/
            double px=WindowConfig.window_width-enemyvirus.getWidth();
            double rng=Math.random();
            if(rng<0.5)
                px=0;
            //Logger.log(this.getClass() + ": rng=" + rng);
            enemyvirus.setPosition( px, WindowConfig.window_height*0.6 );

            enemyList.add( enemyvirus );
        }
    }

    void render(GraphicsContext gc){
        parseInput( getInput() );

        for ( Sprite enemy : enemyList ){

            enemy.render( gc );

        }

        String pointsText = "LEERTASTENKLATSCHE\nGegner abgewehrt: " + (100 * score);
        //Logger.log(getClass()+" Score: "+score);

        gc.fillText( pointsText, 360, 36 );
        gc.strokeText( pointsText, 360, 36 );

        thedude.update( 10 );
        thedude.render( gc );
    }

    void parseInput( ArrayList<String> input ) {
        double v = 0.5;
        thedude.setVelocity( 0, 0 );
        if ( input.contains( "LEFT" ) ) {
            thedude.addVelocity( -v, 0 );
        }
        if ( input.contains( "RIGHT" ) ) {
            thedude.addVelocity( v, 0 );
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

    public void setScore(int score) {
        this.score = score;
    }

    public Sprite getThedude() {
        return thedude;
    }

    public void setThedude(Sprite thedude) {
        this.thedude = thedude;
    }

    private String getLocation() {
        return getExecutionLocation() + "de/hsh/Julian";
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getInput() {
        return input;
    }

    public void setInput(ArrayList<String> input) {
        this.input = input;
    }

    public ArrayList<Sprite> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(ArrayList<Sprite> enemyList) {
        this.enemyList = enemyList;
    }
}
