package engine.interaction;

import util.Grid2dArray;
import util.MutableCell;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderedAll implements Interaction {

    protected List<MutableCell> cells;
    public abstract void setGrid(Grid2dArray currentGrid);

    public OrderedAll(){
        cells = new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        return cells != null && cells.size() >= 2;
    }

    @Override
    public List<MutableCell> next() {
        List<MutableCell> pairOfCells = new ArrayList<>();
        pairOfCells.add(cells.remove(0));
        pairOfCells.add(cells.remove(0));
        return pairOfCells;
    }
}
