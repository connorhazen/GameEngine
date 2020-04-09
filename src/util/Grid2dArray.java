package util;

import java.util.Iterator;

public class Grid2dArray extends Grid {
  private MutableCell[][] grid;

  public Grid2dArray(int width, int height){
    grid = new MutableCell[width][height];
  }

  public void setCell(int x, int y, State newState){
    grid[x][y].setState(newState);
  }

  @Override
  public String getIdentifier(Cell x) {
    return null;
  }

  @Override
  public String getCell(String identifier) {
    return null;
  }

  @Override
  public Iterator<Cell> iterator() {
    return null;
  }
}
