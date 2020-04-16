package controller;

import engine.UpdateObject;
import util.Action;
import util.SimpleAction;

public interface Player {

  void makeListeners(GameObject go);

  void handleEvent(Action e, GameObject go);

  void updateView(UpdateObject uo, GameObject go);


}
