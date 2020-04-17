package game.engine;

import game.util.Grid;
import game.util.Grid2dArray;
import game.util.SimpleState;

public class BaseLevelHandler implements LevelHandler {

  @Override
  public Grid initializeGrid() {
    Grid2dArray grid = new Grid2dArray(5,5);
    grid.loop(e -> grid.setCell(e.x,e.y,new SimpleState("", (int)(Math.random() * 2))));
    return grid;
  }

  @Override
  public boolean determineStatus() {
    return false;
  }
}