package engine;

import util.Grid;
import util.Action;

public interface UpdateObject {
  Grid getGrid();
  GameAnimation getAnimation();
  boolean getLevelOver();
  Action getNextAction();
}
