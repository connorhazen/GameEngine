package util;

import javafx.scene.image.Image;

public class Cell {
  protected State state;
  protected Coordinates coords;


  public String getType(){
    return state.getType();
  }

  public double getValue(){
    return state.getValue();
  }


  public State getState(){
    return state;
  }

  public Coordinates getCoords(){
    return coords;
  }


}
