package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Grid2dArray extends Grid {
  private MutableCell[][] grid;
  private int width;
  private int height;

  public Grid2dArray(int width, int height){
    this.width = width;
    this.height = height;
    grid = new MutableCell[width][height];
  }

  public Grid2dArray(Grid grid){
    width = grid.getWidth();
    height = grid.getHeight();
    this.grid = new MutableCell[width][height];
    loop((c) -> {setCell(c.x, c.y, grid.getCell(c).getState());});
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

  public MutableCell getMutableCell(Coordinates coords) {
    return grid[coords.x][coords.y];
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
  public List<MutableCell> getCellsOfState(State s) {
    List<MutableCell> ret = new ArrayList<>();
    Consumer<Coordinates> con = (c) -> {if(getCell(c).getState().getValue() == s.getValue()) ret.add(getMutableCell(c));};

    loop(con);

    return ret;

  }

  @Override
  public Iterator<Cell> iterator() {
    return null;
  }

  @Override
  public void loop(Consumer<Coordinates> run){
    for(int x = 0; x<width; x++) {
      for (int y = 0; y < height; y++) {
        run.accept(new Coordinates(x,y));
      }
    }
  }
}
