package game.engine.levelHandlers;

import game.util.Grid;
import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleState;
import java.util.List;
import java.util.Random;
import javax.print.DocFlavor.STRING;

public class RandomValsTo implements InitialLevelMaker {
  private int val;


  public RandomValsTo(List<String> args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public MutableGrid execute(int height, int width) {
    Grid2dArray grid = new Grid2dArray(width,height);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", (int)(Math.random() * val))));
    return grid;
  }

  @Override
  public MutableGrid execute(MutableGrid g) {
    g.loop(e -> g.setCell(e.x,e.y,new SimpleState("", (int)(Math.random() * val))));
    return g;
  }
}
