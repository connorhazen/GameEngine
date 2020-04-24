package game.engine.gameHandlers.operation;

import game.util.MutableCell;
import game.util.SimpleState;
import java.util.List;


public class SetType implements Operation{
  private String type;

  public SetType(List<String> type){

    this.type = type.get(0);

  }


  @Override
  public void execute(List<MutableCell> currCells) {

    for(MutableCell c : currCells){
      c.setState(new SimpleState(type, c.getValue(), c.getDirection()));
    }
  }
}
