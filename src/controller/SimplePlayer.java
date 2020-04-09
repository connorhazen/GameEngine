package controller;

import engine.Engine;
import engine.UpdateObject;
import engine.UpdateStatus;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Action;
import util.Grid;
import util.Grid2dArray;
import util.InitialState;
import view.View;

public class SimplePlayer implements Player {
  private View view;
  private Engine engine;
  private final static double HEIGHT = 500;
  private final static double WIDTH = 500;
  private final static String GAME_FILE = "game1.xml";

  private Scene currentScene;


  public SimplePlayer(Stage primaryStage) {
    Group root = new Group();
    Scene display = new Scene(root, WIDTH, HEIGHT);
    currentScene = display;
    primaryStage.setScene(display);
    primaryStage.show();

    GameObject go = new GameObject(GAME_FILE, display);
    view = go.getView();
    engine = go.getEngine();

    view.updateGridDisplay(engine.getGrid());
  }

  @Override
  public void makeListeners() {
    currentScene.setOnKeyPressed(e -> handleEvent(new Action(e)));
  }


  @Override
  public void handleEvent(Action e) {
    UpdateObject uo = engine.executeAction(e);
    if (hasNextAction(uo)){
      updateView(uo);
      handleEvent(e);
    }

  }

  private boolean hasNextAction(UpdateObject uo) {
    return uo.getNextUserAction() !=null;
  }

  @Override
  public void updateView(UpdateObject uo) {
    view.updateGridDisplay(uo);

  }

}
