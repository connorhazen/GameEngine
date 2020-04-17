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
        return (cells != null) && !(cells.size() == 1 && cells.get(0).size() < 2);
    }

    @Override
    public List<MutableCell> next() {
        List<MutableCell> pairOfCells = new ArrayList<>();
        List<MutableCell> currentGroup = cells.get(0);
        if(currentGroup.size() < 2){
            cells.remove(0);
            currentGroup = cells.get(0);
        }
        pairOfCells.add(currentGroup.remove(0));
        pairOfCells.add(currentGroup.get(0));
        return pairOfCells;
    }
}
