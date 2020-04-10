package engine.rules;

import java.util.List;
import util.MutableCell;

public class RuleTrue implements Rules {

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    return true;
  }
}
