package game.controller;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import game.engine.UpdateObject;
import game.util.Action;
import game.util.SimpleAction;


public class SimplePlayer implements Player {

  private final static String GAME_FILE = "Games/game1.xml";
  private static final int PADDING = 5;
  private static final int V_GAP = 10;
  private static final int H_GAP = 50;
  private static final int WIDTH = 750;
  private static final int HEIGHT = 200;
  private static final Color BACKGROUND_COLOR = Color.TAN;
  private static final String TITLE = "GridGUYS Games - Final Project";
  private ToggleGroup toggleGroup = new ToggleGroup();
  private StackPane messageBox = new StackPane();

  public SimplePlayer(Stage primaryStage) {
    startupMenu(primaryStage);
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
    VBox gameSelectors = generateGameButtons();
    Button generateGameButton = createButton();
    Rectangle box = new Rectangle(title.getLayoutBounds().getWidth(), 30);
    box.setFill(Color.WHITE);
    addGameMessage("Select a Game!");
    pane.add(title,0,0);
    pane.add(gameSelectors,0,1);
    pane.add(generateGameButton,1,1);
    pane.add(box,0,2);
    pane.add(messageBox,0,2);
    return pane;
  }

  private Button createButton() {
    Button b = new Button("Generate Game");
    b.setOnAction(e -> {
      String activeButton = "";
      try{
        activeButton = ((RadioButton)toggleGroup.getSelectedToggle()).getText();
        GameObject go = new GameObject("Games/"+activeButton);
        initialGameSetup(go);
        addGameMessage("Enjoy Your Game! Select Another to Start a New Game!");
      }
      catch(Exception ex){
        if(activeButton.equals(""))
          addGameMessage("You Haven't Selected a Game Type Yet!");
        else
          addGameMessage(ex.getMessage());
      }
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

  private VBox generateGameButtons() {
    VBox buttonBox = new VBox();
    int index = 0;
    File fil = new File(getClass().getClassLoader().getResource("Games").getFile());
    for(File f : fil.listFiles()){
      if(!f.getName().contains(".")){
        RadioButton b = new RadioButton(f.getName());
        b.setToggleGroup(toggleGroup);
        buttonBox.getChildren().add(index,b);
        index++;
      }
    }
    return buttonBox;
  }

  private void addGameMessage(String message) {
    Text t = new Text(message);
    if (messageBox.getChildren().size() > 0) {
      messageBox.getChildren().remove(0);
    }
    messageBox.getChildren().add(t);
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
    if(go.isRunning()){
      System.out.println("Player listened code :" + e.getCode());
      UpdateObject uo = go.getEngine().executeAction(e);
      updateView(uo, go);
      go.setRunning(uo.getGameRunning());
      if(hasNextAction(uo)){
        handleEvent(uo.getNextAction(), go);
      }
    }

  }

  private boolean hasNextAction(UpdateObject uo) {
    return uo.getNextAction() != null;
  }

  @Override
  public void updateView(UpdateObject uo, GameObject go) {
    try {
      go.getView().updateGridDisplay(uo);
    }
    catch (Exception e){
      addGameMessage(e.getMessage());
    }
  }

}
