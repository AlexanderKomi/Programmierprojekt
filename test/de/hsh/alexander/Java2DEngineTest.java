package de.hsh.alexander;

import de.hsh.alexander.examples.ExampleFXGameContainer;
import org.junit.jupiter.api.Test;

class Java2DEngineTest {

    @Test
    void startStopStressTest() {
        StressClass e = new StressClass();
        e.startContainer();
    }

    class StressClass extends ExampleFXGameContainer {

        StressClass() {
            this.getEngine().setGameContainer( this );
        }

        @Override
        public void render() {
            this.getEngine().stop();
            this.getEngine().start();
        }
    }


}
