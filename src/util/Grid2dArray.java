package util;

import java.util.Iterator;

public class Grid2dArray extends Grid {
  private MutableCell[][] grid;
  private int width;
  private int height;

  public Grid2dArray(int width, int height){
    this.width = width;
    this.height = height;
    grid = new MutableCell[width][height];
  }

  public void setCell(int x, int y, State newState){
    MutableCell cell = grid[x][y];
    if(cell == null){
      grid[x][y] = new MutableCell(newState, new Coordinates(x,y));
    }
    else{
      cell.setState(newState);
    }
  }



  @Override
  public Cell getCell(Coordinates coords) {
    return getCell(coords.x, coords.y);
  }

  @Override
  public Cell getCell(int x, int y) {
    return grid[x][y];
  }

  @Override
  public int getWidth() {
    return width;
  }



  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public Iterator<Cell> iterator() {
    return null;
  }
}
