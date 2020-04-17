package game.engine.gameHandlers.rules;

import game.util.MutableCell;
import java.util.List;

public class NotOppositeDirection implements Rules {

  private String newDir;

  public NotOppositeDirection(List<String> args){
    newDir = args.get(0);
  }

  @Override
  public boolean canPerform(List<MutableCell> currCells) {
    for(MutableCell c : currCells){
      if( (c.getDirection().equals("LEFT") && newDir.equals("RIGHT")) || (c.getDirection().equals("RIGHT") && newDir.equals("LEFT")) ||
          (c.getDirection().equals("UP") && newDir.equals("DOWN")) || (c.getDirection().equals("DOWN") && newDir.equals("UP"))){
        return false;
      }
    }
    return true;
  }
}
