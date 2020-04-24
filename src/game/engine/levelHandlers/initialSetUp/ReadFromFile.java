package game.engine.levelHandlers.initialSetUp;

import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleState;
import java.util.List;

public class ReadFromFile implements InitialLevelMaker {
  private String file;
  public ReadFromFile(List<String> args){
    file = args.get(0);
  }

  @Override
  public MutableGrid execute(int height, int width) {
    Grid2dArray grid = new Grid2dArray(width,height);
    return grid;
  }

  @Override
  public MutableGrid execute(MutableGrid g) {

    return g;
  }
}
