package engine;

import util.Grid;
import util.UserAction;

public interface GameHandler {

    void addEvent(UserAction action, String operation, String interactions, String rules);

    UserAction getNextEvent();

    Grid generateUpdatedGrid(Grid currentGrid, UserAction e);
}
