package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.MutableCell;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.List;

public class MarkedGrouped implements Interaction {

  protected List<List<MutableCell>> cells;

  @Override
  public void setGrid(MutableGrid currentGrid, Action a) {
    cells = new ArrayList<>();
    cells.add(currentGrid.getMarkedCells());

  }

  @Override
  public boolean hasNext() {
    return cells.size()>0;
  }

  @Override
  public List<MutableCell> next() {
    return cells.remove(0);
  }
}
