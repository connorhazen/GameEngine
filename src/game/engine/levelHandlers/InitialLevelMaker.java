package game.engine.levelHandlers;

import game.util.Grid;
import game.util.Grid2dArray;
import game.util.MutableGrid;

public interface InitialLevelMaker {

  MutableGrid execute(int height, int width);
  MutableGrid execute(MutableGrid g);

}
