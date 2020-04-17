package game.engine.gameHandlers.operation;

import java.util.List;
import game.util.MutableCell;
import game.util.SimpleState;

public class SetValue implements Operation {
  private int val;

  public SetValue(List<String> args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public void execute(List<MutableCell> currCells) {
    for (MutableCell c : currCells){
      c.setState(new SimpleState(c.getType(), val, c.getDirection()));
    }
  }
}
