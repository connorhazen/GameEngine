package game.engine.levelHandlers;

import game.util.Coordinates;
import game.util.Grid;
import java.util.List;
import java.util.function.Consumer;

public class CellOfType implements Condition {
  private String type;

  public CellOfType(List<String> args){
   type = args.get(0);
  }

  @Override
  public boolean checkStatus(Grid g) {
    final boolean[] ret = {false};

    Consumer<Coordinates> t = (e) -> {if(g.getCell(e).getType().equals(type)) ret[0] = true;};
    g.loop(t);

    return ret[0];
  }
}
