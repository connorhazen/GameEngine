package game.util;

import game.engine.animationHandlers.GameAnimation;
import game.util.Action;
import game.util.Grid;

public interface UpdateObject {

  Grid getGrid();

  GameAnimation getAnimation();

  boolean getGameLost();

  void setGameLost(boolean set);

  boolean getGameWon();

  void setGameWon(boolean set);

  boolean getGameRunning();

  Action getNextAction();
}
