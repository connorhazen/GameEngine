package game.engine.levelHandlers.initialSetUp.specificGameMakers;

import game.util.MutableGrid;

public interface LevelMaker {


  MutableGrid makeLevel(int height, int width);

}
