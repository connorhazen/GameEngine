package engine;

import util.Action;
import util.SimpleAction;

public interface EngineAPI {
  UpdateObject getGrid();

  UpdateObject executeAction(Action action);

}
