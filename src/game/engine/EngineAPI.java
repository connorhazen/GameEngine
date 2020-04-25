package game.engine;

import game.util.Action;
import game.util.Grid;

public interface EngineAPI {
  UpdateObject getGrid();

  void setGrid(Grid g);

  UpdateObject executeAction(Action action);

  void intializegrid();

}
