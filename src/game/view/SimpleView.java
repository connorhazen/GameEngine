package game.view;

import game.engine.UpdateObject;
import game.util.Cell;
import java.io.FileInputStream;
import java.util.Map;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import game.util.Grid;

public class SimpleView implements View {

  private Scene display;
  private Group root;
  private Map<Integer, String> imageMap;
  public static final String DEFAULT_IMAGE = "StateImages/questionMark.gif";
  private final static double HEIGHT = 500;
  private final static double WIDTH = 500;

  public SimpleView( Map<Integer, String> images){
    Stage stage = new Stage();

    root = new Group();
    display = new Scene(root, WIDTH, HEIGHT);

    stage.setScene(display);

    imageMap = images;

    stage.show();

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
      AnchorPane pane = new AnchorPane();

      pane.setPrefWidth(display.getWidth() / width);
      pane.setPrefHeight(display.getHeight() / height);
      pane.setLayoutX(display.getWidth() / width * c.x);
      pane.setLayoutY(display.getHeight() / height * c.y);


      Cell cell = grid.getCell(c.x, c.y);
      if(imageMap.containsKey(cell.getValue())) {
        pane.getChildren().add(makeImage(cell.getType(), cell.getValue()));
      }
      else{
        pane.getChildren().add(makeNumber(cell.getValue()));
      }

      root.getChildren().add(pane);

    });
  }

  private Button makeNumber(int value) {

    Button b = new Button(Integer.toString(value));




    b.setBackground(new Background(new BackgroundFill(getColor(value), null, null)));
    AnchorPane.setTopAnchor(b, 0.0);
    AnchorPane.setBottomAnchor(b, 0.0);
    AnchorPane.setLeftAnchor(b, 0.0);
    AnchorPane.setRightAnchor(b, 0.0);
    b.setFocusTraversable(false);
    return b;
  }

  private Paint getColor(int value) {
    return Color.hsb(1.0,.05 + ((Math.log10(value)/Math.log10(2) * .1)%1), 1.0);
  }

  private ImageView makeImage(String type, Integer value){

    String file = imageMap.get(value);

    Image image = null;
    try{
      ClassLoader classLoader = ClassLoader.getSystemClassLoader();
      FileInputStream inputStream = new FileInputStream(Objects.requireNonNull(classLoader.getResource(file)).getFile());
      image = new Image(inputStream);
    } catch (Exception e) {
      try{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream inputStream = new FileInputStream(
            Objects.requireNonNull(classLoader.getResource(DEFAULT_IMAGE)).getFile());
        //image = new Image(inputStream);
      } catch (Exception ex) {
        System.out.println("Fucked up");
        ex.printStackTrace();
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

  @Override
  public Scene getScene() {
    return display;
  }

}

