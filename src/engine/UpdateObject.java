package engine;

import util.Grid;
import util.UserAction;

public interface UpdateObject {
  Grid getGrid();
  GameAnimation getAnimation();
  boolean getLevelOver();
  UserAction getNextUserAction();
}
