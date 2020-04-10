package engine.interaction;

import util.Grid;
import util.Grid2dArray;
import util.MutableCell;

import java.util.Iterator;
import java.util.List;

public interface Interaction extends Iterator {

    void setGrid(Grid2dArray currentGrid);

    boolean hasNext();

    List<MutableCell> next();
}
