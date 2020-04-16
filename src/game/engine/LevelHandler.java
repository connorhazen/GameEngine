package game.engine;

import game.util.Grid;

public interface LevelHandler {
    Grid initializeGrid();

    boolean determineStatus();
}
