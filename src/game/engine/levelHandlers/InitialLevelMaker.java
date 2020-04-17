package game.engine.levelHandlers;

import game.util.Grid;

public interface InitialLevelMaker {

  Grid execute(int height, int width);

}
