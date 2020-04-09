package engine;

import util.Grid;

public class BaseLevelHandler implements LevelHandler {

  @Override
  public Grid initializeGrid() {
    return null;
  }

  @Override
  public boolean determineStatus() {
    return false;
  }
}
