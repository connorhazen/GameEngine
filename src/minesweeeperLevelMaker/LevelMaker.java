package minesweeeperLevelMaker;

import game.controller.GameStorageHandler;
import game.util.UpdateStatus;
import game.util.Cell;
import game.util.Coordinates;
import game.util.Grid2dArray;
import game.util.MutableCell;
import game.util.MutableGrid;
import game.util.SimpleState;
import game.util.State;
import java.util.List;
import java.util.function.Consumer;

public class LevelMaker {

  public static void main(String[] args) {

    LevelMaker lm = new LevelMaker();
    lm.makeLevel(10, 10, .2);
  }

  private void makeLevel(int width, int height, double prob) {
    Grid2dArray g = new Grid2dArray(width, height);
    Consumer<Coordinates> c = (e) -> g.setCell(e.x, e.y, new SimpleState("COVERED", 0));

    g.loop(c);

    makeBombs(prob, g);
    makeIndicators(g);

    GameStorageHandler.storeGame(new UpdateStatus(g), "data/Games/Minesweeper/level1.sav");


  }

  private void makeIndicators(Grid2dArray g) {
    Consumer<Coordinates> t = (e) -> {
      MutableCell current = g.getMutableCell(e);
      if (current.getValue() != -1) {
        List<MutableCell> neighs = g.getNeighbors(current);
        int counter = 0;
        for (Cell c : neighs) {
          if (c.getValue() == -1) {
            counter++;
          }
        }
        current.setState(new SimpleState(current.getType(), counter));
      }

    };

    g.loop(t);

  }

  private void makeBombs(double prob, MutableGrid g) {
    Consumer<Coordinates> t = (e) -> {
      if (Math.random() < prob) {
        State current = g.getMutableCell(e).getState();
        g.getMutableCell(e).setState(new SimpleState(current.getType(), -1));
      }
    };

    g.loop(t);

  }

}
