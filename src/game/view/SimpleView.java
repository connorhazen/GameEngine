package game.view;

import game.engine.UpdateObject;
import game.parse.XMLException;
import game.util.Cell;
import java.io.FileInputStream;
import java.util.HashMap;
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
  private Map<String, String> imageMap;

  public static final String DEFAULT_IMAGE = "questionMark";
  public static final String FILE_PATH = "StateImages/";
  public static final String EXTENSION = ".gif";
  public static final Map<String, Double> ROTATE_MAP = Map.of(
      "LEFT", -90.0,
      "RIGHT", 90.0,
      "UP", 0.0,
      "DOWN", 180.0
      );
  private final static double HEIGHT = 500;
  private final static double WIDTH = 500;

  public SimpleView( Map<String, String> images){
    Stage stage = new Stage();

    root = new Group();
    display = new Scene(root, WIDTH, HEIGHT);

    stage.setScene(display);

    imageMap = images;

    stage.show();

  }



  @Override
  public void updateGridDisplay(UpdateObject uo) throws XMLException{
    if(uo.getAnimation() == null){
      root.getChildren().clear();
      displayGrid(uo.getGrid());
    }

  }

  private void displayGrid(Grid grid) throws XMLException{
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

      String image = getImageFile(cell.getValue());
      if(image!=null) {
        ImageView view = makeImage(image);
        view.setRotate(ROTATE_MAP.get(cell.getDirection()));

        pane.getChildren().add(view);

      }
      else{
        pane.getChildren().add(makeNumber(cell.getValue()));
      }
      root.getChildren().add(pane);

    });
  }

  private String getImageFile(int value) {
    for(String s: imageMap.keySet()){
      String[] split = s.split(" ");
      int lowerIndex = Integer.parseInt(split[0]);
      int upperIndex = Integer.MAX_VALUE;
      if(split.length>1){
        if(split.length>2){
          upperIndex = Integer.parseInt(split[3]);
        }
        if(value >= lowerIndex && value<= upperIndex){
          return imageMap.get(s);
        }
      }
      else{
        if(value == lowerIndex){
          return imageMap.get(s);
        }
      }
    }

    return null;
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

  private ImageView makeImage(String fileName) throws XMLException{


    Image image = null;
    if(!fileName.equals("empty")){
      try{
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        FileInputStream inputStream = new FileInputStream(Objects.requireNonNull(classLoader.getResource(FILE_PATH + fileName + EXTENSION)).getFile());
        image = new Image(inputStream);
      } catch (Exception e) {
        try{
          ClassLoader classLoader = ClassLoader.getSystemClassLoader();
          FileInputStream inputStream = new FileInputStream(
              Objects.requireNonNull(classLoader.getResource(FILE_PATH + DEFAULT_IMAGE + EXTENSION)).getFile());
          image = new Image(inputStream);
        } catch (Exception ex) {
          throw new XMLException("Image not found: "+ fileName);
        }
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

