package engine;

import util.Grid;
import util.Action;

public interface Event {
    Grid execute(Grid currentGrid);

    Action getNextAction();
}
