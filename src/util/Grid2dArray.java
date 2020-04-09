package util;

import java.util.Iterator;

public class Grid2dArray extends Grid {
  private MutableCell[][] grid;

  public Grid2dArray(int width, int height){
    grid = new MutableCell[width][height];
  }

  public void setCell(int x, int y, State newState){
    MutableCell cell = grid[x][y];
    if(cell == null){
      grid[x][y] = new MutableCell();
      cell = grid[x][y];
    }
    cell.setState(newState);
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
  public Cell getCell(int x, int y) {
    return grid[x][y];
  }

  @Override
  public int getWidth() {
    return grid.length;
  }


  /**
   * TODO check if x is 0 ie out of bounds;
   * @return
   */
  @Override
  public int getHeight() {
    return grid[0].length;
  }

  @Override
  public Iterator<Cell> iterator() {
    return null;
  }
}
