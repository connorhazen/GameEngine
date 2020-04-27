package game.engine.gameHandlers;

import game.parse.XMLException;
import game.util.Action;
import game.util.Grid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseGameHandler implements GameHandler {

  private Map<Action, Event> eventMappings;
  private Action nextEvent;

  public BaseGameHandler() {
    eventMappings = new HashMap<>();
    nextEvent = null;
  }

  @Override
  public void addEvent(Action action, List<String> operation, List<String> interaction,
      List<String> rules, Action ifExecuteEvent, Action ifNoExecuteEvent) throws XMLException {
    eventMappings.put(action,
        new GridEvent(operation, interaction, rules, ifExecuteEvent, ifNoExecuteEvent));
  }

  @Override
  public Action getNextEvent() {
    Action retVal = nextEvent;
    nextEvent = null;
    return retVal;
  }

  @Override
  public Grid generateUpdatedGrid(Grid currentGrid, Action action) {
    if (eventMappings.containsKey(action)) {
      Event eventToPerform = eventMappings.get(action);
      Grid updatedGrid = eventToPerform.execute(currentGrid, action);
      nextEvent = eventToPerform.getNextAction();
      return updatedGrid;
    } else {
      nextEvent = null;
      return currentGrid;
    }
  }
}
