package game.engine;

import game.util.Action;

public interface EngineAPI {
  UpdateObject getGrid();

  UpdateObject executeAction(Action action);

}
