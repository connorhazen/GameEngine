package game.engine.gameHandlers.interaction;

import game.util.Grid2dArray;
import game.util.MutableCell;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderedAll implements Interaction {

    protected List<List<MutableCell>> cells;
    public abstract void setGrid(Grid2dArray currentGrid);

    public OrderedAll(){
        cells = new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        return cells != null && cells.size() > 0;
    }

    @Override
    public List<MutableCell> next() {
        return cells.remove(0);
    }
}
