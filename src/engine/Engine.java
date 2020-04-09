package engine;

import util.Grid;

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
        return generateUpdateStatus();
    }

    @Override
    public UpdateObject executeEvent(Event e) {
        return generateUpdateStatus();
    }

    private UpdateObject generateUpdateStatus() {
        return null;
    }
}
