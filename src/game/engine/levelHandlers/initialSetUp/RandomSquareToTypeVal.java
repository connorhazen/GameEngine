package game.engine.levelHandlers.initialSetUp;

import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleState;
import java.util.List;

public class RandomSquareToTypeVal implements InitialLevelMaker {
  private Integer val;
  private String type;


  public RandomSquareToTypeVal(List<String> args){
    val = Integer.parseInt(args.get(1));
    type = args.get(0);


  }

  @Override
  public MutableGrid execute(int height, int width){
    MutableGrid grid = new Grid2dArray(width,height);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", 0)));

    while(true){
      int x = (int)(Math.random()*width);
      int y = (int) (Math.random()*height);
      if(grid.getCell(x,y).getValue()!=0){
        continue;
      }
      grid.setCell(x, y, new SimpleState(type, val));
      break;
    }

    return grid;
  }

  @Override
  public MutableGrid execute(MutableGrid g) {

    int x = (int) (Math.random() * g.getWidth());
    int y = (int) (Math.random() * g.getHeight());
    if (g.getCell(x, y).getValue() != 0) {
      return execute(g);
    }
    g.setCell(x, y, new SimpleState(type, val));
    return g;
  }
}
