package game.util;

import java.util.List;

public interface MutableGrid extends Grid {

  public void setCell(int x, int y, State newState);

  public MutableCell getMutableCell(Coordinates coords);

  List<MutableCell> getNeighbors(MutableCell c);
}
