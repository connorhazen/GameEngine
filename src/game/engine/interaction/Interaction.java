package game.engine.interaction;

import game.util.Grid2dArray;
import game.util.MutableCell;

import java.util.Iterator;
import java.util.List;

public interface Interaction extends Iterator {

    void setGrid(Grid2dArray currentGrid);

    boolean hasNext();

    List<MutableCell> next();
}
