package engine;


import util.Grid;
import util.UserAction;

public class UpdateStatus implements UpdateObject {
    private Grid gridStatus;
    private GameAnimation animationStatus;
    private boolean gameOverStatus;
    private UserAction nextUserAction;

    public UpdateStatus(Grid g, GameAnimation a, boolean over, UserAction ev){
        gridStatus = g;
        animationStatus = a;
        gameOverStatus = over;
        nextUserAction = ev;
    }

    public UpdateStatus(Grid g){
        gridStatus = g;
        animationStatus = null;
        gameOverStatus = false;
        nextUserAction = null;
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
    public UserAction getNextUserAction() {
        return nextUserAction;
    }
}
