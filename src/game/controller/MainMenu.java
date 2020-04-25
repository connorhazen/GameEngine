package game.controller;

import game.util.Action;
import game.util.Grid;
import game.util.SimpleAction;
import java.io.File;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu {
  private final String LANGUAGE = "english";
  private final ResourceBundle  MY_RESOURCES = ResourceBundle.getBundle("resources." + LANGUAGE);
  private final String DATA = "data/";
  private final String PATH_TO_GAMES = "Games/";
  private final String SAVED_GAMES = "/SavedGames/";

  private static final int WIDTH = 600;
  private static final int HEIGHT = 150;
  private static final Color BACKGROUND_COLOR = Color.TAN;

  private Text messageBox;

  private BiConsumer<Action, GameObject> eventRunnable;
  private Consumer<GameObject> initialSet;
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
    menuStage.setTitle(MY_RESOURCES.getString("title"));
    menuStage.show();

    addGameMessage(MY_RESOURCES.getString("pickGame"));

    this.eventRunnable = eventRunnable;
    this.initialSet=initialSet;
  }



  private void makeMessageBox(){
    messageBox = new Text();

  }
  private Text generateTitle() {
    Text title = new Text(MY_RESOURCES.getString("title"));
    title.setFill(Color.BLACK);
    title.setFont(Font.font("Verdana", 32));
    title.setTextAlignment(TextAlignment.CENTER);
    return title;
  }


  private void generateLoadComboBox() {
    loadChoices = new ComboBox();
    File fil = new File(DATA+PATH_TO_GAMES + selectedGame + SAVED_GAMES);
    for(File f : fil.listFiles()){
      loadChoices.getItems().add(f.getName());
    }
    loadChoices.getItems().add(MY_RESOURCES.getString("newGame"));
    loadChoices.setValue(MY_RESOURCES.getString("newGame"));
    pane.setCenter(loadChoices);
    pane.setRight(createButton());
  }

  private Button createButton() {
    Button b = new Button(MY_RESOURCES.getString("makeGame"));
    b.setOnAction(e -> {
      try{
        GameObject go = new GameObject(PATH_TO_GAMES+selectedGame);
        if(!loadChoices.getValue().equals(MY_RESOURCES.getString("newGame"))) {
          Grid g = GameStorageHandler.loadGame(PATH_TO_GAMES + selectedGame+ SAVED_GAMES+loadChoices.getValue()).getGrid();
          go.getEngine().setGrid(g);
        }
        go.setStepFunction(() -> eventRunnable.accept(new SimpleAction("STEP"), go));
        go.getView().setEventCaller((g) -> eventRunnable.accept(g, go));
        initialSet.accept(go);
        addGameMessage(MY_RESOURCES.getString("enjoy"));
      }
      catch(Exception ex){
       addGameMessage(ex.getMessage());
      }
    });
    return b;
  }



  private VBox generateGameButtons() {
    VBox buttonBox = new VBox();
    File fil = new File(getClass().getClassLoader().getResource(PATH_TO_GAMES).getFile());
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
