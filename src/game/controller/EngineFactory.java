package game.controller;

import game.engine.AnimationHandler;
import game.engine.GameHandler;
import game.engine.LevelHandler;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import game.parse.FileReader;
import game.parse.XMLException;
import game.util.SimpleAction;

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

        gameHandler.addEvent(new SimpleAction(code), Arrays.asList(operation.split(" ")),  Arrays.asList(interaction.split(" ")),  Arrays.asList(rule.split(" ")),
            next);

      }
    } catch (XMLException e) {
      throw new XMLException("Bad Event Construction " + e.getMessage());
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

}
