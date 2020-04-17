package game.engine.levelHandlers;

import game.util.Grid;
import java.util.List;

public interface LevelHandler {
    Grid initializeGrid();

    boolean determineStatus();

    void setInitialGridMaker(List<String> args);
}
