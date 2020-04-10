package controller;

import engine.UpdateObject;
import util.Action;
import util.SimpleAction;

public interface Player {

  void makeListeners();

  void handleEvent(Action e);

  void updateView(UpdateObject uo);


}
