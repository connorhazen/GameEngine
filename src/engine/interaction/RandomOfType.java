package engine.interaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import util.Grid2dArray;
import util.MutableCell;
import util.SimpleState;

public class RandomOfType implements Interaction{

  protected List<MutableCell> cells;

  @Override
  public void setGrid(Grid2dArray currentGrid) {
    cells = new ArrayList<>();
    cells.addAll(currentGrid.getCellsOfState(new SimpleState("", 0)));
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
