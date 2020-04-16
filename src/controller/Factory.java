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
import util.SimpleAction;
import view.SimpleView;
import view.View;

public class Factory {
  private String gameFile;
  private Stage display;
  private Map<Integer, String> imageMap;


  public Factory(String gameFile){
    this.gameFile = gameFile;
    this.display = display;
    imageMap = new HashMap<>();
    imageMap.put(0, "StateImages/questionMark.gif");
  }

  public Engine makeEngine() {
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
