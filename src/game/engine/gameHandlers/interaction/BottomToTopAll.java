package game.engine.gameHandlers.interaction;

import game.util.Action;
import game.util.Coordinates;
import game.util.MutableCell;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BottomToTopAll extends OrderedAll {

  public BottomToTopAll() {
    super();
  }

  @Override
  public void setGrid(MutableGrid currentGrid, Action a) {
    cells.clear();
    for (int col = 0; col < currentGrid.getWidth(); col++) {
      List<List<MutableCell>> allRowCombos = new ArrayList<>();
      for (int row = currentGrid.getHeight() - 2; row >= 0; row--) {
        List<MutableCell> newGroup = new ArrayList<>();
        newGroup.add(currentGrid.getMutableCell(new Coordinates(col, row + 1)));
        newGroup.add(currentGrid.getMutableCell(new Coordinates(col, row)));
        newGroup.get(0).mark(false);
        newGroup.get(1).mark(false);
        allRowCombos.add(newGroup);
      }
      for (int index = 1; index <= allRowCombos.size(); index++) {
        List<List<MutableCell>> sub = new ArrayList<>(allRowCombos.subList(0, index));
        Collections.reverse(sub);
        cells.addAll(sub);
      }
    }
  }
}
