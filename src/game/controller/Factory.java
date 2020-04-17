package game.controller;

import game.engine.AnimationHandler;
import game.engine.BaseAnimationHandler;
import game.engine.BaseGameHandler;
import game.engine.BaseLevelHandler;
import game.engine.Engine;
import game.engine.GameHandler;
import game.engine.LevelHandler;
import java.util.HashMap;
import java.util.Map;
import game.parse.XMLException;
import game.view.SimpleView;
import game.view.View;

public class Factory {
  private String gameFile;
  private Map<Integer, String> imageMap;
  public static final String ENGINE = "/engine.xml";
  public static final String VIEW = "/view.xml";


  public Factory(String gameFile){
    this.gameFile = gameFile;
    imageMap = new HashMap<>();
    //imageMap.put(0, "StateImages/questionMark.gif");
    imageMap.put(0, "");
  }

  public Engine makeEngine() throws XMLException {
    GameHandler gh = new BaseGameHandler();
    LevelHandler lh = new BaseLevelHandler();
    AnimationHandler ah = new BaseAnimationHandler();
    EngineFactory.parseXML(gameFile + ENGINE, gh, ah, lh);

    return new Engine(gh,ah,lh);
  }


  public View makeView() {
    return new SimpleView(imageMap);
  }
}
