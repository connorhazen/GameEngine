package game.engine.animationHandlers;

import game.util.Grid;

public interface AnimationHandler {

  GameAnimation getAnimation(Grid currentGrid, Grid updatedGrid);
}
