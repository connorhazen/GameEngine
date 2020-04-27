package game.engine.levelHandlers.initialSetUp;

import game.util.MutableGrid;

public interface InitialLevelMaker {

  MutableGrid execute(int height, int width);

  MutableGrid execute(MutableGrid g);

}
