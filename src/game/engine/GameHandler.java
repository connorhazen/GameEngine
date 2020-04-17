package game.engine;

import game.util.Grid;
import game.util.Action;
import java.util.List;

public interface GameHandler {

    void addEvent(Action action, List<String> operation, List<String> interaction, List<String> rules, Action nextAction);

    Action getNextEvent();

    Grid generateUpdatedGrid(Grid currentGrid, Action e);
}
