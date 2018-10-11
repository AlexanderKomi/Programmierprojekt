package de.hsh.alexander.examples;

import de.hsh.alexander.engine.FXGameContainer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExampleFXGameContainer extends FXGameContainer {

    public ExampleFXGameContainer() {}

    @Override
    public void startContainer( String[] args ) { launch( args ); }

    @Override
    public void startContainer() {
        launch();
    }


    /**
     * Updates every frame drawn.
     */
    @Override
    public void render() {
        //Logger.log("Rendering...");
    }

    @Override
    protected Stage configGui( Stage primaryStage ) {

        this.setCanvas( new Canvas( 400, 300 ) );

        GraphicsContext gc = this.getCanvas().getGraphicsContext2D();
        gc.setFill( Color.CORAL );
        gc.fillRect( 75, 75, 100, 100 );

        Scene s = new Scene( new Group( this.getCanvas() ) );

        primaryStage.setTitle( "Example" );
        primaryStage.setScene( s );
        primaryStage.setResizable( false );

        return primaryStage;
    }

    // ----------------------------------- GETTER & SETTER  -----------------------------------

}
