package game.engine.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import java.util.List;

public class UnMarkCell implements Operation {

  @Override
  public void execute(List<MutableCell> currCells) {
    for(MutableCell c: currCells){
      c.mark(false);
    }
  }
}
