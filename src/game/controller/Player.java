package game.controller;

import game.engine.UpdateObject;
import game.util.Action;

public interface Player {

  void makeListeners(GameObject go);

  void handleEvent(Action e, GameObject go);

  void updateView(UpdateObject uo, GameObject go);


}
