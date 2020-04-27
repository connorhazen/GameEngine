package game.controller;

import game.engine.Engine;
import game.engine.animationHandlers.AnimationHandler;
import game.engine.animationHandlers.BaseAnimationHandler;
import game.engine.gameHandlers.BaseGameHandler;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.BaseLevelHandler;
import game.engine.levelHandlers.LevelHandler;
import game.parse.XMLException;
import game.view.View;
import java.util.Map;


public class Factory {

  public static final String ENGINE = "/engine.xml";
  public static final String VIEW = "/view.xml";
  private String gameFile;
  private Map<Integer, String> imageMap;


  public Factory(String gameFile) {
    this.gameFile = gameFile;
  }

  public Engine makeEngine() throws XMLException {
    try {
      GameHandler gh = new BaseGameHandler();
      LevelHandler lh = new BaseLevelHandler();
      AnimationHandler ah = new BaseAnimationHandler();
      EngineFactory.parseXML(gameFile, ENGINE, gh, ah, lh);
      return new Engine(gh, ah, lh);
    } catch (Exception e) {
      throw e;
    }

  }


  public View makeView() {

    return ViewFactory.makeView(gameFile, VIEW);
  }

  public int getStep() {
    String step = EngineFactory.getStep(gameFile, ENGINE);
    if (!step.equals("")) {
      return Integer.parseInt(step);
    } else {
      return 0;
    }

  }
}
