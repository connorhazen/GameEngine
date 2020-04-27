package game.engine.gameHandlers.rules;

import game.util.MutableCell;
import java.util.List;

public class CellNotOfType implements Rules {

  private List<String> type;

  public CellNotOfType(List<String> args) {
    type = args;
  }

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    for (MutableCell c : currCells) {
      if (type.contains(c.getType())) {
        return false;
      }
    }
    return true;
  }
}
