package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.List;
import game.util.MutableCell;

public class RandomOfValue implements Interaction{

  protected List<MutableCell> cells;
  private int val;
  public RandomOfValue(List<String>args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public void setGrid(MutableGrid currentGrid, Action a) {
    cells = new ArrayList<>();
    cells.addAll(currentGrid.getCellsOfValue(val));
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
