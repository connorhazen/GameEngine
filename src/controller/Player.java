package controller;

import engine.UpdateObject;
import util.SimpleAction;

public interface Player {

  void makeListeners();

  void handleEvent(SimpleAction e);

  void updateView(UpdateObject uo);


}
