package game.engine.levelHandlers.initialSetUp;

import game.engine.ObjectMaker;
import game.engine.levelHandlers.initialSetUp.specificGameMakers.LevelMaker;
import game.util.MutableGrid;
import java.util.List;


public class SpecificLevelMaker implements InitialLevelMaker {

  public static final String OUTER_PACKAGE = "game.engine.levelHandlers.initialSetUp.specificGameMakers.";
  private LevelMaker lm;

  public SpecificLevelMaker(List<String> args) {
    String name = args.remove(0);
    lm = (LevelMaker) ObjectMaker.createObjectOf(name, OUTER_PACKAGE, args);
  }


  @Override
  public MutableGrid execute(int height, int width) {
    return lm.makeLevel(height, width);
  }

  @Override
  public MutableGrid execute(MutableGrid g) {
    return null;
  }
}
