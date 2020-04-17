package game.engine.gameHandlers.rules;

import java.util.List;
import game.util.MutableCell;

public class RuleTrue implements Rules {

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    return true;
  }
}
