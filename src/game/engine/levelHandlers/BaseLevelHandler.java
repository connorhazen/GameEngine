package game.engine.levelHandlers;

import game.engine.ObjectMaker;
import game.engine.UpdateObject;
import game.util.Grid;
import game.util.Grid2dArray;
import game.util.MutableGrid;
import game.util.SimpleState;
import java.util.ArrayList;
import java.util.List;



public class BaseLevelHandler implements LevelHandler {

  private List<InitialLevelMaker> maker;
  private Condition winCon;
  private Condition loseCon;
  public static final String OUTER_PACKAGE = "game.engine.levelHandlers.";

  public BaseLevelHandler(){
    maker = new ArrayList<>();
  }

  @Override
  public Grid initializeGrid() {
    boolean first =  true;
    MutableGrid g = null;
    for(InitialLevelMaker m : maker){
      if(first){
        first = false;
        g = m.execute(5,5);
        continue;
      }
      m.execute(g);
    }
    return g;

  }

  @Override
  public void determineStatus(UpdateObject uo, Grid g) {
    uo.setGameLost(loseCon.checkStatus(g));
    uo.setGameWon(winCon.checkStatus(g));
  }

  @Override
  public void setInitialGridMaker(List<String> args) {
    System.out.println(args.get(0));
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);

    maker.add((InitialLevelMaker) ObjectMaker.createObjectOf(args.get(0), OUTER_PACKAGE,makerParams));

  }

  @Override
  public void setWinCondition(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    winCon = (Condition) ObjectMaker.createObjectOf(args.get(0), OUTER_PACKAGE, makerParams);
  }

  @Override
  public void setLoseCondition(List<String> args) {
    List<String> makerParams = new ArrayList<>(args);
    makerParams.remove(0);
    loseCon = (Condition) ObjectMaker.createObjectOf(args.get(0), OUTER_PACKAGE, makerParams);
  }
}
