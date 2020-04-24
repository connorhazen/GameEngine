package game.engine.gameHandlers;

import game.util.Grid;
import game.util.Action;

public interface Event {
    Grid execute(Grid currentGrid, Action a);

    Action getNextAction();
}
