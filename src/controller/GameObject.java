package controller;

import engine.Engine;
import javafx.scene.Scene;
import view.View;

public class GameObject {
  private Engine engine;
  private View view;

  public GameObject(String gameFile, Scene display) {
    engine = EngineFactory.make(gameFile);
    view = ViewFactory.make(gameFile, display);
  }

  public View getView() {
    return view;
  }

  public Engine getEngine() {
    return engine;
  }
}
