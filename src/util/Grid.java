package util;

import java.util.Iterator;
import java.util.List;

public abstract class Grid implements Iterable<Cell> {


  public abstract Cell getCell(Coordinates coords);

  public abstract Cell getCell(int x, int y);

  public abstract int getWidth();
  public abstract int getHeight();

  public abstract List<Cell> getCellsOfState(State s);
}
