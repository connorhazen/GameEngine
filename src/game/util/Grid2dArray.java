package game.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Grid2dArray implements MutableGrid {
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
    loop((c) -> {setCell(grid.getCell(c));});
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

  public void setCell(Cell c){
    grid[c.coords.x][c.coords.y] = new MutableCell(c);

  }



  @Override
  public Cell getCell(Coordinates coords) {
    return getCell(coords.x, coords.y);
  }

  public MutableCell getMutableCell(Coordinates coords) {
    return grid[coords.x][coords.y];
  }

  @Override
  public List<MutableCell> getNeighbors(MutableCell c) {
    List<MutableCell> ret = new ArrayList<>();
    int x = c.coords.x;
    int y = c.coords.y;

    addIfExists(ret, x - 1, y + 1);
    addIfExists(ret, x, y + 1);
    addIfExists(ret, x + 1, y + 1);

    addIfExists(ret, x - 1, y);
    addIfExists(ret, x + 1, y);

    addIfExists(ret, x - 1, y - 1);
    addIfExists(ret, x, y - 1);
    addIfExists(ret, x + 1, y - 1);
    return ret;
  }

  protected void addIfExists(List<MutableCell> ret, int x, int y) {
    if (x < grid[0].length && x >= 0 && y >= 0 && y < grid.length) {
      ret.add(getMutableCell(new Coordinates(x,y)));
    }
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
  public void loop(Consumer<Coordinates> run){
    for(int x = 0; x<width; x++) {
      for (int y = 0; y < height; y++) {
        run.accept(new Coordinates(x,y));
      }
    }
  }


  @Override
  public List<MutableCell> getCellsOfValue(int val) {
    List<MutableCell> ret = new ArrayList<>();
    Consumer<Coordinates> con = (c) -> {if(getCell(c).getState().getValue() == val) ret.add(getMutableCell(c));};

    loop(con);

    return ret;
  }

  @Override
  public List<MutableCell> getCellsOfType(String type) {
    List<MutableCell> ret = new ArrayList<>();
    Consumer<Coordinates> con = (c) -> {if(getCell(c).getState().getType().equals(type)) ret.add(getMutableCell(c));};

    loop(con);

    return ret;
  }

  @Override
  public MutableCell getCellDirection(MutableCell c) {
    int xD = 0;
    int yD = 0;
    if(c.getDirection().equals("LEFT")) xD = -1;
    if(c.getDirection().equals("RIGHT")) xD = 1;
    if(c.getDirection().equals("UP")) yD = -1;
    if(c.getDirection().equals("DOWN")) yD = 1;


    return getMutableCell(new Coordinates(Math.floorMod(c.getCoords().x + xD, width) , Math.floorMod(c.getCoords().y + yD,height)));
  }

  @Override
  public List<MutableCell> getMarkedCells() {
    List<MutableCell> ret = new ArrayList<>();
    Consumer<Coordinates> con = (c) -> {if(getCell(c).isMarked()) ret.add(getMutableCell(c));};

    loop(con);

    return ret;
  }

  @Override
  public boolean equals(Object obj){
    try{
      Grid g = (Grid) obj;
      AtomicBoolean ret = new AtomicBoolean(true);
      Consumer<Coordinates> t = e -> {if(!g.getCell(e).equals(this.getCell(e))){
        ret.set(false);
      }};
      loop(t);
      return ret.get();
    }
    catch (Exception e){
      return false;
    }
  }
}
