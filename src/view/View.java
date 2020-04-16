package view;


import engine.UpdateObject;
import javafx.scene.Scene;
import util.Grid;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void displayWindow(Window w);

  void removeWindow(Window w);


  Scene getScene();
}
