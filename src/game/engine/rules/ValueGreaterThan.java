package game.engine.rules;

import game.util.Cell;
import game.util.MutableCell;
import java.util.List;

public class ValueGreaterThan implements Rules {

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    for(Cell c : currCells){
      if(c.getValue() <= 0){
        return false;
      }
    }
    return true;
  }
}
