package game.engine.gameHandlers.interaction;

import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.MutableCell;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MaxValue implements Interaction{
  protected List<List<MutableCell>> cells;

  @Override
  public void setGrid(Grid2dArray currentGrid) {
    cells = new ArrayList<>();

    List<MutableCell> all = new ArrayList<>();

    int max = 0;
    MutableCell cell = null;

    Consumer<Coordinates> run = (e) -> {
        all.add(currentGrid.getMutableCell(e));
    };

    currentGrid.loop(run);

    for (MutableCell c : all){
      if(c.getValue()>max){
        cell = c;
        max = c.getValue();
      }
    }
    List<MutableCell> toAdd = new ArrayList<>();
    toAdd.add(cell);
    cells.add(toAdd);
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
