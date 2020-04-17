package game.engine.gameHandlers.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import java.util.List;

public class CombineToMaxAndChanges implements Operation {


  @Override
  public void execute(List<MutableCell> currCells) {
    int max = 0;
    MutableCell maxCell = null;
    for(MutableCell c : currCells){
      c.mark(false);
      if(c.getValue()>max){
        maxCell = c;
        max = c.getValue();
      }
    }

    for(MutableCell c : currCells){
      if(c!= maxCell){
        c.setState(new SimpleState(c.getType(), max, maxCell.getDirection()).mark(true));
      }
    }
  }
}
