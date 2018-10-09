package de.hsh.alexander;

import de.hsh.alexander.examples.ExampleFXGameContainer;
import org.junit.jupiter.api.Test;

class Java2DEngineTest {

    @Test
    void startStopStressTest() {
        StressClass e = new StressClass();
        e.initGame();
    }

    class StressClass extends ExampleFXGameContainer {

        StressClass() {
            this.getEngine().setGame( this );
        }

        @Override
        public void render() {
            this.getEngine().stop();
            this.getEngine().start();
        }
    }


}
