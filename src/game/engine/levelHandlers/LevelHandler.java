package game.engine.levelHandlers;

import game.util.UpdateObject;
import game.engine.gameHandlers.GameHandler;
import game.util.Grid;
import java.util.List;

public interface LevelHandler {

  Grid initializeGrid();

  void determineStatus(UpdateObject uo, Grid g, GameHandler lh);

  void setInitialGridMaker(List<String> args);

  void setWinCondition(List<String> args);

  void setLoseCondition(List<String> args);

  void setSize(String width, String height);

}
