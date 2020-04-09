package engine;

import util.Grid;

public interface GameHandler {

    Event getNextEvent();

    Grid generateUpdatedGrid(Grid currentGrid, Event e);
}
