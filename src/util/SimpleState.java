package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

public class SimpleState implements State {
  private String type;
  private Image image;
  private double value;

  public static final String DEFAULT_IMAGE = "/StateImages/questionMark.gif";

  public SimpleState(String type, String imageFile, double value){
    this.image = makeImage(imageFile);
    this.type = type;
    this.value = value;
  }

  private Image makeImage(String imageFile){
    Image image = null;
    try{
      ClassLoader classLoader = ClassLoader.getSystemClassLoader();
      FileInputStream file = new FileInputStream(classLoader.getResource(imageFile).getFile());
      image = new Image(file);
    } catch (FileNotFoundException e) {
      try{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream file = new FileInputStream(classLoader.getResource(DEFAULT_IMAGE).getFile());
        image = new Image(file);
      } catch (FileNotFoundException ex) {
        System.out.println("Fucked up");
      }
    }

    return image;
  }

  @Override
  public Image getImage() {
    return image;
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
