package game.util;

import javafx.scene.input.KeyEvent;

public class ClickedAction implements Action {

  private String code;
  private Coordinates cell;
  public ClickedAction(String name, Coordinates cell) {
    code =  name;
    this.cell = cell;
  }

  public String getCode(){
    return code;
  }

  /**
   * TODO: fix this shit
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    return code.equals(obj.toString());
  }

  @Override
  public int hashCode() {
    return code.hashCode();
  }

  @Override
  public Coordinates getCell() {
    return cell;
  }

  @Override
  public String toString() {
    return code;
  }

}
