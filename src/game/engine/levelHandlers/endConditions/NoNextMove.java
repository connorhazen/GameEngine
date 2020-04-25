package game.engine.levelHandlers.endConditions;

import game.engine.gameHandlers.GameHandler;
import game.engine.levelHandlers.LevelHandler;
import game.engine.levelHandlers.endConditions.Condition;
import game.util.Grid;
import game.util.Grid2dArray;
import game.util.SimpleAction;
import java.util.ArrayList;
import java.util.List;

public class NoNextMove implements Condition {
  private List<String> eventsToCheck;
  public NoNextMove(List<String> args){
    eventsToCheck = new ArrayList<>();
    eventsToCheck.addAll(args);
  }

  @Override
  public boolean checkStatus(Grid g, GameHandler lh) {
    Grid2dArray grid = (Grid2dArray) g;
    for(String s: eventsToCheck){


       if(!g.equals( (Grid2dArray) lh.generateUpdatedGrid(g, new SimpleAction(s)))){
         return false;
       }
    }

    return true;
  }
}
