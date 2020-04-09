package util;

import engine.Event;
import javafx.scene.input.KeyEvent;

public class Action implements Event {

  private String code;
  public Action(KeyEvent e) {
    code =  e.getText();
  }

  public Action(String e){
    code = e;
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
  public String toString() {
    return code;
  }
}
