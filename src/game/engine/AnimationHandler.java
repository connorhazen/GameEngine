package game.engine;

import game.util.Grid;

public interface AnimationHandler {
    GameAnimation getAnimation(Grid currentGrid, Grid updatedGrid);
}
