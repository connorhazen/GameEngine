package game.engine.levelHandlers.endConditions;

import game.engine.gameHandlers.GameHandler;
import game.util.Coordinates;
import game.util.Grid;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class CellOfType implements Condition {
  private String type;

  public CellOfType(List<String> args){
   type = args.get(0);
  }

  @Override
  public boolean checkStatus(Grid g, GameHandler lh) {
    AtomicBoolean ret = new AtomicBoolean(false);

    Consumer<Coordinates> t = (e) -> {if(g.getCell(e).getType().equals(type)) ret.set(true);};
    g.loop(t);

    return ret.get();
  }
}
