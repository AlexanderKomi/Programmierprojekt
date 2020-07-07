package de.hsh.alexander.src.level;

import de.hsh.alexander.src.actor.level_elements.Fan;
import javafx.scene.canvas.Canvas;

import static de.hsh.alexander.src.actor.ResourcePaths.Actor.LevelElements.Backgrounds.microChip;

public final class Level2 extends PacManLevel {


    public Level2( Canvas gameCanvas ) {super( gameCanvas );}

    @Override
    public void createLevel( Canvas gameCanvas ) {
        setBackgroundImage( microChip, background_width, background_height );
        addPlayers( 500, 350,
                    250, 550 );
        addLevelElements( gameCanvas );
        addEasterEgg( gameCanvas, 500, 300 );
        createDataCoins( gameCanvas );
    }

    private void addLevelElements( Canvas gameCanvas ) {

        addLevelElement( new Fan( 200, 50 ) );
        addLevelElement( new Fan( 200, 375, 6 ) );

        fillPins( gameCanvas );
        addSMDs( gameCanvas, 235 );
    }


}