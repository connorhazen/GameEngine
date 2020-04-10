package engine;

import util.Grid;
import util.Action;
import util.SimpleAction;

public interface GameHandler {

    void addEvent(Action action, String operation, String interactions, String rules);

    Action getNextEvent();

    Grid generateUpdatedGrid(Grid currentGrid, Action e);
}
