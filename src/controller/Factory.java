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
import view.SimpleView;
import view.View;

public class Factory {
  private String gameFile;
  private Scene display;
  private Map<Integer, String> imageMap;


  public Factory(String gameFile, Scene display){
    this.gameFile = gameFile;
    this.display = display;
    imageMap = new HashMap<>();
    imageMap.put(0,"questionMark.gif");

  }

  public Engine makeEngine() {
    GameHandler gh = new BaseGameHandler();
    LevelHandler lh = new BaseLevelHandler();
    AnimationHandler ah = new BaseAnimationHandler();

    return new Engine(gh,ah,lh);
  }

  public View makeView() {
    return new SimpleView(display, imageMap);
  }
}
