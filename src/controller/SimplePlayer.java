package controller;

import engine.Engine;
import engine.UpdateObject;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import util.Action;
import util.SimpleAction;
import view.View;

public class SimplePlayer implements Player {

  private final static String GAME_FILE = "game1.xml";
  private static final int PADDING = 5;
  private static final int V_GAP = 10;
  private static final int H_GAP = 50;
  private static final int WIDTH = 750;
  private static final int HEIGHT = 450;
  private static final Color BACKGROUND_COLOR = Color.TAN;
  private static final String TITLE = "GridGUYS Games - Final Project";



  public SimplePlayer(Stage primaryStage) {
    startupMenu(primaryStage);
    GameObject go = new GameObject(GAME_FILE);
    initialGameSetup(go);
  }

  private void startupMenu(Stage menuStage) {
    Group root = new Group();
    GridPane gridLayout = initializePane();
    root.getChildren().add(gridLayout);
    Scene mainScreen = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);
    menuStage.setScene(mainScreen);
    menuStage.setTitle(TITLE);
    menuStage.show();
  }

  private GridPane initializePane() {
    GridPane pane = new GridPane();
    pane.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
    pane.setVgap(V_GAP);
    pane.setHgap(H_GAP);
    Text title = generateTitle();
    Button generateGameButton = createButton();
    pane.add(title,0,0);
    pane.add(generateGameButton,1,1);
    return pane;
  }

  private Button createButton() {
    Button b = new Button("Generate Game");
    b.setOnAction(e -> {
      //TODO: fill this in to get access to selected radio button
    });
    return b;
  }

  private Text generateTitle() {
    Text title = new Text(TITLE);
    title.setFill(Color.BLACK);
    title.setFont(Font.font("Verdana", 32));
    title.setTextAlignment(TextAlignment.CENTER);
    return title;
  }

  private void initialGameSetup(GameObject go){
    updateView(go.getEngine().getGrid(),go);
    makeListeners(go);
  }

  @Override
  public void makeListeners(GameObject go) {
    go.getScene().setOnKeyPressed(e -> {
      handleEvent(new SimpleAction(e), go);
    });
  }


  @Override
  public void handleEvent(Action e, GameObject go) {
    System.out.println("Player listened code :" + e.getCode());
    UpdateObject uo = go.getEngine().executeAction(e);
    updateView(uo, go);
    if(hasNextAction(uo)){
      handleEvent(uo.getNextAction(), go);
    }
  }

  private boolean hasNextAction(UpdateObject uo) {
    return uo.getNextAction() != null;
  }

  @Override
  public void updateView(UpdateObject uo, GameObject go) {
    go.getView().updateGridDisplay(uo);

  }

}
