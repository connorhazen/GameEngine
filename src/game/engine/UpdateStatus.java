package game.engine;


import game.engine.animationHandlers.GameAnimation;
import game.util.Grid;
import game.util.Action;

public class UpdateStatus implements UpdateObject {
    private Grid gridStatus;
    private GameAnimation animationStatus;
    private boolean gameOverStatus;
    private Action nextAction;

    public UpdateStatus(Grid g, GameAnimation a, boolean over, Action ev){
        gridStatus = g;
        animationStatus = a;
        gameOverStatus = over;
        nextAction = ev;
    }

    public UpdateStatus(Grid g){
        gridStatus = g;
        animationStatus = null;
        gameOverStatus = false;
        nextAction = null;
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
    public boolean getLevelOver() {
        return gameOverStatus;
    }

    @Override
    public Action getNextAction() {
        return nextAction;
    }
}
