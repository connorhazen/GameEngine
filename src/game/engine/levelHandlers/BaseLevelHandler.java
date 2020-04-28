package game.engine.levelHandlers;

import game.engine.ObjectMaker;
import game.util.UpdateObject;
import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.endConditions.Condition;
import game.engine.levelHandlers.initialSetUp.InitialLevelMaker;
import game.parse.XMLException;
import game.util.Grid;
import game.util.MutableGrid;
import java.util.ArrayList;
import java.util.List;


public class BaseLevelHandler implements LevelHandler {

  public static final String OUTER_PACKAGE = "game.engine.levelHandlers.";
  public static final String INITIAL_LEVEL = "initialSetUp.";
  public static final String END_CONDITIONS = "endConditions.";
  private int height;
  private int width;
  private List<InitialLevelMaker> maker;
  private Condition winCon;
  private Condition loseCon;

  public BaseLevelHandler() {
    maker = new ArrayList<>();
  }

  @Override
  public Grid initializeGrid() throws XMLException {
    try {
      boolean first = true;
      MutableGrid g = null;
      for (InitialLevelMaker m : maker) {
        if (first) {
          first = false;
          g = m.execute(height, width);
          continue;
        }
        m.execute(g);
      }
      return g;
    } catch (Exception e) {

      throw new XMLException("Error Making grid: " + e.getClass().toString());
    }
  }

  @Override
  public void determineStatus(UpdateObject uo, Grid g, GameHandler lh) {
    uo.setGameLost(loseCon.checkStatus(g, lh));
    uo.setGameWon(winCon.checkStatus(g, lh));
  }

  @Override
  public void setInitialGridMaker(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    maker.add((InitialLevelMaker) ObjectMaker
        .createObjectOf(args.get(0), OUTER_PACKAGE + INITIAL_LEVEL, makerParams));

  }

  @Override
  public void setWinCondition(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    winCon = (Condition) ObjectMaker
        .createObjectOf(args.get(0), OUTER_PACKAGE + END_CONDITIONS, makerParams);
  }

  @Override
  public void setLoseCondition(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    loseCon = (Condition) ObjectMaker
        .createObjectOf(args.get(0), OUTER_PACKAGE + END_CONDITIONS, makerParams);
  }

  @Override
  public void setSize(String width, String height) {
    this.width = Integer.parseInt(width);
    this.height = Integer.parseInt(height);
  }
}
