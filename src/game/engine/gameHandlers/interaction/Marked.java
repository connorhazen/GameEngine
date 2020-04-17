package game.engine.gameHandlers.interaction;

import game.util.Grid2dArray;
import game.util.MutableCell;
import java.util.ArrayList;
import java.util.List;

public class Marked implements Interaction {

  protected List<List<MutableCell>> cells;

  @Override
  public void setGrid(Grid2dArray currentGrid) {
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
