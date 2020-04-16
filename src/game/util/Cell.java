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


  public State getState(){
    return state;
  }

  public Coordinates getCoords(){
    return coords;
  }


}
