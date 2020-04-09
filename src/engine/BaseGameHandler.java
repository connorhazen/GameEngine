package engine;

import util.Grid;
import util.UserAction;

import java.util.HashMap;
import java.util.Map;

public class BaseGameHandler implements GameHandler {

    private Map<UserAction,Event> eventMappings;
    private UserAction nextEvent;

    public BaseGameHandler(){
        eventMappings = new HashMap<>();
        nextEvent = null;
    }

    @Override
    public void addEvent(UserAction action, String operation, String interaction, String rules){
        eventMappings.put(action, new GridEvent(operation,interaction,rules));
    }

    @Override
    public UserAction getNextEvent() {
        UserAction retVal = nextEvent;
        nextEvent = null;
        return retVal;
    }

    @Override
    public Grid generateUpdatedGrid(Grid currentGrid, UserAction action) {
        Event eventToPerform = eventMappings.get(action);
        Grid updatedGrid = eventToPerform.execute(currentGrid);
        nextEvent = eventToPerform.getNextAction();
        return updatedGrid;
    }
}
