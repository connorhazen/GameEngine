package game.engine;

import game.util.Grid;
import game.util.Action;

public class Engine implements EngineAPI{
    private Grid currentGrid;
    private Grid updatedGrid;
    private GameHandler currGameHandler;
    private AnimationHandler currAnimationHandler;
    private LevelHandler currLevelHandler;

    public Engine(GameHandler gameHandler, AnimationHandler animationHandler, LevelHandler levelHandler){
        currGameHandler = gameHandler;
        currAnimationHandler = animationHandler;
        currLevelHandler = levelHandler;
        currentGrid = currLevelHandler.initializeGrid();
        updatedGrid = null;
    }

    @Override
    public UpdateObject getGrid() {
        return new UpdateStatus(currentGrid);
    }

    @Override
    public UpdateObject executeAction(Action action) {
        updatedGrid = currGameHandler.generateUpdatedGrid(currentGrid, action);
        return generateUpdateStatus();
    }

    private UpdateObject generateUpdateStatus() {
        boolean status = currLevelHandler.determineStatus();
        GameAnimation animation = currAnimationHandler.getAnimation(currentGrid, updatedGrid);
        Action nextAction = currGameHandler.getNextEvent();
        UpdateObject updateStatus = new UpdateStatus(updatedGrid,animation,status, nextAction);
        currentGrid = updatedGrid;
        updatedGrid = null;
        return updateStatus;
    }
}
