package game.engine;


import game.engine.animationHandlers.GameAnimation;
import game.util.Grid;
import game.util.Action;

public class UpdateStatus implements UpdateObject {
    private Grid gridStatus;
    private GameAnimation animationStatus;
    private boolean gameLost;
    private boolean gameWon;
    private Action nextAction;

    public UpdateStatus(Grid g, GameAnimation a, boolean won, boolean lost, Action ev){
        gridStatus = g;
        animationStatus = a;
        gameWon = won;
        gameLost = lost;
        nextAction = ev;
    }

    public UpdateStatus(Grid g){
        this(g, null, false, false, null);

    }

    @Override
    public Grid getGrid() {
        return gridStatus;
    }

    @Override
    public GameAnimation getAnimation() {
        return animationStatus;
    }

    @Override
    public boolean getGameLost() {
        return gameLost;
    }

    @Override
    public boolean getGameWon() {
        return gameWon;
    }

    @Override
    public void setGameLost(boolean set) {
        gameLost = set;
    }

    @Override
    public void setGameWon(boolean set) {
        gameWon = set;
    }

    @Override
    public boolean getGameRunning() {
        return !(gameLost || gameWon);
    }

    @Override
    public Action getNextAction() {
        return nextAction;
    }
}
