package engine;


import util.Grid;

public class UpdateStatus implements UpdateObject {
    private Grid gridStatus;
    private GameAnimation animationStatus;
    private boolean gameOverStatus;
    private Event nextEvent;

    public UpdateStatus(Grid g, GameAnimation a, boolean over, Event ev){
        gridStatus = g;
        animationStatus = a;
        gameOverStatus = over;
        nextEvent = ev;
    }

    public UpdateStatus(Grid g){
        gridStatus = g;
        animationStatus = null;
        gameOverStatus = false;
        nextEvent = null;
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
    public Event getNextEvent() {
        return nextEvent;
    }
}
