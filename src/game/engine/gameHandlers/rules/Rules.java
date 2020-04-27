package game.engine.gameHandlers.rules;

import game.util.MutableCell;
import java.util.List;

public interface Rules {

  boolean canPerform(List<MutableCell> currCells);
}
