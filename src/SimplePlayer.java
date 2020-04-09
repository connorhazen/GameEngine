import engine.Event;
import engine.UpdateObject;
import engine.UpdateStatus;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Grid;
import util.Grid2dArray;
import util.InitialState;
import util.State;
import view.SimpleView;
import view.View;

public class SimplePlayer implements Player {
  private View v;


  public SimplePlayer(Stage primaryStage) {
    v = new SimpleView(primaryStage);

    primaryStage.show();
  }

  @Override
  public void makeListeners() {

  }

  @Override
  public void handleEvent(Event e) {

  }

  @Override
  public void updateView(UpdateObject uo) {
    v.updateGridDisplay(uo);

  }


  public void tester(){
    updateView(new UpdateStatus(makeStupidGrid(), null, true));
  }

  private Grid makeStupidGrid(){
    Grid2dArray grid = new Grid2dArray(10,10);

    for(int x = 0; x < 10; x++){
      for(int y = 0; y < 10; y++){
        grid.setCell(x, y, new InitialState("dumb", "StateImages/questionMark.gif", 0) {
        });
      }
    }
    return grid;
  }
}
