package game.engine.gameHandlers;

import game.parse.XMLException;
import game.util.Action;
import game.util.Grid;
import java.util.List;

public interface GameHandler {

  void addEvent(Action action, List<String> operation, List<String> interaction, List<String> rules,
      Action ifExecuteEvent, Action ifNoExecuteEvent) throws XMLException;

  Action getNextEvent();


  Grid generateUpdatedGrid(Grid currentGrid, Action e);
}
