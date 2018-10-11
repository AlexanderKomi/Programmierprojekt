package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExampleFXGameContainer extends FXGameContainer {

    @Override
    public void initGame( String[] args ) {
        launch( args );
    }

    @Override
    public void initGame() {
        launch();
    }


    @Override
    public void render() {
        //Logger.log("Rendering...");
    }

    public Stage configGui( Stage primaryStage ) {

        this.setCanvas( new Canvas( 400, 300 ) );

        GraphicsContext gc = this.getCanvas().getGraphicsContext2D();
        gc.setFill( Color.CORAL );
        gc.fillRect( 75, 75, 100, 100 );

        Group g = new Group();
        g.getChildren().addAll( this.getCanvas() );

        Scene s = new Scene( g );
        primaryStage.setScene( s );
        primaryStage.setResizable( false );
        return primaryStage;
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
