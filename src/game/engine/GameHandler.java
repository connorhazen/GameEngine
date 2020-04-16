package game.engine;

import game.util.Grid;
import game.util.Action;

public interface GameHandler {

    void addEvent(Action action, String operation, String interactions, String rules, Action nextAction);

    Action getNextEvent();

    Grid generateUpdatedGrid(Grid currentGrid, Action e);
}
