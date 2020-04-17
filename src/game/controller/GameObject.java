package game.controller;

import game.engine.Engine;
import javafx.scene.Scene;
import game.parse.XMLException;
import game.view.View;

public class GameObject {
  private Engine engine;
  private View view;

  public GameObject(String folderPath) throws XMLException {


    Factory f = new Factory(folderPath);
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