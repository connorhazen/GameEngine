package game.engine.levelHandlers.endConditions;

import game.engine.gameHandlers.GameHandler;
import game.util.Cell;
import game.util.Coordinates;
import game.util.Grid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class AllCellOfTypes implements Condition {
  private Map<String, String> type;

  public AllCellOfTypes(List<String> args){
    type = new HashMap<>();
    while(args.size()>1){
      type.put(args.remove(0), args.remove(0));
    }
  }

  @Override
  public boolean checkStatus(Grid g, GameHandler lh) {
    AtomicBoolean ret = new AtomicBoolean(true);

    Consumer<Coordinates> t = (e) -> {
      if(!inMap(g.getCell(e))) {
        ret.set(false);
      }
    };
    g.loop(t);

    return ret.get();
  }

  private boolean inMap(Cell c){
    if(!type.containsKey(c.getType())){
      return false;
    }
    String value = type.get(c.getType());
    try{
      int val = Integer.parseInt(value);
      return val==c.getValue();
    }
    catch (Exception e){
      return true;
    }
  }
}
