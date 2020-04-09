package engine;

import util.Grid;
import util.UserAction;

public interface Event {
    Grid execute(Grid currentGrid);

    UserAction getNextAction();
}
