package game.view;


import game.util.UpdateObject;
import game.util.Action;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void setEventCaller(Consumer<Action> run);

  Scene getScene();

  Stage getStage();

  void setResetRunnable(Runnable run);

  void setPausePlayRunnable(Runnable run);
}
