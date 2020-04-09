import engine.Event;
import engine.UpdateObject;
import javafx.application.Application;

public interface Player {

  void makeListeners();

  void handleEvent(Event e);

  void updateView(UpdateObject uo);


}
