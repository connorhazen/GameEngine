package game.engine.gameHandlers.interaction;

import game.util.MutableCell;

import game.util.MutableGrid;
import java.util.Iterator;
import java.util.List;

public interface Interaction extends Iterator {

    void setGrid(MutableGrid currentGrid);

    boolean hasNext();

    List<MutableCell> next();
}
