package engine;

import util.Grid;
import util.Action;

public class GridEvent implements Event {

    public GridEvent(String operation, String interaction, String rules) {
    }

    @Override
    public Grid execute(Grid currentGrid) {
        return null;
    }

    @Override
    public Action getNextAction() {
        return null;
    }
}
