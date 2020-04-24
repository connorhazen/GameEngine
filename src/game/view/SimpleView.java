package game.view;

import game.controller.GameObject;
import game.controller.GameStorageHandler;
import game.engine.UpdateObject;
import game.parse.XMLException;
import game.util.Action;
import game.util.Cell;
import game.util.ClickedAction;
import game.util.Coordinates;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import game.util.Grid;

public class SimpleView implements View {

  private Scene display;
  private Group root;
  private Map<String, String> imageMap;
  private Stage stage;
  private Consumer<Action> eventCaller;

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
  private final static double SCENE_HEIGHT = 600;

  public SimpleView( Map<String, String> images){
    stage = new Stage();

    root = new Group();

    display = new Scene(root, WIDTH, SCENE_HEIGHT);

    stage.setScene(display);

    imageMap = images;

    stage.show();

  }



  @Override
  public void updateGridDisplay(UpdateObject uo) throws XMLException{
    if(!uo.getGameRunning()){
      System.out.println(uo.getGameRunning());
      displayStatusUO(uo);
    }
    else if(uo.getAnimation() == null){
      root.getChildren().clear();
      displayGrid(uo.getGrid());
    }
//    displayOrganizeButtons(uo);
  }

//  private void displayOrganizeButtons(UpdateObject uo) {
//    Rectangle bottomEdge = new Rectangle(0,HEIGHT+1,WIDTH,4);
//    bottomEdge.setFill(Color.BLACK);
//    Button saveGameButton = new Button("Save Game");
//    TextField filName = new
//    saveGameButton.setOnAction(e -> GameStorageHandler.storeGame(uo);)
//    root.getChildren().add(bottomEdge);
//  }

  private void displayStatusUO(UpdateObject uo) {
    root.getChildren().clear();
    Label status = new Label();
    if(uo.getGameLost()){
      status.setText("YOU LOST");
    }
    if (uo.getGameWon()){
      status.setText("YOU WON");
    }
   root.getChildren().add(status);
  }

  private void displayGrid(Grid grid) throws XMLException{
    if(grid == null){
      return;
    }
    int height = grid.getHeight();
    int width = grid.getWidth();

    grid.loop((c) -> {

      Cell cell = grid.getCell(c.x, c.y);
      String image = getImageFile(cell.getValue(), cell.getType());
      Node n = null;
      if(image!=null) {
        ImageView view = makeImage(image);
        n=view;
        view.setLayoutX(WIDTH / width * c.x);
        view.setLayoutY(HEIGHT / height * c.y);
        view.setFitWidth(WIDTH / width);
        view.setFitHeight(HEIGHT / height);
        view.setRotate(ROTATE_MAP.get(cell.getDirection()));
        root.getChildren().add(view);
      }
      else{
        AnchorPane pane = makePane(c, width, height);
        Button l = makeNumber(cell.getValue());
        n = l;
        pane.getChildren().add(l);
        root.getChildren().add(pane);
      }

      n.setOnMouseClicked((e) -> eventCaller.accept(new ClickedAction("ClickedCell", c)));
    });
  }

  private AnchorPane makePane(Coordinates c, int width, int height){
    AnchorPane ret = new AnchorPane();
    ret.setPrefWidth(HEIGHT / width);
    ret.setPrefHeight(HEIGHT / height);
    ret.setLayoutX(WIDTH / width * c.x);
    ret.setLayoutY(WIDTH / height * c.y);
    return ret;
  }

  private String getImageFile(int value, String type) {
    if(imageMap.containsKey(type)){
      return imageMap.get(type);
    }
    for(String s: imageMap.keySet()){
      try{
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
      catch (Exception e){
        continue;
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
  public void setEventCaller(Consumer<Action> run) {
    eventCaller = run;
  }

  @Override
  public Scene getScene() {
    return display;
  }

  @Override
  public Stage getStage() {
    return stage;
  }

}

