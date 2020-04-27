package game.engine.gameHandlers;

import game.util.Action;
import game.util.Grid;

public interface Event {

  Grid execute(Grid currentGrid, Action a);

  Action getNextAction();
}
