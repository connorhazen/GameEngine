package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.Coordinates;
import game.util.MutableCell;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CellsOfType implements Interaction {

  protected List<MutableCell> cells;
  private String type;

  public CellsOfType(List<String> args) {
    type = args.get(0);
  }

  @Override
  public void setGrid(MutableGrid currentGrid, Action a) {
    cells = new ArrayList<>();

    Consumer<Coordinates> run = (e) -> {
      if (currentGrid.getCell(e).getType().equals(type)) {
        cells.add(currentGrid.getMutableCell(e));
      }

    };

    currentGrid.loop(run);


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
