package game.engine.gameHandlers.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import game.util.State;
import java.util.List;

public class OperationCombine2048 implements Operation {

  @Override
  public void execute(List<MutableCell> currCells) {
    MutableCell cell1 = currCells.get(0);
    MutableCell cell2 = currCells.get(1);
    State combinedState = new SimpleState("", cell1.getValue() + cell2.getValue());
    State combinedZero = new SimpleState("", 0);
    if (cell1.getValue() == cell2.getValue()) {
      cell1.mark(true);
    }
    cell1.setState(combinedState);
    cell2.setState(combinedZero);
  }
}
