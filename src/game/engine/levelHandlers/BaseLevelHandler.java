package game.engine.levelHandlers;

import game.engine.ObjectMaker;
import game.util.Grid;
import game.util.Grid2dArray;
import game.util.SimpleState;
import java.util.ArrayList;
import java.util.List;

public class BaseLevelHandler implements LevelHandler {

  private InitialLevelMaker maker;
  public static final String OUTER_PACKAGE = "game.engine.levelHandlers.";

  @Override
  public Grid initializeGrid() {
    return maker.execute(5, 5);

  }

  @Override
  public boolean determineStatus() {
    return false;
  }

  @Override
  public void setInitialGridMaker(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    maker = (InitialLevelMaker) ObjectMaker.createObjectOf(args.get(0), OUTER_PACKAGE,makerParams);

  }
}
