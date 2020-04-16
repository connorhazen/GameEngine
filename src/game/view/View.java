package game.view;


import game.engine.UpdateObject;
import javafx.scene.Scene;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void displayWindow(Window w);

  void removeWindow(Window w);


  Scene getScene();
}
