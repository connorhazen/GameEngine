package game.engine;

import game.util.Action;
import game.util.Grid;
import game.util.UpdateObject;

public interface EngineAPI {

  UpdateObject getGrid();

  void setGrid(Grid g);

  UpdateObject executeAction(Action action);

  void intializegrid();

}
