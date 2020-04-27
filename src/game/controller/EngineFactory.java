package game.controller;

import game.engine.animationHandlers.AnimationHandler;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.LevelHandler;
import game.parse.FileReader;
import game.parse.XMLException;
import game.util.SimpleAction;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class EngineFactory {
  private static final String LANGUAGE = "english";
  private static final ResourceBundle MY_RESOURCES = ResourceBundle
      .getBundle("resources." + LANGUAGE);

  public static void parseXML(String folder, String fileName, GameHandler gameHandler,
      AnimationHandler animationHandler, LevelHandler levelHandler) throws XMLException {

    FileReader fr = new FileReader(folder + fileName);
    doLevelMaker(levelHandler, fr);
    doEvents(gameHandler, fr);
  }

  private static void doEvents(GameHandler gameHandler, FileReader fr) throws XMLException {
    List<Map<String, String>> list = fr.makeMapsForTag("Event");
    try {
      for (Map<String, String> map : list) {
        String code = Objects.requireNonNull(map.get("Code"));
        String operation = Objects.requireNonNull(map.get("Operations"));
        String interaction = Objects.requireNonNull(map.get("Interaction"));
        String rule = Objects.requireNonNull(map.get("Rules"));
        SimpleAction ifExe = new SimpleAction(map.get("IfExecuteEvent"));
        if (ifExe.getCode() == null) {
          ifExe = null;
        }

        SimpleAction ifNoExe = new SimpleAction(map.get("IfNoExecuteEvent"));
        if (ifNoExe.getCode() == null) {
          ifNoExe = null;
        }

        gameHandler.addEvent(new SimpleAction(code), Arrays.asList(operation.split(" ")),
            Arrays.asList(interaction.split(" ")), Arrays.asList(rule.split(" ")),
            ifExe, ifNoExe);

      }
    } catch (XMLException e) {
      throw new XMLException(MY_RESOURCES.getString("engineParseException") + e.getMessage());
    } catch (Exception e) {
      throw new XMLException(e.getMessage());
    }
  }

  private static void doLevelMaker(LevelHandler lh, FileReader fr) throws XMLException {
    String height = fr.getValue("Height");
    String width = fr.getValue("Width");

    lh.setSize(width, height);
    List<String> val = fr.getValues("LevelMaker");
    for (String s : val) {
      lh.setInitialGridMaker(Arrays.asList(s.split(" ")));
    }

    String val1 = fr.getValue("LoseCon");
    lh.setLoseCondition(Arrays.asList(val1.split(" ")));

    val1 = fr.getValue("WinCon");
    lh.setWinCondition(Arrays.asList(val1.split(" ")));


  }

  public static String getStep(String folder, String fileName) {
    FileReader fr = new FileReader(folder + fileName);
    try {
      return fr.getValue("STEP");
    } catch (Exception e) {
      return "";
    }


  }
}
