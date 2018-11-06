package de.hsh.Julian;

import de.hsh.alexander.engine.game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import static de.hsh.alexander.util.Path.getExecutionLocation;

//
//

public class Leertastenklatsche extends Game {

    public Leertastenklatsche( Observer o ) {
        super( o, "Leertastenklatsche" );
        this.setGameContentPane( this.initGameContentWindow( o ) );
    }

    int score =0;
    GraphicsContext gc;

    @Override
    public void update( Observable o, Object arg ) {

    }
    public AnimationTimer createAnimationTimer(Sprite briefcase, ArrayList<Sprite> moneybagList, ArrayList<String> input){
        LongValue lastNanoTime = new LongValue( System.nanoTime() );
        return
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic

                briefcase.setVelocity(0,0);
                if (input.contains("LEFT")) {
                    Logger.log("Leertastenklatsche : Left klicked");
                }
                briefcase.addVelocity(-50,0);
                if (input.contains("RIGHT"))
                    Logger.log("Leertastenklatsche : Right klicked");
                briefcase.addVelocity(50,0);
                if (input.contains("UP"))
                    briefcase.addVelocity(0,-50);
                if (input.contains("DOWN"))
                    briefcase.addVelocity(0,50);
                //Logger.log(briefcase);

                briefcase.update(elapsedTime);

                // collision detection

                Iterator<Sprite> moneybagIter = moneybagList.iterator();
                while ( moneybagIter.hasNext() )
                {
                    Sprite moneybag = moneybagIter.next();
                    if ( briefcase.intersects(moneybag) )
                    {
                        moneybagIter.remove();
                        score++;
                    }
                }

                // render

                gc.clearRect(0, 0, 512,512);
                briefcase.render( gc );

                for (Sprite moneybag : moneybagList )
                    moneybag.render( gc );

                String pointsText = "Cash: $" + (100 * score);
                gc.fillText( pointsText, 360, 36 );
                gc.strokeText( pointsText, 360, 36 );
            }
        };
    };

    public Pane initGameContentWindow( Observer observer ) {

     //   Logger.log( "Dir : " + getExecutionLocation(),  Path.getAllFileNames(getExecutionLocation()) );
        Pane root = new Pane();

        Canvas canvas = new Canvas( 512, 512 );

        ArrayList<String> input = new ArrayList<String>();

        canvas.setOnKeyPressed( e -> {
            String code = e.getCode().toString();
            //Logger.log("KeyCode : " + code);
            if ( !input.contains(code) )
                input.add( code );
        });
        root.getChildren().add( canvas );

        root.setOnKeyReleased(
                e -> {
                    String code = e.getCode().toString();
                    input.remove( code );
                });

        gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont( theFont );
        gc.setFill( Color.GREEN );
        gc.setStroke( Color.BLACK );
        gc.setLineWidth(1);

        Sprite briefcase = new Sprite();
        StringBuilder sb = new StringBuilder(getExecutionLocation());
        //sb.deleteCharAt(0);

        String location  = sb.toString() + "de/hsh/Julian";
        briefcase.setImage( location + "/briefcase.png" );
        briefcase.setPosition(200, 0);

        ArrayList<Sprite> moneybagList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++)
        {
            Sprite moneybag = new Sprite();
            moneybag.setImage(location + "/moneybag.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            moneybag.setPosition(px,py);
            moneybagList.add( moneybag );
        }
        createAnimationTimer(briefcase, moneybagList,  input);

        return root;
    }
}
