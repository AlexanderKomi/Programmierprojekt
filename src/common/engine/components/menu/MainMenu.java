package common.engine.components.menu;

import common.engine.components.game.Game;
import common.engine.components.game.Games;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

public abstract class MainMenu extends Menu {

    public ArrayList<String> gameNames = new ArrayList<>();

    public MainMenu() {
        super();
    }

    public MainMenu(Observer sceneController, ArrayList<String> games) {
        super(sceneController);
        this.addObserver(sceneController);
        this.setGameNames(games);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void notifyObservers(Object arg) {
        this.setChanged();
        super.notifyObservers(arg);
    }

    //-------------------------------------- GETTER & SETTER --------------------------------------

    public void setGameNames(ArrayList<String> gameNames) {
        this.gameNames = gameNames;
    }

    public void setGameNames(Games games) {
        this.gameNames = games.stream().map(Game::getName).collect(Collectors.toCollection(ArrayList::new));
    }
}
