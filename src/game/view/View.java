package game.view;


import game.engine.UpdateObject;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void displayWindow(Window w);

  void removeWindow(Window w);


  Scene getScene();

  Stage getStage();
}
