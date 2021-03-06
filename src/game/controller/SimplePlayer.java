package game.controller;

import game.util.UpdateObject;
import game.util.Action;
import game.util.SimpleAction;
import javafx.stage.Stage;


public class SimplePlayer implements Player {

  public SimplePlayer(Stage primaryStage) {
    MainMenu menu = new MainMenu(primaryStage, this::handleEvent, this::initialGameSetup);
  }

  private void restart(GameObject go) {
    go.getEngine().intializegrid();
    updateView(go.getEngine().getGrid(), go);
    go.setRunning(true);
  }

  private void initialGameSetup(GameObject go) {
    go.getView().setResetRunnable(() -> restart(go));
    go.getView().setPausePlayRunnable(() -> go.setRunning(!go.isRunning()));
    updateView(go.getEngine().getGrid(), go);
    makeListeners(go);
  }

  @Override
  public void makeListeners(GameObject go) {
    go.getScene().setOnKeyPressed(e -> {
      handleEvent(new SimpleAction(e), go);
    });
  }


  @Override
  public void handleEvent(Action e, GameObject go) {
    if (go.isRunning()) {
      //System.out.println("Player Listened Event: " + e.getCode());
      UpdateObject uo = go.getEngine().executeAction(e);
      go.setRunning(uo.getGameRunning());
      if (hasNextAction(uo) && go.isRunning()) {
        handleEvent(uo.getNextAction(), go);
        return;
      }
      updateView(uo, go);
    }
  }

  private boolean hasNextAction(UpdateObject uo) {
    return uo.getNextAction() != null;
  }

  @Override
  public void updateView(UpdateObject uo, GameObject go) {
    go.getView().updateGridDisplay(uo);
  }

}
