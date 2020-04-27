package game.engine.levelHandlers.initialSetUp;

import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleState;
import java.util.List;

public class RandomValsTo implements InitialLevelMaker {

  private int val;


  public RandomValsTo(List<String> args) {
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public MutableGrid execute(int height, int width) {
    Grid2dArray grid = new Grid2dArray(width, height);
    grid.loop(e -> grid.setCell(e.x, e.y, new SimpleState("", (int) (Math.random() * val))));
    return grid;
  }

  @Override
  public MutableGrid execute(MutableGrid g) {
    g.loop(e -> g.setCell(e.x, e.y, new SimpleState("", (int) (Math.random() * val))));
    return g;
  }
}
