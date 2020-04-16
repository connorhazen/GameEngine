package game.engine;

import game.util.Grid;
import game.util.Action;

public interface UpdateObject {

  Grid getGrid();
  GameAnimation getAnimation();
  boolean getLevelOver();
  Action getNextAction();
}
