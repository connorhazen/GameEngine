package game.engine.gameHandlers.rules;

import game.util.MutableCell;
import java.util.List;

public class RuleTrue implements Rules {

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    return true;
  }
}
