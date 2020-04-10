package controller;

import engine.Engine;
import javafx.scene.Scene;
import view.View;

public class GameObject {
  private Engine engine;
  private View view;

  public GameObject(String gameFile, Scene display) {
    Factory f = new Factory(gameFile, display);
    engine = f.makeEngine();
    view = f.makeView();
  }

  public View getView() {
    return view;
  }

  public Engine getEngine() {
    return engine;
  }
}
