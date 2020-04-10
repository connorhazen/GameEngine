package view;

import engine.UpdateObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import util.Grid;

public class SimpleView implements View {

  private Scene display;
  private Group root;
  private Map<Integer, String> imageMap;
  public static final String DEFAULT_IMAGE = "/StateImages/questionMark.gif";

  public SimpleView(Scene scene, Map<Integer, String> images){
    display = scene;
    root = (Group) scene.getRoot();
    imageMap = images;

  }



  @Override
  public void updateGridDisplay(UpdateObject uo) {
    if(uo.getAnimation() == null){
      root.getChildren().clear();
      displayGrid(uo.getGrid());
    }

  }

  private void displayGrid(Grid grid) {
    if(grid == null){
      return;
    }
    int height = grid.getHeight();
    int width = grid.getWidth();

    grid.loop((c) -> {
      Pane pane = new Pane();
      pane.setPrefWidth(display.getWidth() / width);
      pane.setPrefHeight(display.getHeight() / height);
      pane.setLayoutX(display.getWidth() / width * c.x);
      pane.setLayoutY(display.getHeight() / height * c.y);
      pane.getChildren().add(makeImage(grid.getCell(c.x, c.y).getType(),grid.getCell(c.x, c.y).getValue() ));

      root.getChildren().add(pane);
    });
  }

  private Node makeImage(String type, Integer value){


    if(!imageMap.containsKey(value)){
      return new Label(Integer.toString(value));
    }

    String file = imageMap.get(value);

    Image image = null;
    try{
      ClassLoader classLoader = ClassLoader.getSystemClassLoader();
      FileInputStream inputStream = new FileInputStream(classLoader.getResource(file).getFile());
      image = new Image(inputStream);
    } catch (FileNotFoundException e) {
      try{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream inputStream = new FileInputStream(classLoader.getResource(DEFAULT_IMAGE).getFile());
        image = new Image(inputStream);
      } catch (FileNotFoundException ex) {
        System.out.println("Fucked up");
      }
    }


    return new ImageView(image);
  }

  @Override
  public void displayWindow(Window w) {

  }

  @Override
  public void removeWindow(Window w) {

  }
}
