package controller;

import engine.Engine;
import engine.UpdateObject;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Action;
import util.SimpleAction;
import view.View;

public class SimplePlayer implements Player {

  private final static String GAME_FILE = "game1.xml";




  public SimplePlayer(Stage primaryStage) {


    GameObject go = new GameObject(GAME_FILE);
    updateView(go.getEngine().getGrid(), go);
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
