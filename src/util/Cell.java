package util;

import javafx.scene.Node;
import javafx.scene.image.Image;

public class Cell {
  protected State state;


  public Image getImage(){
    return state.getImage();
  }

}
