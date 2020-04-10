package engine;

import util.Grid;
import util.Action;
import util.SimpleAction;

import java.util.HashMap;
import java.util.Map;

public class BaseGameHandler implements GameHandler {

    private Map<Action,Event> eventMappings;
    private Action nextEvent;

    public BaseGameHandler(){
        eventMappings = new HashMap<>();
        nextEvent = null;
    }

    @Override
    public void addEvent(Action action, String operation, String interaction, String rules, Action nextAction){
        eventMappings.put(action, new GridEvent(operation,interaction,rules, nextAction));
    }

    @Override
    public Action getNextEvent() {
        Action retVal = nextEvent;
        nextEvent = null;
        return retVal;
    }

    @Override
    public Grid generateUpdatedGrid(Grid currentGrid, Action action) {
        if (eventMappings.containsKey(action)){
            Event eventToPerform = eventMappings.get(action);
            Grid updatedGrid = eventToPerform.execute(currentGrid);
            nextEvent = eventToPerform.getNextAction();
            return updatedGrid;
        }
        else{
            nextEvent = null;
            return currentGrid;
        }
    }
}
