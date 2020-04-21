package game.util;

public class Cell {
  protected State state;
  protected Coordinates coords;


  public String getType(){
    return state.getType();
  }

  public int getValue(){
    return state.getValue();
  }

  public String getDirection(){
    return state.getDirection();
  }


  public State getState(){
    return state;
  }

  public Coordinates getCoords(){
    return coords;
  }

  public void mark(boolean newStat) {
    state.mark(newStat);
  }

  @Override
  public boolean equals(Object obj){
    try{
      Cell c = (Cell) obj;
      return c.getValue() == getValue() && c.getType().equals(c.getType());
    }
    catch (Exception e){
      return false;
    }
  }
}
