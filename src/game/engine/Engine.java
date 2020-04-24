package game.engine;

import game.engine.animationHandlers.AnimationHandler;
import game.engine.animationHandlers.GameAnimation;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.LevelHandler;
import game.parse.XMLException;
import game.util.Grid;
import game.util.Action;

public class Engine implements EngineAPI{
    private Grid currentGrid;
    private Grid updatedGrid;
    private GameHandler currGameHandler;
    private AnimationHandler currAnimationHandler;
    private LevelHandler currLevelHandler;

    public Engine(GameHandler gameHandler, AnimationHandler animationHandler, LevelHandler levelHandler) throws XMLException {
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

    @Override
    public void setGrid(Grid g){
        currentGrid = g;
    }

    private UpdateObject generateUpdateStatus() {
        Action nextAction = currGameHandler.getNextEvent();
        GameAnimation animation = currAnimationHandler.getAnimation(currentGrid, updatedGrid);
        UpdateObject updateStatus = new UpdateStatus(updatedGrid,animation,false, false, nextAction);
        currLevelHandler.determineStatus(updateStatus, updatedGrid, currGameHandler);

        currentGrid = updatedGrid;
        updatedGrid = null;
        return updateStatus;
    }
}
