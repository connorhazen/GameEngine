package game.engine.gameHandlers.rules;

import game.util.MutableCell;
import java.util.List;

public class CellNotOfType implements Rules {
  private String type;

  public CellNotOfType(List<String> args){
    type = args.get(0);
  }

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    for(MutableCell c : currCells){
      if(c.getType().equals(type)){
        return false;
      }
    }
    return true;
  }
}
