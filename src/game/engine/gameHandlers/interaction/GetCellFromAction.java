package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.MutableCell;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.List;

public class GetCellFromAction implements Interaction {

  protected List<MutableCell> cells;


  @Override
  public void setGrid(MutableGrid currentGrid, Action a) {
    cells = new ArrayList<>();

    cells.add(currentGrid.getMutableCell(a.getCell()));
  }

  @Override
  public boolean hasNext() {
    return cells.size() > 0;
  }

  @Override
  public List<MutableCell> next() {
    List<MutableCell> ret = new ArrayList<>();
    ret.add(cells.remove(0));
    return ret;
  }
}
