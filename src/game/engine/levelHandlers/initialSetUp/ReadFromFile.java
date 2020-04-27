package game.engine.levelHandlers.initialSetUp;

import game.controller.GameStorageHandler;
import game.util.MutableGrid;
import java.util.List;

public class ReadFromFile implements InitialLevelMaker {

  private String file;

  public ReadFromFile(List<String> args) {
    file = args.get(0);
  }

  @Override
  public MutableGrid execute(int height, int width) {
    return (MutableGrid) GameStorageHandler.loadGame(file).getGrid();
  }

  @Override
  public MutableGrid execute(MutableGrid g) {

    return g;
  }
}
