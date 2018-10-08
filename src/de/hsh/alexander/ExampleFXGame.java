package de.hsh.alexander;

import de.hsh.alexander.engine.FXGame;
import javafx.stage.Stage;

public class ExampleFXGame extends FXGame {

    public ExampleFXGame() {
        super();
        this.getEngine().setGame( this );
    }

    public Stage configGui( Stage primaryStage ) {
        return primaryStage;
    }

    @Override
    public void render() {
        System.out.println( "Render" );
    }


}
