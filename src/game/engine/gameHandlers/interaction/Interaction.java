package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.MutableCell;

import game.util.MutableGrid;
import java.util.Iterator;
import java.util.List;


/**
 * This set of classes is used to create the set of cells to preform operations on.
 * There are two different types of interactions, grouped and normal.
 * This affects how the rules execute, if the cells are grouped, then they are passed to the
 * rules as one list. Otherwise, they are one by one. 
 */
public interface Interaction extends Iterator {

    void setGrid(MutableGrid currentGrid, Action a);

    boolean hasNext();

    List<MutableCell> next();
}
