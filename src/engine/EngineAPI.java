package engine;

import util.UserAction;

public interface EngineAPI {
  UpdateObject getGrid();

  UpdateObject executeAction(UserAction action);

}
