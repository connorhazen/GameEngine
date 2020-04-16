package controller;

import engine.AnimationHandler;
import engine.BaseAnimationHandler;
import engine.BaseGameHandler;
import engine.BaseLevelHandler;
import engine.Engine;
import engine.GameHandler;
import engine.LevelHandler;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.stage.Stage;
import parse.XMLException;
import util.SimpleAction;
import view.SimpleView;
import view.View;

public class Factory {
  private String gameFile;
  private Map<Integer, String> imageMap;


  public Factory(String gameFile){
    this.gameFile = gameFile;
    imageMap = new HashMap<>();
    imageMap.put(0, "StateImages/questionMark.gif");
  }

  public Engine makeEngine() throws XMLException {
    GameHandler gh = new BaseGameHandler();
    LevelHandler lh = new BaseLevelHandler();
    AnimationHandler ah = new BaseAnimationHandler();

    EngineFactory.parseXML(gameFile, gh, ah, lh);

    return new Engine(gh,ah,lh);
  }


  public View makeView() {
    return new SimpleView(imageMap);
  }
}
