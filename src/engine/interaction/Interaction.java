package engine.interaction;

import util.Grid;
import util.MutableCell;

import java.util.List;

public interface Interaction extends Iterable {

    void setGrid(Grid currentGrid);

    boolean hasNext();

    List<MutableCell> getNext();
}
