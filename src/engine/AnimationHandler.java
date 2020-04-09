package engine;

import util.Grid;

public interface AnimationHandler {
    GameAnimation getAnimation(Grid currentGrid, Grid updatedGrid);
}
