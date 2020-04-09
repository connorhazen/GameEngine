package controller;

import javafx.scene.Scene;
import view.SimpleView;
import view.View;

public class ViewFactory {

  public static View make(String gameFile, Scene display) {
    return new SimpleView(display);
  }
}
