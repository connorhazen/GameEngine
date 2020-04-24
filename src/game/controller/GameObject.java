package game.controller;

import game.engine.Engine;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.Scene;
import game.parse.XMLException;
import game.view.View;

public class GameObject {
  private boolean running;
  private Engine engine;
  private View view;
  private Factory f;

  public GameObject(String folderPath) throws XMLException {
    f = new Factory(folderPath);

    engine = f.makeEngine();

    view = f.makeView();
    running = true;
  }

  public View getView() {
    return view;
  }

  public Engine getEngine() {
    return engine;
  }

  public Scene getScene(){
    return view.getScene();
  }

  public boolean isRunning(){
    return running;
  }

  public void setRunning(boolean running){
    this.running = running;
  }

  private void setTimer(int time, Runnable runnable){
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        if(view.getStage().isFocused()){
          Platform.runLater(runnable);
        }
      }
    };

    Timer t = new Timer();

    t.scheduleAtFixedRate(task, 1, time);

    view.getStage().setOnCloseRequest(e->t.cancel());
  }


  public void setStepFunction(Runnable run) {
    int step = f.getStep();
    if(step!=0){
      setTimer(step, run);
    }
  }
}
