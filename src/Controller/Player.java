package Controller;

import engine.Event;
import engine.UpdateObject;
import util.Action;

public interface Player {

  void makeListeners();

  void handleEvent(Action e);

  void updateView(UpdateObject uo);


}
