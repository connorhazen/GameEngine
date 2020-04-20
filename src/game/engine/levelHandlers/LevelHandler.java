package game.engine.levelHandlers;

import game.engine.UpdateObject;
import game.util.Grid;
import java.util.List;

public interface LevelHandler {
    Grid initializeGrid();

    void determineStatus(UpdateObject uo, Grid g);

    void setInitialGridMaker(List<String> args);

    void setWinCondition(List<String> args);

    void setLoseCondition(List<String> args);
}
