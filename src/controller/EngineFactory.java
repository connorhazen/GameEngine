package controller;

import engine.AnimationHandler;
import engine.Engine;
import engine.GameHandler;
import engine.LevelHandler;
import util.SimpleAction;

public class EngineFactory {

  public static void parseXML(String fileName, GameHandler gameHandler, AnimationHandler animationHandler, LevelHandler lh){
    doEvents(gameHandler);

  }

  private static void doEvents(GameHandler gameHandler){
    gameHandler.addEvent(new SimpleAction("LEFT"),"OperationCombine2048","RightToLeftAll","Rule2048", new SimpleAction("addRandom"));
    gameHandler.addEvent(new SimpleAction("RIGHT"),"OperationCombine2048","LeftToRightAll","Rule2048", new SimpleAction("addRandom"));
    gameHandler.addEvent(new SimpleAction("UP"),"OperationCombine2048","BottomToTopAll","Rule2048", new SimpleAction("addRandom"));
    gameHandler.addEvent(new SimpleAction("DOWN"),"OperationCombine2048","TopToBottomAll","Rule2048", new SimpleAction("addRandom"));
    gameHandler.addEvent(new SimpleAction("addRandom"),"OperationAddRandom","RandomOfType","RuleTrue", null);
  }

}
