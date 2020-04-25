package game.controller;

import game.util.Action;
import game.util.Grid;
import game.util.SimpleAction;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu {
  private final static String GAME_FILE = "MainMenu.xml";
  private static final int PADDING = 5;
  private static final int V_GAP = 10;
  private static final int H_GAP = 50;
  private static final int WIDTH = 700;
  private static final int HEIGHT = 300;
  private static final Color BACKGROUND_COLOR = Color.TAN;
  private static final String TITLE = "GridGUYS Games - Final Project";
  private ComboBox selectFile;
  private ToggleGroup toggleGroup = new ToggleGroup();
  private Text messageBox;
  private ComboBox loadGame = new ComboBox();
  private BiConsumer<Action, GameObject> eventRunnable;
  private Consumer<GameObject> intialSet;
  private String selectedGame;
  private BorderPane pane;
  private ComboBox loadChoices;

  public MainMenu(Stage menuStage, BiConsumer<Action, GameObject> eventRunnable, Consumer<GameObject> initialSet){

    Group root = new Group();
    pane = new BorderPane();
    makeMessageBox();
    pane.setBottom(messageBox);
    pane.setTop(generateTitle());
    pane.setLeft(generateGameButtons());

    root.getChildren().add(pane);
    Scene mainScreen = new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR);
    menuStage.setScene(mainScreen);
    menuStage.setTitle(TITLE);
    menuStage.show();

    addGameMessage("Select a Game!");

    this.eventRunnable = eventRunnable;
    this.intialSet=initialSet;
  }



  private void makeMessageBox(){
    messageBox = new Text();

  }
  private Text generateTitle() {
    Text title = new Text(TITLE);
    title.setFill(Color.BLACK);
    title.setFont(Font.font("Verdana", 32));
    title.setTextAlignment(TextAlignment.CENTER);
    return title;
  }


  private void generateLoadComboBox() {
    loadChoices = new ComboBox();
    File fil = new File("data/Games/" + selectedGame + "/SavedGames");
    for(File f : fil.listFiles()){
      loadChoices.getItems().add(f.getName());
    }
    loadChoices.getItems().add("NewGame");
    loadChoices.setValue("NewGame");
    pane.setCenter(loadChoices);
    pane.setRight(createButton());
  }

  private Button createButton() {
    Button b = new Button("Generate Game");
    b.setOnAction(e -> {
      try{
        GameObject go = new GameObject("Games/"+selectedGame);
        if(!loadChoices.getValue().equals("NewGame"))
        {
          Grid g = GameStorageHandler.loadGame("Games/" + selectedGame+ "/SavedGames/"+loadChoices.getValue()).getGrid();
          go.getEngine().setGrid(g);
        }
        go.setStepFunction(() -> eventRunnable.accept(new SimpleAction("STEP"), go));
        go.getView().setEventCaller((g) -> eventRunnable.accept(g, go));
        intialSet.accept(go);
        addGameMessage("Enjoy Your Game! Select Another to Start a New Game!");
      }
      catch(Exception ex){
       ex.printStackTrace();
      }
    });
    return b;
  }



  private VBox generateGameButtons() {
    VBox buttonBox = new VBox();
    File fil = new File(getClass().getClassLoader().getResource("Games").getFile());
    for(File f : fil.listFiles()){
      if(!f.getName().contains(".")){
        Button b = new Button(f.getName());
        b.setOnMouseClicked(e -> {
          selectedGame = f.getName();
          generateLoadComboBox();

        });
        buttonBox.getChildren().add(b);
      }
    }
    return buttonBox;
  }

  private void addGameMessage(String message) {
    messageBox.setText(message);

  }

}
