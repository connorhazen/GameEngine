package engine;

import util.Action;

public interface EngineAPI {
  UpdateObject getGrid();

  UpdateObject executeAction(Action action);

}
