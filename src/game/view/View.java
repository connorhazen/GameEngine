package game.view;


import game.controller.GameObject;
import game.engine.UpdateObject;
import game.util.Action;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void displayWindow(Window w);

  void removeWindow(Window w);

  void setEventCaller(Consumer<Action> run);


  Scene getScene();

  Stage getStage();
}
