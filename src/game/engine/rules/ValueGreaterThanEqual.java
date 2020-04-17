package game.engine.rules;

import game.util.Cell;
import game.util.MutableCell;
import java.util.List;

public class ValueGreaterThanEqual implements Rules {
  private int val;

  public ValueGreaterThanEqual(List<String> args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    for(Cell c : currCells){
      if(c.getValue() <val){
        return false;
      }
    }
    return true;
  }
}
