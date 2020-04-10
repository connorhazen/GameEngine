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
import util.SimpleAction;
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
    imageMap.put(0, "StateImages/questionMark.gif");
  }

  public Engine makeEngine() {
    GameHandler gh = new BaseGameHandler();
    LevelHandler lh = new BaseLevelHandler();
    AnimationHandler ah = new BaseAnimationHandler();
    hardcodedEventsForNow(gh);

    return new Engine(gh,ah,lh);
  }

  //TODO: remove and use xml parsing
  public void hardcodedEventsForNow(GameHandler gh){
    gh.addEvent(new SimpleAction("LEFT"),"OperationCombine2048","RightToLeftAll","Rule2048");
    gh.addEvent(new SimpleAction("RIGHT"),"OperationCombine2048","LeftToRightAll","Rule2048");
    gh.addEvent(new SimpleAction("UP"),"OperationCombine2048","BottomToTopAll","Rule2048");
    gh.addEvent(new SimpleAction("DOWN"),"OperationCombine2048","TopToBottomAll","Rule2048");
  }

  public View makeView() {
    return new SimpleView(display, imageMap);
  }
}
