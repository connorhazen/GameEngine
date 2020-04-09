package util;

import java.util.Iterator;

public abstract class Grid implements Iterable<Cell> {

  public abstract String getIdentifier(Cell x);

  public abstract String getCell(String identifier);

  public abstract Cell getCell(int x, int y);

  public abstract int getWidth();
  public abstract int getHeight();
}
