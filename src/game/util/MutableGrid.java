package game.util;

public interface MutableGrid extends Grid {

  public void setCell(int x, int y, State newState);

  public MutableCell getMutableCell(Coordinates coords);

}
