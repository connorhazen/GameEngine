package game.engine.interaction;

import java.util.ArrayList;
import java.util.List;
import game.util.Grid2dArray;
import game.util.MutableCell;
import game.util.SimpleState;

public class RandomOfValue implements Interaction{

  protected List<MutableCell> cells;

  @Override
  public void setGrid(Grid2dArray currentGrid) {
    cells = new ArrayList<>();
    cells.addAll(currentGrid.getCellsOfValue(0));
  }

  @Override
  public boolean hasNext() {
    return cells.size()>0;
  }

  @Override
  public List<MutableCell> next() {
    MutableCell toRet = cells.get((int) (Math.random()*cells.size()));
    cells.clear();
    List<MutableCell> ret = new ArrayList<>();
    ret.add(toRet);
    return ret;
  }
}
