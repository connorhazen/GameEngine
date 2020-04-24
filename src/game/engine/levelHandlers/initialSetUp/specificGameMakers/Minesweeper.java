package game.engine.levelHandlers.initialSetUp.specificGameMakers;

import game.controller.GameStorageHandler;
import game.engine.UpdateStatus;
import game.engine.gameHandlers.interaction.Interaction;
import game.util.Cell;
import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.MutableCell;
import game.util.MutableGrid;
import game.util.SimpleState;
import game.util.State;
import java.util.List;
import java.util.function.Consumer;

public class Minesweeper implements LevelMaker {
  private double prob;

  public Minesweeper(List<String> args){
    prob = Double.parseDouble(args.get(0));
  }

  public  MutableGrid makeLevel(int height, int width) {
    Grid2dArray g = new Grid2dArray(width, height);
    Consumer<Coordinates> c = (e) -> g.setCell(e.x, e.y, new SimpleState("COVERED", 0));

    g.loop(c);

    makeBombs(prob, g);
    makeIndicators(g);

    return g;


  }

  private  void makeIndicators(Grid2dArray g) {
    Consumer<Coordinates> t = (e) -> {
      MutableCell current = g.getMutableCell(e);
      if(current.getValue() != -1 ){
        List<MutableCell> neighs = g.getNeighbors(current);
        int counter =0;
        for(Cell c: neighs){
          if(c.getValue()==-1){
            counter++;
          }
        }
        current.setState(new SimpleState(current.getType(), counter));
      }

    };

    g.loop(t);

  }

  private  void makeBombs(double prob, MutableGrid g){
    Consumer<Coordinates> t = (e) -> {
      if (Math.random() < prob) {
        State current = g.getMutableCell(e).getState();
        g.getMutableCell(e).setState(new SimpleState(current.getType(), -1));
      }
    };

    g.loop(t);

  }

}
