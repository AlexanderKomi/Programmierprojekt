import de.hsh.alexander.examples.ExampleFXGameContainer;

public class Main {

    public static void main( String[] args ) {
        ExampleFXGameContainer e1 = new ExampleFXGameContainer();
        if ( !ExampleFXGameContainer.isLaunched() ) {

            e1.startContainer( args );
        }
    }

}
