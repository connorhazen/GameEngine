package game.engine.levelHandlers.endConditions;

import game.engine.gameHandlers.GameHandler;
import game.util.Grid;

public interface Condition {

  boolean checkStatus(Grid g, GameHandler lh);

}
