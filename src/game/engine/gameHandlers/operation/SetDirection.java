package game.engine.gameHandlers.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import java.util.List;

public class SetDirection implements Operation{
  private String newDir;

  public SetDirection(List<String> args){
    newDir = args.get(0);
  }

  @Override
  public void execute(List<MutableCell> currCells) {
    for(MutableCell c: currCells){
      c.setState(new SimpleState(c.getState().getType(), c.getValue(), newDir));
    }

  }
}
