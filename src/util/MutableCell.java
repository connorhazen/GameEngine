package util;

public class MutableCell  extends Cell{

  public MutableCell(Cell c){
    setState(c.getState());
    coords = c.getCoords();

  }

  public MutableCell(State s, Coordinates coordinates){
    setState(s);
    coords = coordinates;
  }

  public void setState(State s){
    state = s;
  }
}
