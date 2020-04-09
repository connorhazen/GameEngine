package Controller;

import engine.Engine;
import engine.Event;
import engine.UpdateObject;
import engine.UpdateStatus;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Action;
import util.Grid;
import util.Grid2dArray;
import util.InitialState;
import view.SimpleView;
import view.View;

public class SimplePlayer implements Player {
  private View v;
  private Engine engine;
  private final static double HEIGHT = 500;
  private final static double WIDTH = 500;

  private Scene currentScene;


  public SimplePlayer(Stage primaryStage) {

    Group root = new Group();
    Scene display = new Scene(root, WIDTH, HEIGHT);
    currentScene = display;

    primaryStage.setScene(display);
    v = new SimpleView(display);

    primaryStage.show();

    engine = new Engine();
  }

  @Override
  public void makeListeners() {
    currentScene.setOnKeyPressed(e -> handleEvent(new Action(e)));
  }


  @Override
  public void handleEvent(Action e) {
    engine.

  }

  @Override
  public void updateView(UpdateObject uo) {
    v.updateGridDisplay(uo);

  }


  public void tester(){
    updateView(new UpdateStatus(makeStupidGrid(), null, true));
  }

  private Grid makeStupidGrid(){
    Grid2dArray grid = new Grid2dArray(10,10);

    for(int x = 0; x < 10; x++){
      for(int y = 0; y < 10; y++){
        grid.setCell(x, y, new InitialState("dumb", "StateImages/questionMark.gif", 0) {
        });
      }
    }
    return grid;
  }
}
