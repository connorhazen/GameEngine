package controller;

import engine.Engine;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

public class GameObject {
  private Engine engine;
  private View view;

  public GameObject(String folderPath, Stage display) {
    Factory f = new Factory(folderPath, display);
    engine = f.makeEngine();
    view = f.makeView();
  }

  public View getView() {
    return view;
  }

  public Engine getEngine() {
    return engine;
  }

  public Scene getScene(){
    return view.getScene();
  }
}
