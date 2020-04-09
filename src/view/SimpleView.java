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
  private final static double HEIGHT = 500;
  private final static double WIDTH = 500;

  public SimpleView(Stage stage){
    root = new Group();
    display = new Scene(root, WIDTH, HEIGHT);
    stage.setScene(display);
  }



  @Override
  public void updateGridDisplay(UpdateObject uo) {
    if(uo.getAnimation() == null){
      root.getChildren().clear();
      displayGrid(uo.getGrid());
    }

  }

  private void displayGrid(Grid grid) {
    int height = grid.getHeight();
    int width = grid.getWidth();

    for(int x = 0; x<width; x++){
      for(int y = 0; y<height; y++){
        ImageView add = new ImageView(grid.getCell(x,y).getImage());
        add.setFitWidth(WIDTH/width);
        add.setFitHeight(HEIGHT/height);
        add.setLayoutX(WIDTH/width * x);
        add.setLayoutY(HEIGHT/height*y);
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
