package engine;


import util.Grid;

public class UpdateStatus implements UpdateObject {
    private Grid gridStatus;
    private GameAnimation animationStatus;
    private boolean gameOverStatus;

    public UpdateStatus(Grid g, GameAnimation a, boolean over){
        gridStatus = g;
        animationStatus = a;
        gameOverStatus = over;
    }

    public UpdateStatus(Grid g){
        gridStatus = g;
        animationStatus = null;
        gameOverStatus = false;
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
}
