package game.engine;

import game.engine.animationHandlers.GameAnimation;
import game.util.Grid;
import game.util.Action;

import java.io.Serializable;

public interface UpdateObject extends Serializable {

  Grid getGrid();
  GameAnimation getAnimation();
  boolean getLevelOver();
  Action getNextAction();
}
