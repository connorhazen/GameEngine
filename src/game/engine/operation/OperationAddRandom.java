package game.engine.operation;

import java.util.List;
import game.util.MutableCell;
import game.util.SimpleState;

public class OperationAddRandom implements Operation {

  @Override
  public void execute(List<MutableCell> currCells) {
    if(currCells.size()>0){
      MutableCell c = currCells.get(0);
      c.setState(new SimpleState("", 1));
    }
  }
}
