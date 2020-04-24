package game.engine.levelHandlers;

import game.engine.UpdateObject;
import game.engine.gameHandlers.GameHandler;
import game.util.Grid;
import java.util.List;
import java.util.logging.Level;

public interface LevelHandler {
    Grid initializeGrid();

    void determineStatus(UpdateObject uo, Grid g, GameHandler lh);

    void setInitialGridMaker(List<String> args);

    void setWinCondition(List<String> args);

    void setLoseCondition(List<String> args);
    void setSize(String width, String height);

}
