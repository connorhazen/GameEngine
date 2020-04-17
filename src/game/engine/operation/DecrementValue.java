package game.engine.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import java.util.List;

public class DecrementValue implements Operation{
  private int val;

  public DecrementValue(List<String> args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public void execute(List<MutableCell> currCells) {
    for(MutableCell c : currCells){
      c.setState(new SimpleState(c.getType(), c.getValue()-val));
    }
  }
}
