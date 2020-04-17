package game.engine.gameHandlers.interaction;

import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.MutableCell;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class All implements Interaction{

  protected List<MutableCell> cells;
  private int val;

  @Override
  public void setGrid(Grid2dArray currentGrid) {
    cells = new ArrayList<>();


    Consumer<Coordinates> run = (e) -> {
      cells.add(currentGrid.getMutableCell(e));
    };

    currentGrid.loop(run);

  }

  @Override
  public boolean hasNext() {
    return cells.size()>0;
  }

  @Override
  public List<MutableCell> next() {
    List<MutableCell> ret = new ArrayList<>();
    ret.add(cells.remove(0));
    return ret;
  }
}
