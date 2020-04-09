package engine;

import util.Grid;

public interface UpdateObject {
  Grid getGrid();
  GameAnimation getAnimation();
  boolean getLevelOver();
  Event getNextEvent();
}
