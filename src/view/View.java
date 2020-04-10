package view;


import engine.UpdateObject;
import util.Grid;

public interface View {

  void updateGridDisplay(UpdateObject uo);

  void displayWindow(Window w);

  void removeWindow(Window w);


}
