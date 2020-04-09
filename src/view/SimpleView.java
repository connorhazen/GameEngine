package view;

import engine.UpdateObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import util.Grid;

public class SimpleView implements View {

  private Scene display;
  private Group root;

  public SimpleView(Scene scene){
    display = scene;
    root = (Group) scene.getRoot();

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

    for(int x = 0; x<width; x++){
      for(int y = 0; y<height; y++){
        ImageView add = new ImageView(grid.getCell(x,y).getImage());
        add.setFitWidth(display.getWidth()/width);
        add.setFitHeight(display.getHeight()/height);
        add.setLayoutX(display.getWidth()/width * x);
        add.setLayoutY(display.getHeight()/height*y);
        root.getChildren().add(add);
      }
    }
  }

  @Override
  public void displayWindow(Window w) {

  }

  @Override
  public void removeWindow(Window w) {

  }
}
