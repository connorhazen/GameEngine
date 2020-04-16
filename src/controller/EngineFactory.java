package controller;

import engine.AnimationHandler;
import engine.Engine;
import engine.GameHandler;
import engine.LevelHandler;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import parse.FileReader;
import parse.XMLException;
import util.SimpleAction;

public class EngineFactory {

  public static void parseXML(String fileName, GameHandler gameHandler, AnimationHandler animationHandler, LevelHandler lh) throws XMLException{

    FileReader fr = new FileReader(fileName);

    doEvents(gameHandler, fr.makeMapsForTag("Event"));

  }

  private static void doEvents(GameHandler gameHandler, List<Map<String, String>> list) throws XMLException{
    try {
      for (Map<String, String> map : list) {
        String code = Objects.requireNonNull(map.get("Code"));
        String operation = Objects.requireNonNull(map.get("Operations"));
        String interaction = Objects.requireNonNull(map.get("Interaction"));
        String rule = Objects.requireNonNull(map.get("Rules"));
        SimpleAction next = new SimpleAction(map.get("FollowEvents"));
        if(next.getCode() == null) next = null;

        gameHandler.addEvent(new SimpleAction(code), operation, interaction, rule,
            next);

      }
    } catch (Exception e) {
      throw new XMLException("Bad Event Construction");
    }
  }

}
