package game.engine.levelHandlers;

import game.util.Grid;
import game.util.Grid2dArray;
import game.util.SimpleState;
import java.util.ArrayList;
import java.util.List;

public class RandomSquareToValue implements InitialLevelMaker {
  private List<Integer> val;


  public RandomSquareToValue(List<String> args){
    val = new ArrayList<>();
    for (String s : args){
      val.add(Integer.parseInt(s));
    }
  }

  @Override
  public Grid execute(int height, int width) {
    Grid2dArray grid = new Grid2dArray(width,height);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", 0)));

    int loc = 0;
    while(true){
      if(loc>= val.size()){
        break;
      }

      int x = (int)(Math.random()*width);
      int y = (int) (Math.random()*height);
      if(grid.getCell(x,y).getValue()!=0){
        continue;
      }
      grid.setCell(x, y, new SimpleState("", val.get(loc)));
      loc++;
    }

    return grid;
  }
}
