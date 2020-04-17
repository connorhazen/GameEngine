package game.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public abstract class Grid implements Iterable<Cell> {


  public abstract Cell getCell(Coordinates coords);

  public abstract Cell getCell(int x, int y);

  public abstract int getWidth();
  public abstract int getHeight();

  public abstract List<MutableCell> getCellsOfState(State s);

  public abstract void loop(Consumer<Coordinates> c);

  public abstract List<MutableCell> getCellsOfValue(SimpleState simpleState);
}
