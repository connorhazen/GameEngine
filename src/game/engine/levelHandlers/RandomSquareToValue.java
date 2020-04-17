package game.engine.levelHandlers;

import game.util.Grid;
import game.util.Grid2dArray;
import game.util.SimpleState;
import java.util.List;

public class RandomSquareToValue implements InitialLevelMaker {
  private int val;


  public RandomSquareToValue(List<String> args){
    val = Integer.parseInt(args.get(0));
  }

  @Override
  public Grid execute(int height, int width) {
    Grid2dArray grid = new Grid2dArray(width,height);
    int x = (int)(Math.random()*width);
    int y = (int) (Math.random()*height);

    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", 0)));
    grid.setCell(x, y, new SimpleState("", val));

    return grid;
  }
}
