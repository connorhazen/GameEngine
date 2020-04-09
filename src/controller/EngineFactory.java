package controller;

import engine.AnimationHandler;
import engine.BaseAnimationHandler;
import engine.BaseGameHandler;
import engine.BaseLevelHandler;
import engine.Engine;
import engine.GameHandler;
import engine.LevelHandler;

public class EngineFactory {

  public static Engine make(String gameFile) {
    GameHandler gh = new BaseGameHandler();
    LevelHandler lh = new BaseLevelHandler();
    AnimationHandler ah = new BaseAnimationHandler();

    return new Engine(gh,ah,lh);

  }
}
