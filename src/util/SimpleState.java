package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class SimpleState implements State {
  private String type;
  private double value;



  public SimpleState(String type, double value){
    this.type = type;
    this.value = value;
  }



  @Override
  public String getType() {
    return type;
  }

  @Override
  public double getValue() {
    return value;
  }
}
