package de.hsh.kevin;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import common.updates.UpdateCodes;
import de.hsh.alexander.engine.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TIController extends Game implements Initializable {

    private KevinMenu gameMenu;
    private KevinGame game;

    public TIController(Observer o) {
	super(o, UpdateCodes.TunnelInvader.gameName);
	if (!loadMenuFXML()) { // In case FXML could not be loaded, a default pane is set
	    this.setGameContentPane(new Pane());
	}
	loadGame();
    }

    private boolean loadGame() {
	this.game = new KevinGame(this);
	return true;
    }

    private boolean loadMenuFXML() {
	try {
            this.gameMenu = new KevinMenu( this );
            VBox node = FXMLLoader.load( KevinMenu.class.getResource( "res/TIMenu.fxml" ) );

            this.gameMenu.setMenuPane( node );
            this.gameMenu.addObserver(this);
            this.setGameContentPane( this.gameMenu.getMenuPane() );
            return true;
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        return false;
	
	//this.gameMenu = new KevinMenu(this);
	//return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub

    }

    @Override
    public void update(Observable o, Object arg) {
	// TODO Auto-generated method stub

    }
    
    public void render() {
	
    }

}
