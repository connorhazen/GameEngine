package engine;

import util.Grid;

public interface LevelHandler {
    Grid initializeGrid();

    boolean determineStatus();
}
